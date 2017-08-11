package de.codencetric.blog.nlp.textprocessor.processor.postTagger;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class EnglishPosTaggerTest {

    @Test
    public void should_return_postTags() throws Exception {
        final EnglishPosTagger englishPosTagger = new EnglishPosTagger();

        String[] testTokens = {"test", "this", "guy", ".", "run"};
        String[] testTokensPostTags = {"NN", "DT", "NN", ".", "VB"};

        assertArrayEquals(testTokensPostTags, englishPosTagger.processPosTags(testTokens));
    }
}