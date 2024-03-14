package com.chat.chat_server.controller;

import com.chat.chat_server.constant.ExceptionConstant;
import com.chat.chat_server.constant.HTTPCode;
import com.chat.chat_server.dto.request.GroupMemberReq;
import com.chat.chat_server.dto.response.GroupMemberRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group-member")
@RequiredArgsConstructor
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    @GetMapping("/{conversationId}")
    public List<GroupMemberRes> getGroupMemberInConversation(@PathVariable("conversationId") long conversationId) {
        try {
            return groupMemberService.getGroupMembersByConversationId(conversationId);
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping
    public MessageResponse addGroupMember(@RequestBody GroupMemberReq groupMemberReq) {
        MessageResponse ms = new MessageResponse();
        try {
            groupMemberService.save(groupMemberReq);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteGroupMember(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        try {
            groupMemberService.deleteMember(id);
        } catch (Exception ex) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }
}
