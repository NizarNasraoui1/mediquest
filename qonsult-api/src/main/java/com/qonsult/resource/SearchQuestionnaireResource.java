package com.qonsult.resource;

import com.qonsult.dto.PaginatedResponse;
import com.qonsult.dto.SearchQuestionnaireDTO;
import com.qonsult.service.SearchQuestionnaireResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/historic")
@RequiredArgsConstructor
public class SearchQuestionnaireResource {
    private final SearchQuestionnaireResponseService searchQuestionnaireResponseService;

    @PostMapping("/questionnaires")
    public ResponseEntity<PaginatedResponse<SearchQuestionnaireDTO>>searchQuestionnaireReponse(
            @RequestParam(value = "page",required = false,defaultValue = "0") String page,
            @RequestParam(value = "pageSize",required = false,defaultValue = "0") String pageSize,
            @RequestBody SearchQuestionnaireDTO searchQuestionnaireDTO
    ){
        return new ResponseEntity<>(searchQuestionnaireResponseService.searchQuestionnaireReponse(Integer.parseInt(page),Integer.parseInt(pageSize),searchQuestionnaireDTO), HttpStatus.OK);
    }
}
