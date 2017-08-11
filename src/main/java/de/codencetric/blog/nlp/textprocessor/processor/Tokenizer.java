package de.codencetric.blog.nlp.textprocessor.processor;

import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Tokenizer {

    public List<String> tokenizeText(String text) {
        return Arrays.asList(SimpleTokenizer.INSTANCE.tokenize(text));
    }

}
