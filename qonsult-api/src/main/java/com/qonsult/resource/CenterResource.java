package com.qonsult.resource;

import com.qonsult.dto.CenterDTO;
import com.qonsult.entity.Center;
import com.qonsult.generic.GenericResource;
import com.qonsult.mapper.CenterMapper;
import com.qonsult.repository.CenterRepository;
import com.qonsult.service.CenterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("api/center")
public class CenterResource extends GenericResource<Center, CenterDTO,Long, CenterRepository, CenterMapper, CenterService> {
    public CenterResource(CenterService service) {
        super(service);
    }

    @PostMapping("/save")
    public Mono<Center> saveCenter(@RequestBody CenterDTO centerDTO)  throws IOException {
        return this.service.addCenter(centerDTO,null);
    }
}
