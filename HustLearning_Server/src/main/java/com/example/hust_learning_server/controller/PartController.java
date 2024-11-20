package com.example.hust_learning_server.controller;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.MessageResponse;
import com.example.hust_learning_server.dto.response.PartRes;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;

    @PostMapping
    public ResponseEntity<MessageResponse> addPart(@RequestBody PartReq partReq) {
        MessageResponse ms = new MessageResponse();
        partService.addPart(partReq);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-list")
    public ResponseEntity<MessageResponse> addPartList(@RequestBody List<PartReq> partReqList) {
        MessageResponse ms = new MessageResponse();
        partService.addParts(partReqList);
        return ResponseEntity.ok(ms);
    }
    
    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllPart(
        @RequestParam(required = false) long lessonId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getAllParts(lessonId);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/all/v2")
    public ResponseEntity<MessageResponse> getAllPartV2(
        @RequestParam(required = false) Long lessonId,
        @RequestParam(required = false) String partType,
        @RequestParam(required = false) String content
    ) {
        MessageResponse ms = new MessageResponse();
        if (lessonId != null) {
            if (partType == null) {
                ms.data = partService.getPartsByLessonId(lessonId);
            } else {
                ms.data = partService.getPartByLessonIdAndPartTypeAndSearchContent(lessonId, partType, content);
            }
        } else {
            ms.data = partService.getAllPart();
        }
        return ResponseEntity.ok(ms);
    }


    @GetMapping("/{lessonId}")
    public ResponseEntity<MessageResponse> getAllPartsByLessonId(@PathVariable("lessonId") long lessonId) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getPartsByLessonId(lessonId);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/get-by-content")
    public ResponseEntity<MessageResponse> getExactParts(@RequestBody ExactPartReq exactPartReq) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getExactParts(exactPartReq);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/get-by-content/v2")
    public ResponseEntity<MessageResponse> getExactParts(
        @RequestParam(required = true) String content
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.getPartsByContent(content);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/limits-lesson")
    public ResponseEntity<MessageResponse> getAllParts(@RequestBody PartLimitReq partLimitReq) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.partLimits(partLimitReq);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/limits-lesson/v2")
    public ResponseEntity<MessageResponse> getPartsLimits(
        @RequestParam(defaultValue = "1", required = true) int page,
        @RequestParam(defaultValue = "10", required = true) int size,
        @RequestParam(required = true) long lessonId
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.partLimitsLesson(page, size, lessonId);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search")
    public PageDTO<PartRes> getList(@RequestBody SearchPartParamReq searchPartParamReq) {
        return partService.search(searchPartParamReq);
    }

    @GetMapping("/search/v2")
    public ResponseEntity<MessageResponse> getList_v2(
        @RequestParam(defaultValue = "1", required = true) int page,
        @RequestParam(defaultValue = "10", required = true) int size,
        @RequestParam(required = true) String text,
        @RequestParam(required = false) boolean ascending,
        @RequestParam(required = false) String orderBy,
        @RequestParam(required = false) long lessonId

    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = partService.searchV2(page, size, text, ascending, orderBy, lessonId);
        return ResponseEntity.ok(ms);
    }



    @PostMapping("/add-new-lesson")
    public ResponseEntity<MessageResponse> addPartToNewLesson(@RequestBody AddPartToNewLesson addPartToNewLesson) {
        MessageResponse ms = new MessageResponse();
        partService.addPartToNewLesson(addPartToNewLesson);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-vocab-list-to-new-lesson")
    public ResponseEntity<MessageResponse> addPartListToNewLesson(@RequestBody List<AddPartToNewLesson> addPartToNewLessonList) {
        MessageResponse ms = new MessageResponse();
        partService.addPartListToNewLesson(addPartToNewLessonList);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-vocab-list-to-new-lesson/v2")
    public ResponseEntity<MessageResponse> addPartListToNewLesson_v2(@RequestBody AddPartListToNewLesson addPartListToNewLesson) {
        MessageResponse ms = new MessageResponse();
        partService.addPartListToNewLesson_v2(addPartListToNewLesson);
        return ResponseEntity.ok(ms);
    }



    @PutMapping
    public ResponseEntity<MessageResponse> updatePart(@RequestBody UpdatePartReq updatePartReq) {
        MessageResponse ms = new MessageResponse();
        partService.updatePart(updatePartReq);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePartById(@PathVariable("id") long id) {
        MessageResponse ms = new MessageResponse();
        partService.deleteById(id);
        return ResponseEntity.ok(ms);
    }

    @DeleteMapping("/delete-list")
    public ResponseEntity<MessageResponse> deleteParts(@RequestBody DeletePartsReq deletePartsReq) {
        MessageResponse ms = new MessageResponse();
        partService.deleteAllById(deletePartsReq);
        return ResponseEntity.ok(ms);
    }


}
