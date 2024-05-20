package com.qonsult.service.impl;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.qonsult.service.PdfGenerationService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {
    @Override
    public Mono<byte[]> generatePdfFromHtml(String html) {
        return Mono.fromCallable(() -> {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocumentFromString(convertHtmlToXhtml(html));
                renderer.layout();
                renderer.createPDF(outputStream);
                return outputStream.toByteArray();
            } catch (Exception e) {
                throw new RuntimeException("Error in PDF generation", e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }


    public String convertHtmlToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }
}
