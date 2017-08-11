package de.codencetric.blog.nlp.textprocessor.processor;

import java.util.List;

public interface Processor {
    List<Word> processText(String text, String language);
    List<Word> extractDistinctWords(String text);
}
