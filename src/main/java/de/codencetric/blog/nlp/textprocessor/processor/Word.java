package de.codencetric.blog.nlp.textprocessor.processor;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
public class Word {

    @Getter
    @Setter
    private final String text;

    @Getter
    @Setter
    private final String basic;

    @Getter
    @Setter
    private final String type;

    @Getter
    @NonFinal
    private int occurrence = 1;

    public Word(String text, String basic, String type) {
        this.text = text.toLowerCase();
        this.basic = basic.toLowerCase();
        this.type = type;
    }

    public void increaseOccurrence() {
        this.occurrence++;
    }

    public boolean isSameWord(Word other) {
        return this.basic.equals(other.getBasic());
    }
}
