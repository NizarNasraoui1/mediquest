package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireDTO;
import com.qonsult.entity.Questionnaire;
import com.qonsult.generic.GenericResource;
import com.qonsult.mapper.QuestionnaireMapper;
import com.qonsult.repository.QuestionnaireRepository;
import com.qonsult.service.QuestionnaireService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/public/questionnaire")
public class QuestionnaireResource extends GenericResource<Questionnaire, QuestionnaireDTO,Long, QuestionnaireRepository, QuestionnaireMapper, QuestionnaireService> {
    public QuestionnaireResource(QuestionnaireService service) {
        super(service);
    }

//    @GetMapping("/{id}")
//    public Mono<QuestionnaireDTO> getQuestionnaireBySpeciality(@PathVariable("id")Long id){
//        return service.findById(id);
//    }
}
