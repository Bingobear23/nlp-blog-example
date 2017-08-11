package de.codencetric.blog.nlp.textprocessor.processor.lemmatizer;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class EnglishLemmatizer {

    private final DictionaryLemmatizer dictionaryLemmatizer;

    public EnglishLemmatizer() {
        dictionaryLemmatizer = initiateLemmatizer();
    }

    public String[] lemmatize(String[] tokens, String[] posTags) {
        return dictionaryLemmatizer.lemmatize(tokens, posTags);
    }

    private DictionaryLemmatizer initiateLemmatizer() {
        try {
            InputStream dictLemmatizer = new ClassPathResource("lemmatizer_dict/en-lemmatizer.dict").getInputStream();
            return new DictionaryLemmatizer(dictLemmatizer);
        } catch (IOException e) {
            // dictionary loading failed, handle the error
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

}
