package com.chat.chat_server.service.impl;

import com.chat.chat_server.dto.request.ConversationReq;
import com.chat.chat_server.dto.request.UpdateConversationReq;
import com.chat.chat_server.dto.response.GrouAttachConvRes;
import com.chat.chat_server.dto.response.ConversationAndGrouAttachConvListRes;
import com.chat.chat_server.dto.response.LastMessageRes;
import com.chat.chat_server.entity.Contact;
import com.chat.chat_server.entity.Conversation;
import com.chat.chat_server.entity.GroupMember;
import com.chat.chat_server.entity.Message;
import com.chat.chat_server.enum_constant.ConversationType;
import com.chat.chat_server.exception.BusinessLogicException;
import com.chat.chat_server.repository.ContactRepository;
import com.chat.chat_server.repository.ConversationRepository;
import com.chat.chat_server.service.ConversationService;
import com.chat.chat_server.utils.EmailUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationServiceImpl implements ConversationService {

    private final ContactRepository contactRepository;

    private final ConversationRepository conversationRepository;

    @Override
    public Conversation getById(long conversationId) {
        return conversationRepository.findById(conversationId).orElseThrow(BusinessLogicException::new);
    }

    @Override
    public ConversationAndGrouAttachConvListRes getByContactId(long contactId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        //
        // Conversation
        //
        Conversation conversation = conversationRepository.getConversationByContractId(email, contactId);

        // Nếu chưa có conversation thì tạo
        if (ObjectUtils.isEmpty(conversation)) {
            ConversationReq conversationReq = null;

            Contact contactById = contactRepository.findById(contactId)
                    .orElseThrow(BusinessLogicException::new);

            Contact contactByEmail = contactRepository.findByEmail(email)
                    .orElseThrow(BusinessLogicException::new);

            if (!contactById.equals(contactByEmail)) {
                conversationReq = ConversationReq.builder()
                        .conversationName(email + "_" + contactByEmail.getEmail())
                        .conversationType(ConversationType.SINGLE.toString())
                        .contactIds(Arrays.asList(contactByEmail.getId(), contactById.getId()))
                        .build();

                conversation = createConversation(conversationReq);
            }
        }

        //
        // GroupMember
        //
        List<GroupMember> groupMemberList = conversation.getGroupMembers();
        List<GrouAttachConvRes> grouAttachConvResList = new ArrayList<>();

        if (ObjectUtils.isNotEmpty(groupMemberList)) {
            grouAttachConvResList = groupMemberList.stream()
                    .map(groupMember -> {
                                Contact contact = groupMember.getContact();

                                GrouAttachConvRes grouAttachConvRes = GrouAttachConvRes.builder()
                                        .contactId(contact.getId())
                                        .avatarLocation(contact.getAvatarLocation())
                                        .contactName(contact.getName())
                                        .email(contact.getEmail())
                                        .lastActivity(groupMember.getLastActivity())
                                        .build();

                                LastMessageRes lastMessageRes = null;
                                if (ObjectUtils.isNotEmpty(groupMember.getMessages())) {
                                    Message lastMessage = groupMember.getMessages().get(groupMember.getMessages().size() - 1);

                                    lastMessageRes = LastMessageRes.builder()
                                            .messageId(lastMessage.getId())
                                            .content(lastMessage.getContent())
                                            .messageType(lastMessage.getMessageType())
                                            .mediaLocation(lastMessage.getMediaLocation())
                                            .status(lastMessage.getStatus())
                                            .created(lastMessage.getCreated())
                                            .contactName(lastMessage.getGroupMember().getContact().getName())
                                            .build();
                                }

                                grouAttachConvRes.setLastMessageRes(lastMessageRes);
                                return grouAttachConvRes;
                            }
                    ).collect(Collectors.toList());
        }

        ConversationAndGrouAttachConvListRes conversationAndGrouAttachConvListRes = ConversationAndGrouAttachConvListRes.builder()
                .conversationId(conversation.getId())
                .grouAttachConvResList(grouAttachConvResList)
                .build();

        return conversationAndGrouAttachConvListRes;
    }

    @Override
    public void updateConversation(UpdateConversationReq updateConversationReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Conversation conversation = conversationRepository.findById(updateConversationReq.getConversationId()).orElseThrow(() -> new BusinessLogicException());
        conversation.setConversationName(updateConversationReq.getConversationName());

        conversationRepository.save(conversation);
    }

    @Override
    public void deleteById(long conversationID) {
        conversationRepository.deleteById(conversationID);
    }

    @Override
    public List<ConversationAndGrouAttachConvListRes> getMyList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<Conversation> conversationList = conversationRepository.getMyConversationList(email);

        List<ConversationAndGrouAttachConvListRes> allMe = conversationList.stream()
                .map(conversation -> {
                    List<GroupMember> groupMemberList = conversation.getGroupMembers();
                    List<GrouAttachConvRes> grouAttachConvResList = new ArrayList<>();

                    if (ObjectUtils.isNotEmpty(groupMemberList)) {
                        grouAttachConvResList = groupMemberList.stream()
                                .map(groupMember -> {
                                            Contact contact = groupMember.getContact();

                                            GrouAttachConvRes grouAttachConvRes = GrouAttachConvRes.builder()
                                                    .contactId(contact.getId())
                                                    .avatarLocation(contact.getAvatarLocation())
                                                    .contactName(contact.getName())
                                                    .email(contact.getEmail())
                                                    .lastActivity(groupMember.getLastActivity())
                                                    .build();

                                            LastMessageRes lastMessageRes = null;
                                            if (ObjectUtils.isNotEmpty(groupMember.getMessages())) {
                                                Message lastMessage = groupMember.getMessages().get(groupMember.getMessages().size() - 1);

                                                lastMessageRes = LastMessageRes.builder()
                                                        .messageId(lastMessage.getId())
                                                        .content(lastMessage.getContent())
                                                        .messageType(lastMessage.getMessageType())
                                                        .mediaLocation(lastMessage.getMediaLocation())
                                                        .status(lastMessage.getStatus())
                                                        .created(lastMessage.getCreated())
                                                        .contactName(lastMessage.getGroupMember().getContact().getName())
                                                        .build();
                                            }

                                            grouAttachConvRes.setLastMessageRes(lastMessageRes);

                                            return grouAttachConvRes;
                                        }

                                ).collect(Collectors.toList());
                    }
                    ConversationAndGrouAttachConvListRes conversationAndGrouAttachConvListRes = ConversationAndGrouAttachConvListRes.builder()
                            .conversationId(conversation.getId())
                            .grouAttachConvResList(grouAttachConvResList)
                            .build();

                    return conversationAndGrouAttachConvListRes;
                }).collect(Collectors.toList());

        return allMe;
    }

    //
    //
    //
    @Override
    public Conversation createConversation(ConversationReq conversationReq) {
        Conversation conversation = Conversation.builder()
                .conversationType(ConversationType.valueOf(conversationReq.conversationType))
                .conversationName(conversationReq.conversationName)
                .build();

        List<GroupMember> groupMembers = conversationReq.getContactIds().stream()
                .map(contactId -> {
                    Contact contact = contactRepository.findById(contactId)
                            .orElseThrow(() -> new BusinessLogicException("User : " + contactId + " doesn't exist"));
                    return contact;
                })

                .map(contact -> GroupMember.builder()
                        .contact(contact)
                        .conversation(conversation)
                        .build()
                )
                .collect(Collectors.toList());

        conversation.setGroupMembers(groupMembers);

        return conversationRepository.save(conversation);
    }

    @Override
    public void save(Conversation conversation) {
        conversationRepository.save(conversation);
    }

}
