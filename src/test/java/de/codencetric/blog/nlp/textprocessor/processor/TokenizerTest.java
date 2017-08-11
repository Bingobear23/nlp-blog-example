package de.codencetric.blog.nlp.textprocessor.processor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {

    @Test
    public void should_return_text_as_tokens() throws Exception {
        final Tokenizer tokenizer = new Tokenizer();
        final List<String> expected = Arrays.asList("This", "is", "a", "good", "day", "to", "code", ".");

        final List<String> result = tokenizer.tokenizeText("This is a good day to code.");
        assertEquals(expected, result);
    }
}