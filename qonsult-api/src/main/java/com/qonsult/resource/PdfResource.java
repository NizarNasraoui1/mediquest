package com.qonsult.resource;

import com.qonsult.service.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/pdf")
@RequiredArgsConstructor
public class PdfResource {

    private final PdfGenerationService pdfGenerationService;
    @PostMapping("/generate")
    public Mono<byte[]> generatePdf(@RequestBody String htmlContent) {
        return pdfGenerationService.generatePdfFromHtml(htmlContent);
    }
}
