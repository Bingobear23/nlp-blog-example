package de.codencetric.blog.nlp.textprocessor.processor;

import de.codencetric.blog.nlp.textprocessor.processor.postTagger.EnglishPosTagger;
import de.codencetric.blog.nlp.textprocessor.processor.postTagger.GermanPosTagger;
import opennlp.tools.tokenize.SimpleTokenizer;

import java.util.*;
import java.util.stream.Collectors;

import static de.codencetric.blog.nlp.textprocessor.processor.NLPConst.ENGLISH;
import static de.codencetric.blog.nlp.textprocessor.processor.NLPConst.GERMAN;
import static de.codencetric.blog.nlp.textprocessor.processor.NLPConst.WORD_TYPES;

public class SimpleProcessor {

    private String language = "en";
    private final WordStemmer wordStemmer;

    public SimpleProcessor(WordStemmer wordStemmer) {
        this.wordStemmer = wordStemmer;
    }

    public List<Word> processTextWithStemming(String text, String language) {
        this.language = language;
        final List<Word> uniqueWords = createUniqueWordsWithStemming(text);

        return filterWordTypes(uniqueWords);
    }

    private List<Word> createWords(String text) {
        final String[] tokenArray = SimpleTokenizer.INSTANCE.tokenize(text);
        final String[] postTags = retrievePostTags(tokenArray);
        final List<String> wordStems = retrieveWordStems(tokenArray);

        final List<Word> words = new ArrayList<>();
        for (int counter = 0; counter < wordStems.size(); counter++) {
            words.add(new Word(tokenArray[counter], wordStems.get(counter), postTags[counter]));
        }

        return words;
    }

    private Map<String, List<Word>> groupWordsByStem(String text) {
        return filterWordTypes(createWords(text)).stream()
                .collect(Collectors.groupingBy(Word::getBasic));
    }

    private List<Word> createUniqueWordsWithStemming(String text) {
        final String[] tokenArray = SimpleTokenizer.INSTANCE.tokenize(text);
        final String[] postTags = retrievePostTags(tokenArray);
        final List<String> wordStems = retrieveWordStems(tokenArray);

        final List<Word> words = new ArrayList<>();
        for (int counter = 0; counter < wordStems.size(); counter++) {
            final Word currentWord = new Word(tokenArray[counter], wordStems.get(counter), postTags[counter]);
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

    private List<String> retrieveWordStems(String[] tokenArray) {
        return wordStemmer.stem(Arrays.asList(tokenArray));
    }

}
