package com.rumplestilzken.mmpi2.data.scale;

import com.rumplestilzken.mmpi2.data.QuestionData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScaleProcessor {

    Map<Integer, Boolean> answerMap = new HashMap<>();

    public void process(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList, boolean isMale) {
        answers.forEach(i -> answerMap.put(i.getIndex(), i.getAnswer()));

        processTrueScale(answers, scaleList);
        processFalseScale(answers, scaleList);
        processQuestionScale(answers, scaleList);
        processVRIN(answers, scaleList);
        processTRIN(answers, scaleList);

        processCommon(K.class, answers, scaleList, isMale);

        processCommon(F.class, answers, scaleList, isMale);
    }

    private void processCommon(Class clazz, List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList, boolean isMale) {
        Scale currentScale = (Scale) scaleList.stream().filter(clazz::isInstance).findFirst().get();
        System.out.println(currentScale.toString());
        for (String currentIndex: currentScale.getTrueQuestions()) {
            QuestionData.QuestionAnswerData currentQAD = answers.stream().filter(i -> i.getIndex() == Integer.parseInt(currentIndex)).findFirst().get();
            if(currentQAD.getAnswer() != null && currentQAD.getAnswer()) {
                currentScale.incrementRawScore();
            }
        }
        for (String currentIndex: currentScale.getFalseQuestions()) {
            QuestionData.QuestionAnswerData currentQAD = answers.stream().filter(i -> i.getIndex() == Integer.parseInt(currentIndex)).findFirst().get();
            if(currentQAD.getAnswer() != null && !currentQAD.getAnswer()) {
                currentScale.incrementRawScore();
            }
        }

        List<String> tScale = null;
        if(isMale) {
            tScale = currentScale.getMaleTScale();
        }
        else {
            tScale = currentScale.getFemaleTScale();
        }

        currentScale.settScore(tScale.get(Integer.parseInt(String.valueOf(currentScale.rawScore+1))));


    }


    private void processTRIN(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList) {
        TRIN trin = (TRIN) scaleList.stream().filter(i -> i instanceof TRIN).findFirst().get();
        long baseScore = trin.getRawScore();
        List<RINPair> pairs = TRIN.getTRINPairs();
        for (RINPair currentPair: pairs) {
            Boolean answer1 = answers.stream().filter(i -> i.getIndex() == currentPair.getIndex1()).findFirst().get().getAnswer();
            Boolean answer2 = answers.stream().filter(i -> i.getIndex() == currentPair.getIndex2()).findFirst().get().getAnswer();
//            System.out.println("Index1:" + currentPair.index1 + ",Answer1:" + answer1 + ",Index2:" + currentPair.getIndex2() + ",Answer2:" + answer2);
            if(answer1 == currentPair.isBool1() && answer2 == currentPair.isBool2())
            {
                baseScore += currentPair.getValue();
            }
        }
        trin.setRawScore(baseScore);
    }

    private void processVRIN(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList) {
        VRIN vrin = (VRIN) scaleList.stream().filter(i -> i instanceof VRIN).findFirst().get();
        long baseScore = vrin.getRawScore();
        List<RINPair> pairs = VRIN.getVRINPairs();
        for (RINPair currentPair: pairs) {
            Boolean answer1 = answers.stream().filter(i -> i.getIndex() == currentPair.getIndex1()).findFirst().get().getAnswer();
            Boolean answer2 = answers.stream().filter(i -> i.getIndex() == currentPair.getIndex2()).findFirst().get().getAnswer();
//            System.out.println("Index1:" + currentPair.index1 + ",Answer1:" + answer1 + ",Index2:" + currentPair.getIndex2() + ",Answer2:" + answer2);
            if(answer1 == currentPair.isBool1() && answer2 == currentPair.isBool2())
            {
                baseScore += currentPair.getValue();
            }
        }
        vrin.setRawScore(baseScore);
    }

    private void processTrueScale(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList) {
        scaleList.stream().filter(i -> i instanceof True).findFirst().get().rawScore = answers.stream().filter(i -> i.getAnswer() == Boolean.TRUE).count();
    }

    private void processFalseScale(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList) {
        scaleList.stream().filter(i -> i instanceof False).findFirst().get().rawScore = answers.stream().filter(i -> i.getAnswer() == Boolean.FALSE).count();
    }

    private void processQuestionScale(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList) {
        scaleList.stream().filter(i -> i instanceof QuestionScale).findFirst().get().rawScore = answers.stream().filter(i -> i.getAnswer() == null).count();
    }
}
