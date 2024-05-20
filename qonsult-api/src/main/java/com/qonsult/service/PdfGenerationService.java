package com.qonsult.service;

import reactor.core.publisher.Mono;

public interface PdfGenerationService {
    Mono<byte[]> generatePdfFromHtml(String html);
}
