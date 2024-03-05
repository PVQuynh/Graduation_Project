package com.chat.websocket.controller;

import com.chat.websocket.dto.request.AddMemberReq;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group-member")
@RequiredArgsConstructor
public class GroupMemberController {

  private final GroupMemberService groupMemberService;

  @PostMapping
  public MessageResponse addGroupMember(@RequestBody AddMemberReq addmemberReq) {
    MessageResponse ms = new MessageResponse();
    try {
      groupMemberService.add(addmemberReq);
    } catch (Exception ex) {
      ms.code = 5000;
      ms.message = ex.getMessage();
    }
    return ms;
  }

  @DeleteMapping("/{groupMemberId}")
  public MessageResponse deleteGroupMember(@PathVariable long groupMemberId){
    MessageResponse ms = new MessageResponse();
    try {
      groupMemberService.deleteMember(groupMemberId);
    } catch (Exception ex) {
      ms.code = 5000;
      ms.message = ex.getMessage();
    }
    return ms;
  }
}
