package com.rumplestilzken.mmpi2.data.scale;

import java.util.ArrayList;
import java.util.List;

public class Scale {
    public Scale() {
        settScore("undefined");
        setRawScore(0);
    }

    long rawScore = -1;
    long kScore = -1;

    String tScore = "";

    public long getRawScore() {
        return rawScore;
    }

    public void incrementRawScore() {
        rawScore++;
    }

    public void setRawScore(long rawScore) {
        this.rawScore = rawScore;
    }

    public long getkScore() {
        return kScore;
    }

    public void setkScore(long kScore) {
        this.kScore = kScore;
    }

    public String gettScore() {
        return tScore;
    }

    public void settScore(String tScore) {
        this.tScore = tScore;
    }

    public String getDescription() {
        return "Scale Description";
    }
    @Override
    public String toString() {
        return "SCALE";
    }

    public List<String> getMaleTScale() {
        List<String> tScale = new ArrayList<>();
        return tScale;
    }

    public List<String> getFemaleTScale() {
        List<String> tScale = new ArrayList<>();
        return tScale;
    }

    public List<String> getTrueQuestions() {
        List<String> trueQuestions = new ArrayList<>();
        return trueQuestions;
    }

    public List<String> getFalseQuestions() {
        List<String> trueQuestions = new ArrayList<>();
        return trueQuestions;
    }
}
