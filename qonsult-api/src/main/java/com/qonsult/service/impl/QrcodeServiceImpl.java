package com.qonsult.service.impl;

import com.qonsult.config.tenant_config.TenantContext;
import com.qonsult.dto.QrCodeDTO;
import com.qonsult.entity.QuestionnaireRequest;
import com.qonsult.repository.QuestionnaireRequestRepository;
import com.qonsult.service.QrcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QrcodeServiceImpl implements QrcodeService {
    private final QuestionnaireRequestRepository questionnaireRequestRepository;

    @Value("${questionnaire-fe-link}")
    private String consentLink;

    @Override
    public List<QrCodeDTO> getAllQrCodes() {
        return questionnaireRequestRepository.findAllByUsedForQrCodeTrue().stream().map((questionnaireRequest -> {
            QrCodeDTO codeDTO = new QrCodeDTO();
            codeDTO.setUrl(getConsentRequestUrl(questionnaireRequest));
            codeDTO.setName(questionnaireRequest.getQuestionnaireModel().getName());
            return codeDTO;
        })).collect(Collectors.toList());
    }

    public String getConsentRequestUrl(QuestionnaireRequest questionnaireRequest){
        String schema = TenantContext.getCurrentTenant()==null?"public":TenantContext.getCurrentTenant();
        return new StringBuilder(consentLink).append(questionnaireRequest.getId()).append("?schema=").append(schema).toString();
    }
}
