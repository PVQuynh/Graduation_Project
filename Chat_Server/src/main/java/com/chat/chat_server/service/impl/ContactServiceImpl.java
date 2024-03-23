package com.chat.chat_server.service.impl;

import com.chat.chat_server.dto.PageDTO;
import com.chat.chat_server.dto.request.ContactByEmailReq;
import com.chat.chat_server.dto.request.ContactReq;
import com.chat.chat_server.dto.request.ContactSearchReq;
import com.chat.chat_server.dto.request.UploadAvatarReq;
import com.chat.chat_server.dto.response.ContactRes;
import com.chat.chat_server.entity.Contact;
import com.chat.chat_server.exception.BusinessLogicException;
import com.chat.chat_server.mapper.impl.ContactMapper;
import com.chat.chat_server.repository.ContactRepository;
import com.chat.chat_server.service.ContactService;
import com.chat.chat_server.utils.EmailUtils;
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

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    @Override
    public ContactRes getContactById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Contact contact = contactRepository.findById(id).orElseThrow(BusinessLogicException::new);

        return new ContactRes(contact);
    }

    @Override
    public ContactRes getByEmail(ContactByEmailReq contactByEmailReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Contact contact = contactRepository.findByEmail(contactByEmailReq.getEmail()).orElseThrow(BusinessLogicException::new);

        return new ContactRes(contact);
    }

    @Override
    public ContactRes getMyContact() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Contact contact = contactRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLogicException());

        return new ContactRes(contact);
    }

    @Override
    public PageDTO<ContactRes> search(ContactSearchReq contactSearchReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);

        Root<Contact> root = criteriaQuery.from(Contact.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by text (if provided)
        if (!ObjectUtils.isEmpty(contactSearchReq.text)) {
            String searchText = "%" + contactSearchReq.text + "%";
            Predicate nameLike = criteriaBuilder.like(root.get("name"), searchText);
            Predicate emailLike = criteriaBuilder.like(root.get("email"), searchText);
            Predicate validEmail = criteriaBuilder.notEqual(root.get("email"), email);
            predicates.add(criteriaBuilder.or(nameLike, emailLike));
            predicates.add(validEmail);
        } else return null;

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
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        return contactRepository.findById(id).orElseThrow(BusinessLogicException::new);
    }


}
