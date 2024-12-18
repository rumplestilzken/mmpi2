package com.rumplestilzken.mmpi.data.scale;

import java.util.List;

public class ASP extends Scale {
    @Override
    public String getDescription() {
        return "Antisocial Practices";
    }

    @Override
    public String toString() {
        return "ASP";
    }


    @Override
    public List<String> getTrueQuestions() {
        List<String> list = super.getTrueQuestions();
        list.add("26");
        list.add("35");
        list.add("66");
        list.add("81");
        list.add("84");
        list.add("104");
        list.add("105");
        list.add("110");
        list.add("123");
        list.add("227");
        list.add("240");
        list.add("248");
        list.add("250");
        list.add("254");
        list.add("269");
        list.add("283");
        list.add("284");
        list.add("374");
        list.add("412");
        list.add("418");
        list.add("419");
        return list;
    }

    @Override
    public List<String> getFalseQuestions() {
        List<String> list = super.getFalseQuestions();
        list.add("266");
        return list;
    }

    @Override
    public List<String> getMaleTScale() {
        List<String> list = super.getMaleTScale();
        list.add("");
        list.add("30");
        list.add("34");
        list.add("37");
        list.add("40");
        list.add("42");
        list.add("44");
        list.add("46");
        list.add("47");
        list.add("49");
        list.add("51");
        list.add("53");
        list.add("55");
        list.add("58");
        list.add("62");
        list.add("65");
        list.add("69");
        list.add("72");
        list.add("76");
        list.add("79");
        list.add("83");
        list.add("87");
        list.add("90");
        list.add("94");
        return list;
    }

    @Override
    public List<String> getFemaleTScale() {
        List<String> list = super.getFemaleTScale();
        list.add("");
        list.add("33");
        list.add("36");
        list.add("39");
        list.add("42");
        list.add("45");
        list.add("47");
        list.add("49");
        list.add("42");
        list.add("54");
        list.add("56");
        list.add("59");
        list.add("63");
        list.add("66");
        list.add("69");
        list.add("72");
        list.add("75");
        list.add("79");
        list.add("82");
        list.add("85");
        list.add("88");
        list.add("91");
        list.add("94");
        list.add("98");
        return list;
    }
}
