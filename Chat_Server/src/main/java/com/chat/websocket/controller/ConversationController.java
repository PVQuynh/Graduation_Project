package com.chat.websocket.controller;

import com.chat.websocket.dto.request.CreateConversationReq;
import com.chat.websocket.dto.response.GetListConversationRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationController {

  private final ConversationService conversationService;

  @PostMapping
  public MessageResponse createConversation(
      @RequestBody CreateConversationReq createConversationReq) {
    MessageResponse ms = new MessageResponse();
    try {
      conversationService.createConversation(createConversationReq);
    } catch (Exception e) {
      ms.code = 5000;
      ms.message = e.getMessage();
    }
    return ms;
  }


  @DeleteMapping("/{conversationID}")
  public MessageResponse deleteConversation(@PathVariable("conversationID") int conversationID) {
    MessageResponse ms = new MessageResponse();
    try {
      conversationService.deleteById(conversationID);
    } catch (Exception ex) {
      ms.code = 5000;
      ms.message = ex.getMessage();
    }
    return ms;
  }

  @GetMapping("/list/me")
  public List<GetListConversationRes> getMyListConversation() {
   return conversationService.getMyList();
  }

  @GetMapping("/contactId/{contactId}")
  public GetListConversationRes getConverSationByContactId(@PathVariable long contactId) {
    return conversationService.getByContactId(contactId);
  }

}
