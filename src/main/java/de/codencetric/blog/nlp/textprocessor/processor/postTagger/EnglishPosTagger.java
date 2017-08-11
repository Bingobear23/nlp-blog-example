package de.codencetric.blog.nlp.textprocessor.processor.postTagger;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class EnglishPosTagger implements PostTagger {

    private final POSTaggerME posTaggerME;

    public EnglishPosTagger() {
        posTaggerME = initiatePostTagger();
    }

    @Override
    public String[] processPosTags(String[] text) {
        return posTaggerME.tag(text);
    }

    private POSTaggerME initiatePostTagger() {
        InputStream modelIn = null;

        try {
            modelIn = new ClassPathResource("posTags_models/en-pos-maxent.bin").getInputStream();
            POSModel model = new POSModel(modelIn);
            return new POSTaggerME(model);
        } catch (IOException e) {
            // Model loading failed, handle the error
            e.printStackTrace();
        } finally {
            if (modelIn != null) {
                try {
                    modelIn.close();
                } catch (IOException ignored) {
                }
            }
        }
        throw new IllegalArgumentException("Cannot create postTags for text");
    }

}
