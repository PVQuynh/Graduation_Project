package com.example.UserService.service.impl;


import com.example.UserService.client.ChatFeignClient;
import com.example.UserService.client.request.ContactRequest;
import com.example.UserService.client.request.UploadAvatarClientReq;
import com.example.UserService.dto.PageDTO;
import com.example.UserService.dto.UserDTO;
import com.example.UserService.dto.UserDetailDTO;
import com.example.UserService.dto.request.ChangePasswordReq;
import com.example.UserService.dto.request.RegisterReq;
import com.example.UserService.dto.request.UpdateUserReq;
import com.example.UserService.dto.request.UploadAvatarReq;
import com.example.UserService.dto.request.UserSearchReq;
import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.entity.FriendShip;
import com.example.UserService.entity.Role;
import com.example.UserService.entity.User;
import com.example.UserService.enum_constant.Gender;
import com.example.UserService.exception.BusinessLogicException;
import com.example.UserService.mapper.impl.UserMapper;
import com.example.UserService.repository.FriendShipRepository;
import com.example.UserService.repository.RoleRepository;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.KeycloakService;
import com.example.UserService.service.UserService;
import com.example.UserService.utils.EmailUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @PersistenceContext(type = PersistenceContextType.EXTENDED)
  private EntityManager entityManager;

  private final KeycloakService keycloakService;

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final RoleRepository roleRepository;

  private final ChatFeignClient client;

  private final FriendShipRepository friendShipRepository;


  @Override
  public User create(RegisterReq registerReq) {
    ContactRequest contactRequest = new ContactRequest(registerReq.getName(),
        registerReq.getEmail(), null);
    MessagesResponse ms = client.createContact(contactRequest);
    if (ms.code == 200) {
      User user = new User();
      user.setName(registerReq.getName());
      user.setEmail(registerReq.getEmail());
      user.setPassword(registerReq.getPassword());
      Role role = roleRepository.findByCode(registerReq.getRole()).orElse(null);
      user.setRole(role);
      return userRepository.save(user);
    }
    return null;
  }


  @Override
  public void deleteUser(long id) {

    userRepository.deleteById(id);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);


  }


  @Override
  public User findById(long Id) {
    var userOptional = userRepository.findById(Id);
    if (userOptional.isPresent()) {
      return userOptional.get();
    }
    ;
    return null;
  }

  @Override
  public UserDTO getCurrentUser() {
    String email = EmailUtils.getCurrentUser();
    User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());

    return userMapper.toDTO(user);


  }

  @Override
  public void updateUser(UpdateUserReq updateUserReq) throws ParseException {
    String email = EmailUtils.getCurrentUser();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date birthDay = dateFormat.parse(updateUserReq.getBirthDay());

    if (!ObjectUtils.isEmpty(email)) {
      userRepository.updateUser(email, updateUserReq.getName(), updateUserReq.getPhoneNumber(),
          updateUserReq.getAddress(), birthDay, Gender.valueOf(updateUserReq.getGender()));
    }

  }

  @Override
  public void changePassword(ChangePasswordReq changePasswordReq) {
    String email = EmailUtils.getCurrentUser();
    User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());
    if (user.getPassword().equals(changePasswordReq.getOldPassword())) {
      user.setPassword(changePasswordReq.getNewPassword());
      userRepository.save(user);
      keycloakService.changePassword(changePasswordReq);
    }
  }

  @Override
  public PageDTO<UserDTO> search(UserSearchReq userSearchReq) {
    String email = EmailUtils.getCurrentUser();
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
    Root<User> root = criteriaQuery.from(User.class);
    List<Predicate> predicates = new ArrayList<>();

    // Filter by text (if provided)
    String searchText = "%" + userSearchReq.text + "%";
    Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
    Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
    Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
    predicates.add(criteriaBuilder.or(nameLike, emailLike));
    predicates.add(validEmail);

    // Filter by descending and orderBy (if provided)
    if (!ObjectUtils.isEmpty(userSearchReq.ascending) && !ObjectUtils.isEmpty(
        userSearchReq.orderBy)) {
      if (userSearchReq.ascending) {
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get(userSearchReq.orderBy)));
      } else {
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(userSearchReq.orderBy)));
      }
    }

    if (!predicates.isEmpty()) {

      criteriaQuery.where(predicates.toArray(new Predicate[0]));
    }
    TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
    int totalRows = query.getResultList().size();
    List<User> results = query
        .setFirstResult((userSearchReq.page - 1) * userSearchReq.size) // Offset
        .setMaxResults(userSearchReq.size) // Limit
        .getResultList();
    PageDTO<UserDTO> userResPageDTO = new PageDTO<>(userMapper.toDTOList(results),
        userSearchReq.page, totalRows);

    return userResPageDTO;
  }

  @Override
  public UserDetailDTO getUserById(long userId) {
    String email = EmailUtils.getCurrentUser();
    if (!ObjectUtils.isEmpty(email)) {
      User user = userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException());
      UserDTO userDTO = userMapper.toDTO(user);
      UserDetailDTO userDetailDTO = new UserDetailDTO(userDTO);
      User currentUser = userRepository.findByEmail(email)
          .orElseThrow(() -> new BusinessLogicException());
      if (currentUser.getRole().getCode().equals("USER")) {
        FriendShip friendShip = friendShipRepository.checkFriendStatus(currentUser.getId(),
                userDetailDTO.getId())
            .orElse(null);
        if (!ObjectUtils.isEmpty(friendShip)) {
          userDetailDTO.setFriendStatus(friendShip.getStatus());
        }
      }
      return userDetailDTO;
    }
    return null;
  }

  @Override
  public void uploadAvatar(UploadAvatarReq uploadAvatarReq) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }
    UploadAvatarClientReq uploadAvatarClientReq = UploadAvatarClientReq.builder()
        .avatarLocation(uploadAvatarReq.getAvatarLocation()).email(email).build();
    MessagesResponse ms = client.uploadAvatar(uploadAvatarClientReq);
    if (ms.code == 200) {

      User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());
      user.setAvatarLocation(uploadAvatarReq.getAvatarLocation());
      userRepository.save(user);
    }

  }


}
