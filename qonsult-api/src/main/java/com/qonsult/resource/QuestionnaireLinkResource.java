//package com.qonsult.resource;
//
//import com.qonsult.dto.QuestionnaireLinkDTO;
//import com.qonsult.service.QuestionnaireLinkService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/questionnaire-link")
//@RequiredArgsConstructor
//public class QuestionnaireLinkResource {
//
//    private final QuestionnaireLinkService questionnaireLinkService;
//
//    @GetMapping("/all")
//    public ResponseEntity<List<QuestionnaireLinkDTO>> getAllQuestionnaireLinks(){
//        return new ResponseEntity<>(questionnaireLinkService.getAllStoreduestionnaireLinks(), HttpStatus.OK);
//    }
//}
