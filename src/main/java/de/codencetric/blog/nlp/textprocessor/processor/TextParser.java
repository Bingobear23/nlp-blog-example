package de.codencetric.blog.nlp.textprocessor.processor;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TextParser {

    private final LanguageDetectionService languageDetectionService;
    private final AdvancedProcessor advancedProcessor;

    public TextParser(LanguageDetectionService languageDetectionService, AdvancedProcessor tokenizationService) {
        this.languageDetectionService = languageDetectionService;
        this.advancedProcessor = tokenizationService;
    }

    public String processText(String text) throws IOException {
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }

        final String language = languageDetectionService.detect(text).getLanguage();

        return language;
    }


}
