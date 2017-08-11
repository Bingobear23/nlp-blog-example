package de.codencetric.blog.nlp.textprocessor.processor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TextProcessorController {

    private final LanguageDetectionService languageDetectionService;
    private final AdvancedProcessor advancedProcessor;
    private final SimpleProcessor simpleProcessor;
    private final Tokenizer tokenizer;


    public TextProcessorController(LanguageDetectionService languageDetectionService,
                                   AdvancedProcessor tokenizationService,
                                   SimpleProcessor simpleProcessor,
                                   Tokenizer tokenizer) {
        this.languageDetectionService = languageDetectionService;
        this.advancedProcessor = tokenizationService;
        this.simpleProcessor = simpleProcessor;
        this.tokenizer = tokenizer;
    }

    @GetMapping("/processTextBasic")
    public ResponseEntity<List<String>> extractWords(@RequestBody  String text) {
        return ResponseEntity.ok(tokenizer.tokenizeText(text));
    }

    @GetMapping("/processTextSimple")
    public ResponseEntity<List<Word>> extractRelevantWordsStem(@RequestBody String text) {
        return ResponseEntity.ok(processText(text, false));
    }

    @GetMapping("/processTextAdvanced")
    public ResponseEntity<List<Word>> extractRelevantWordsLem(@RequestBody String text) {
        return ResponseEntity.ok(processText(text, true));
    }

    public List<Word> processText(String text, boolean advanced) {
        if (text.isEmpty()) {
            throw new IllegalArgumentException();
        }

        final String language = languageDetectionService.detect(text).getLanguage();

        if (advanced) {
            return advancedProcessor.processText(text, language);
        }
        return simpleProcessor.processText(text, language);
    }

}
