package de.codencetric.blog.nlp.textprocessor.processor;

import de.codencetric.blog.nlp.textprocessor.processor.lemmatizer.EnglishLemmatizer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AdvancedProcessorTest {

    private AdvancedProcessor advancedProcessor;

    @Before
    public void setUp() throws Exception {
        final EnglishLemmatizer englishLemmatizer = new EnglishLemmatizer();
        advancedProcessor = new AdvancedProcessor(englishLemmatizer);
    }

    @Test
    public void should_tokenize_complex_text_en() throws Exception {
        final List<Word> expected = Arrays.asList(
                new Word("run", "run", "NN"),
                new Word("bird", "bird", "NN"));

        expected.get(0).increaseOccurrence();
        final List<Word> result = advancedProcessor.processText("run ran bird .","en");

        assertEquals(expected, result);
    }

}