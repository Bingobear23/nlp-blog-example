package de.codencetric.blog.nlp.textprocessor.processor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleProcessorTest {

    private WordStemmer wordStemmer;

    @InjectMocks
    private SimpleProcessor simpleProcessor;

    @Before
    public void setUp() throws Exception {
        final WordStemmer wordStemmer = new WordStemmer();
        simpleProcessor = new SimpleProcessor(wordStemmer);
    }

    @Test
    public void should_tokenize_simple_text_en() throws Exception {
        final List<Word> expected = Arrays.asList(
                new Word("run", "run", "NN"),
                new Word("ran", "ran", "VBD"),
                new Word("bird", "bird", "NN"));

        final List<Word> result = simpleProcessor.processText("run ran bird .","en");

        assertEquals(expected, result);
    }

    @Test
    public void should_tokenize_text_ger() throws Exception {
        final List<Word> expected = Arrays.asList(
                new Word("springt", "springt", "VVFIN"),
                new Word("vogel", "vogel", "NN"));

        expected.get(0).increaseOccurrence();

        final List<Word> result = simpleProcessor.processText("springt springt Vogel und .","de");
        assertEquals(expected, result);
    }
}