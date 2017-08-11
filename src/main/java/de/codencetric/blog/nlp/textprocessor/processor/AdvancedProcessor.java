package de.codencetric.blog.nlp.textprocessor.processor;

import de.codencetric.blog.nlp.textprocessor.processor.lemmatizer.EnglishLemmatizer;
import de.codencetric.blog.nlp.textprocessor.processor.postTagger.EnglishPosTagger;
import de.codencetric.blog.nlp.textprocessor.processor.postTagger.GermanPosTagger;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.codencetric.blog.nlp.textprocessor.processor.NLPConst.*;

@Component
public class AdvancedProcessor {

    private String language = "en";

    private final EnglishLemmatizer lemmatizer;

    public AdvancedProcessor(EnglishLemmatizer lemmatizer) {
        this.lemmatizer = lemmatizer;
    }

    public List<Word> processTextWithLemmatizer(String text, String language) {
        this.language = language;
        final List<Word> uniqueWords = createUniqueWordsWithLemmatisation(text);

        return filterWordTypes(uniqueWords);
    }

    private List<Word> createUniqueWordsWithLemmatisation(String text) {
        final String[] tokenArray = SimpleTokenizer.INSTANCE.tokenize(text);
        final String[] postTags = retrievePostTags(tokenArray);
        final String[] basicWords = lemmatizer.lemmatize(tokenArray, postTags);

        final List<Word> words = new ArrayList<>();
        for (int counter = 0; counter < basicWords.length; counter++) {
            final Word currentWord = new Word(tokenArray[counter], basicWords[counter], postTags[counter]);
            final Optional<Word> duplicateWord = words.stream().filter(word -> word.isSameWord(currentWord)).findFirst();
            if (duplicateWord.isPresent()) {
                duplicateWord.get().increaseOccurrence();
            } else {
                words.add(currentWord);
            }
        }
        return words;
    }

    private List<Word> filterWordTypes(List<Word> wordList) {
        return wordList.stream()
                .filter(word -> WORD_TYPES.stream()
                        .anyMatch(type -> word.getType().contains(type)))
                .collect(Collectors.toList());
    }

    private String[] retrievePostTags(String[] tokenArray) {
        if (language.equals(GERMAN)) {
            final GermanPosTagger germanPosTagger = new GermanPosTagger();
            return germanPosTagger.processPosTags(tokenArray);
        } else if (language.equals(ENGLISH)) {
            final EnglishPosTagger englishPosTagger = new EnglishPosTagger();
            return englishPosTagger.processPosTags(tokenArray);

        }
        throw new IllegalArgumentException();
    }

}

