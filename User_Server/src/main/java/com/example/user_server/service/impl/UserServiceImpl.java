package com.example.user_server.service.impl;


import com.example.user_server.client.ChatFeignClient;
import com.example.user_server.client.request.ContactClientReq;
import com.example.user_server.client.request.UploadAvatarClientReq;
import com.example.user_server.dto.PageDTO;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.dto.UserDetailDTO;
import com.example.user_server.dto.request.*;
import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.entity.FriendShip;
import com.example.user_server.entity.Role;
import com.example.user_server.entity.User;
import com.example.user_server.enum_constant.Gender;
import com.example.user_server.exception.BadRequestException;
import com.example.user_server.exception.BusinessLogicException;
import com.example.user_server.exception.ResourceNotFoundException;
import com.example.user_server.exception.UnAuthorizedException;
import com.example.user_server.mapper.impl.UserMapper;
import com.example.user_server.repository.FriendShipRepository;
import com.example.user_server.repository.RoleRepository;
import com.example.user_server.repository.UserRepository;
import com.example.user_server.service.KeycloakService;
import com.example.user_server.service.UserService;
import com.example.user_server.utils.EmailUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    private final KeycloakService keycloakService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final ChatFeignClient chatFeignClient;

    private final FriendShipRepository friendShipRepository;


    @Transactional
    @Override
    public User create(RegisterReq registerReq) {
        ContactClientReq contactRequest = new ContactClientReq(registerReq.getName(),
                registerReq.getEmail(), null);
        MessageResponse ms = chatFeignClient.createContact(contactRequest);

        if (ms.code == 200) {
            User user = new User();
            user.setName(registerReq.getName());
            user.setEmail(registerReq.getEmail());
            user.setPassword(registerReq.getPassword());
            Role role = roleRepository.findByCode(registerReq.getRole()).orElseThrow(ResourceNotFoundException::new);
            user.setRole(role);

            return userRepository.save(user);
        }
        return null;
    }


    @Override
    public void deleteUser(long id) {
        String email = EmailUtils.getCurrentUser();
        if (org.apache.commons.lang3.ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public User findById(long Id) {
        return userRepository.findById(Id).orElse(null);
    }

    @Override
    public UserDTO getCurrentUser() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        UserDTO userDTO = userRepository.findByEmail(email).map(userMapper::toDTO).orElse(null);
        return userDTO;
    }

    @Transactional
    @Override
    public void updateUser(UpdateUserReq updateUserReq) throws ParseException {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        if (updateUserReq.getName() != null) {
            user.setName(updateUserReq.getName());
        }
        if (updateUserReq.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserReq.getPhoneNumber());
        }
        if (updateUserReq.getAddress() != null) {
            user.setAddress(updateUserReq.getAddress());
        }
        if (updateUserReq.getBirthDay() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDay = dateFormat.parse(updateUserReq.getBirthDay());
            user.setBirthDay(birthDay);
        }
        if (updateUserReq.getGender() != null) {
            user.setGender(Gender.valueOf(updateUserReq.getGender()));
        }

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void changePassword(ChangePasswordReq changePasswordReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        if (user.getPassword().equals(changePasswordReq.getOldPassword())) {
            user.setPassword(changePasswordReq.getNewPassword());
            userRepository.save(user);
            keycloakService.changePassword(changePasswordReq);
        } else {
            throw new BadRequestException();
        }
    }


    @Override
    public PageDTO<UserDTO> search(UserSearchReq userSearchReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(userSearchReq.text)) {
            String searchText = "%" + userSearchReq.text + "%";
            Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
            Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
            Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
            predicates.add(criteriaBuilder.or(nameLike, emailLike));
            predicates.add(validEmail);
        } else return null;

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
    public PageDTO<UserDTO> searchV2(int page, int size, String text, boolean ascending, String orderBy) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(text)) {
            String searchText = "%" + text + "%";
            Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
            Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
            Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
            predicates.add(criteriaBuilder.or(nameLike, emailLike));
            predicates.add(validEmail);
        } else return null;

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(ascending) && !ObjectUtils.isEmpty(orderBy)) {
            if (ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();

        List<User> results = query
                .setFirstResult((page - 1) * size) // Offset
                .setMaxResults(size) // Limit
                .getResultList();

        PageDTO<UserDTO> userResPageDTO = new PageDTO<>(userMapper.toDTOList(results), page, totalRows);

        return userResPageDTO;
    }

    @Override
    public UserDetailDTO getUserById(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        UserDTO userDTO = userMapper.toDTO(user);
        UserDetailDTO userDetailDTO = new UserDetailDTO(userDTO);
        User currentUser = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        if (currentUser.getRole().getCode().equals("USER")) {
            FriendShip friendShip = friendShipRepository.checkFriendStatus(currentUser.getId(),
                            userDetailDTO.getUserId())
                    .orElseThrow(BusinessLogicException::new);

            userDetailDTO.setFriendStatus(friendShip.getStatus());
        }
        return userDetailDTO;

    }

    @Transactional
    @Override
    public void uploadAvatar(UploadAvatarReq uploadAvatarReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        UploadAvatarClientReq uploadAvatarClientReq = UploadAvatarClientReq
                .builder()
                .avatarLocation(uploadAvatarReq.getAvatarLocation())
                .email(email)
                .build();

        MessageResponse ms = chatFeignClient.uploadAvatar(uploadAvatarClientReq);

        if (ms.code == 200) {
            User user = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
            user.setAvatarLocation(uploadAvatarReq.getAvatarLocation());
            userRepository.save(user);
        }

    }
}
