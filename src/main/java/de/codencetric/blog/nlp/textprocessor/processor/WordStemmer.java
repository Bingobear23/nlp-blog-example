package de.codencetric.blog.nlp.textprocessor.processor;

import org.springframework.stereotype.Component;
import org.tartarus.snowball.ext.englishStemmer;
import org.tartarus.snowball.ext.germanStemmer;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WordStemmer {

    /**
     * generates stemmed version of a given array
     *
     * @param tokens
     * @return stemmed array
     */
    public List<String> stem(List<String> tokens) {
            return tokens.stream()
                    .map(this::getEnglishWordStem)
                    .collect(Collectors.toList());
    }

    private String getEnglishWordStem(String token) {
        englishStemmer stemmer = new englishStemmer();
        stemmer.setCurrent(token);
        stemmer.stem();
        return stemmer.getCurrent();
    }

    private String getGermanWordStem(String token) {
        germanStemmer stemmer = new germanStemmer();
        stemmer.setCurrent(token);
        stemmer.stem();
        return stemmer.getCurrent();
    }

}
