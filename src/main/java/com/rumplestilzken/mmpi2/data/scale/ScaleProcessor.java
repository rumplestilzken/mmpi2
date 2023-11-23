package com.rumplestilzken.mmpi2.data.scale;

import com.rumplestilzken.mmpi2.data.QuestionData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ScaleProcessor {

    Map<Integer, Boolean> answerMap = new HashMap<>();
    String[] ignoreList = { "True", "False", "?" };

    public double process(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList, List<CriticalScale> criticalScales,  boolean isMale) {
        answers.forEach(i -> answerMap.put(i.getIndex(), i.getAnswer()));

        for(int i = 0; i < scaleList.size(); i++) {
            scaleList.get(i).setIndex(i);
        }

        processTrueScale(answers, scaleList);
        processFalseScale(answers, scaleList);
        processQuestionScale(answers, scaleList);
        processVRIN(answers, scaleList);
        processTRIN(answers, scaleList);

        scaleList.stream().filter(i -> !List.of(ignoreList).contains(i.toString())).forEach(i -> processRawScore(i.getClass(), answers, scaleList));

        processTScores(answers, scaleList, isMale);

        processCritical(answers, criticalScales);
        
        List<String> peScalesList = new ArrayList<>();
        peScalesList.add("Hs");
        peScalesList.add("D");
        peScalesList.add("Hy");
        peScalesList.add("Pd");
        peScalesList.add("Pa");
        peScalesList.add("Pt");
        peScalesList.add("Sc");
        peScalesList.add("Ma");

        double peScale = 0;
        List<Scale> peScales = scaleList.stream().filter(i -> peScalesList.contains(i.toString())).toList();
        for (Scale currentScale : peScales) {
            double tmp = 0;
            try {
                tmp = Double.parseDouble(currentScale.gettScore().isEmpty() ? "0" : currentScale.gettScore());;
            }
            catch (Exception e)
            {
                //TODO:
            }
            peScale += tmp;
        }
        return peScale/8;
    }

    public void processCritical(List<QuestionData.QuestionAnswerData> answers, List<CriticalScale> criticalScales) {
        criticalScales.forEach(currentScale -> {
            List<String> trueValues = currentScale.getTrueQuestions().stream().filter(currentQuestion -> answers.stream().filter(i -> i.getIndex() == Integer.parseInt(currentQuestion) && i.getAnswer() == Boolean.TRUE).count() > 0).toList();
            List<String> falseValues = currentScale.getFalseQuestions().stream().filter(currentQuestion -> answers.stream().filter(i -> i.getIndex() == Integer.parseInt(currentQuestion) && i.getAnswer() == Boolean.FALSE).count() > 0).toList();
            boolean t = trueValues.stream().count() > 0;
            boolean f =  falseValues.stream().count() > 0;
            if(t || f) {
                currentScale.getTrueValues().addAll(trueValues);
                currentScale.getFalseValues().addAll(falseValues);
                currentScale.setVisible(true);
            }
        });
    }

    private String getTScore(K k, List<String> tScale, Scale currentScale)
    {
        if(tScale.size() == 0)
        {
            return "undefined";
        }

        if(currentScale instanceof VRIN || currentScale instanceof TRIN)
        {
            return tScale.get(Math.toIntExact(currentScale.rawScore));
        }

        if(tScale.get(0).equals(""))
        {
            if(tScale.get((int)currentScale.rawScore+1).isEmpty())
            {
                return "undefined";
            }
            return tScale.get(Math.toIntExact(currentScale.rawScore + 1));
        }

        long kValue = k.rawScore;
        double kScore = kValue*Double.parseDouble(tScale.get(0))+currentScale.rawScore;
        double actualKScore = Math.floor(kScore+.5);
        int tScore = Integer.parseInt(tScale.get((int)actualKScore + 1));

        currentScale.setkScore((long)actualKScore);

        return Integer.toString(tScore);
    }

    private void processTScores(List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList, boolean isMale) {
        K kScale = (K) scaleList.stream().filter(i -> i instanceof K).findFirst().get();
        scaleList.forEach(currentScale -> {

//            for (String currentIndex: currentScale.getTrueQuestions()) {
//                if(!kScale.getTrueQuestions().contains(currentIndex))
//                {
//                    continue;
//                }
//                currentScale.settScore(getTScore(kScale, isMale ? currentScale.getMaleTScale() : currentScale.getFemaleTScale(), currentScale));
//            }
//
//            for (String currentIndex: currentScale.getFalseQuestions()) {
//                if(!kScale.getFalseQuestions().contains(currentIndex))
//                {
//                    continue;
//                }
//                currentScale.settScore(getTScore(kScale, isMale ? currentScale.getMaleTScale() : currentScale.getFemaleTScale(), currentScale));
//            }

            currentScale.settScore(getTScore(kScale, isMale ? currentScale.getMaleTScale() : currentScale.getFemaleTScale(), currentScale));
        });

    }

    private void processRawScore(Class clazz, List<QuestionData.QuestionAnswerData> answers, List<Scale> scaleList) {
        Scale currentScale = (Scale) scaleList.stream().filter(clazz::isInstance).findFirst().get();
        for (String currentIndex: currentScale.getTrueQuestions()) {
            Boolean bool = answerMap.get(Integer.parseInt(currentIndex));
            if(bool != null && bool) {
                currentScale.incrementRawScore();
            }
        }
        for (String currentIndex: currentScale.getFalseQuestions()) {
            Boolean bool = answerMap.get(Integer.parseInt(currentIndex));
            if(bool != null && !bool) {
                currentScale.incrementRawScore();
            }
        }
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
