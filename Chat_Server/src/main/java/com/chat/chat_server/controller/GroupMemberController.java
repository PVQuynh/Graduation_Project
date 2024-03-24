package com.chat.chat_server.controller;

import com.chat.chat_server.dto.request.GroupMemberReq;
import com.chat.chat_server.dto.response.GroupMemberRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group-member")
@RequiredArgsConstructor
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    @GetMapping("/{conversationId}")
    public MessageResponse getGroupMemberInConversation(@PathVariable("conversationId") long conversationId) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = groupMemberService.getGroupMembersByConversationId(conversationId);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping
    public MessageResponse addGroupMember(@RequestBody GroupMemberReq groupMemberReq) {
        MessageResponse ms = new MessageResponse();
        try {
            groupMemberService.save(groupMemberReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteGroupMember(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            groupMemberService.deleteMember(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }
}
