package com.chat.websocket.service.impl;

import com.chat.websocket.dto.PageDTO;
import com.chat.websocket.dto.request.ContactByEmailReq;
import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.ContactSearchReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.mapper.Mapper;
import com.chat.websocket.mapper.impl.ContactMapper;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.utils.EmailUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    @Override
    public ContactRes getContactById(long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(BusinessLogicException::new);

        return new ContactRes(contact);
    }

    @Override
    public ContactRes getByEmail(ContactByEmailReq contactByEmailReq) {
        Contact contact = contactRepository.findByEmail(contactByEmailReq.getEmail()).orElseThrow(BusinessLogicException::new);

        return new ContactRes(contact);
    }

    @Override
    public ContactRes getMyContact() {
        String email = EmailUtils.getCurrentUser();

        Contact contact = contactRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException());

        return new ContactRes(contact);
    }

    @Override
    public PageDTO<ContactRes> search(ContactSearchReq contactSearchReq) {
        String email = EmailUtils.getCurrentUser();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);

        Root<Contact> root = criteriaQuery.from(Contact.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        String searchText = "%" + contactSearchReq.text + "%";
        Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
        Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
        Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
        predicates.add(criteriaBuilder.or(nameLike, emailLike));
        predicates.add(validEmail);

        // Filter by descending and orderBy (if provided)
        if (!ObjectUtils.isEmpty(contactSearchReq.ascending) && !ObjectUtils.isEmpty(contactSearchReq.orderBy)) {
            if (contactSearchReq.ascending) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(contactSearchReq.orderBy)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(contactSearchReq.orderBy)));
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Contact> query = entityManager.createQuery(criteriaQuery);
        int totalRows = query.getResultList().size();

        List<Contact> results = query
                .setFirstResult((contactSearchReq.page - 1) * contactSearchReq.size) // Offset
                .setMaxResults(contactSearchReq.size) // Limit
                .getResultList();

        PageDTO<ContactRes> contactResPageDTO = new PageDTO<>(contactMapper.toDTOList(results),
                contactSearchReq.page, totalRows);

        return contactResPageDTO;
    }

    @Override
    public void saveContact(ContactReq contactReq) {
        Contact contact = new Contact(contactReq);
        contactRepository.save(contact);
    }

    @Override
    public void uploadAvatar(UploadAvatarReq uploadAvatarReq) {
        Contact contact = contactRepository.findByEmail(uploadAvatarReq.email).orElseThrow(BusinessLogicException::new);
        contact.setAvatarLocation(uploadAvatarReq.avatarLocation);
        contactRepository.save(contact);

    }

    @Override
    public Contact findById(long id) {
        return contactRepository.findById(id).orElseThrow(BusinessLogicException::new);
    }


}
