package com.qonsult.resource;

import com.qonsult.dto.QuestionnaireModelDTO;
import com.qonsult.entity.QuestionnaireModel;
import com.qonsult.generic.GenericResource;
import com.qonsult.mapper.QuestionnaireMapper;
import com.qonsult.repository.QuestionnaireRepository;
import com.qonsult.service.QuestionnaireService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/questionnaire")
public class QuestionnaireResource extends GenericResource<QuestionnaireModel, QuestionnaireModelDTO,Long, QuestionnaireRepository, QuestionnaireMapper, QuestionnaireService> {
    public QuestionnaireResource(QuestionnaireService service) {
        super(service);
    }

//    @GetMapping("/{id}")
//    public Mono<QuestionnaireDTO> getQuestionnaireBySpeciality(@PathVariable("id")Long id){
//        return service.findById(id);
//    }
}
