package com.chat.websocket.controller;

import com.chat.websocket.dto.request.GroupMemberReq;
import com.chat.websocket.dto.response.GroupMemberRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.GroupMemberService;
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
      ms.code = 5000;
      ms.message = ex.getMessage();
    }
    return ms;
  }

  @DeleteMapping("/{id}")
  public MessageResponse deleteGroupMember(@PathVariable long id){
    MessageResponse ms = new MessageResponse();
    try {
      groupMemberService.deleteMember(id);
    } catch (Exception ex) {
      ms.code = 5000;
      ms.message = ex.getMessage();
    }
    return ms;
  }
}
