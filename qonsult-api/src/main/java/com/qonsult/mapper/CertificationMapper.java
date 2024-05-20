package com.qonsult.mapper;

import com.qonsult.dto.CertificationDTO;
import com.qonsult.entity.Certification;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificationMapper extends GenericMapper<Certification, CertificationDTO> {
}
