package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireAnswerDTO;
import com.qonsult.entity.QuestionnaireAnswer;
import com.qonsult.generic.GenericResource;
import com.qonsult.mapper.QuestionnaireAnswerMapper;
import com.qonsult.repository.QuestionnaireAnswerRepository;
import com.qonsult.service.QuestionnaireAnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/questionnaire-answer")
public class QuestionnaireAnswerResource extends GenericResource<QuestionnaireAnswer, QuestionnaireAnswerDTO,Long, QuestionnaireAnswerRepository, QuestionnaireAnswerMapper, QuestionnaireAnswerService> {
    public QuestionnaireAnswerResource(QuestionnaireAnswerService service) {
        super(service);
    }
}
