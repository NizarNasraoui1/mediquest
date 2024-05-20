package com.qonsult.service;

import com.qonsult.dto.PaginatedResponse;
import com.qonsult.dto.SearchQuestionnaireDTO;

public interface SearchQuestionnaireResponseService {
    PaginatedResponse<SearchQuestionnaireDTO> searchQuestionnaireReponse(int page, int pageSize, SearchQuestionnaireDTO searchQuestionnaireDTO);
}
