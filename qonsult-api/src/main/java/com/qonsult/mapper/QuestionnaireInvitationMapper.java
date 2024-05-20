package com.qonsult.mapper;

import com.qonsult.dto.QuestionnaireInvitationDTO;
import com.qonsult.entity.QuestionnaireInvitation;
import com.qonsult.generic.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireInvitationMapper extends GenericMapper<QuestionnaireInvitation, QuestionnaireInvitationDTO> {
}
