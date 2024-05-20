package com.qonsult.mapper;

import com.qonsult.dto.PatientInformationDTO;
import com.qonsult.entity.PatientInformation;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientInformationMapper extends GenericMapper<PatientInformation, PatientInformationDTO> {
}
