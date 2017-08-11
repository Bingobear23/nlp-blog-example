package de.codencetric.blog.nlp.textprocessor.processor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageDetectionServiceTest {

    private LanguageDetectionService languageDetectionService = new LanguageDetectionService();

    @Test
    public void should_detect_language_for_en_text() throws Exception {
        final String detectedLanguage = languageDetectionService.detect("I want to know if I need a model to recognize my language!").getLanguage();

        assertEquals(detectedLanguage, "en");
    }

    @Test
    public void should_detect_language_for_ger_text() throws Exception {
        final String detectedLanguage = languageDetectionService.detect("Weit weit hinter den Bergen, wo es immer noch schneit.").getLanguage();

        assertEquals(detectedLanguage, "de");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_detect_language_for_text_when_unknown() throws Exception {
        final String detectedLanguage = languageDetectionService.detect("afdhadfhadfhfha").getLanguage();

        assertEquals(detectedLanguage, "en");
    }
}