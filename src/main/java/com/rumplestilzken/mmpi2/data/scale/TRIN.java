package com.rumplestilzken.mmpi2.data.scale;

import java.util.ArrayList;
import java.util.List;

public class TRIN extends Scale {

    public TRIN() {
        setRawScore(9);
    }

    @Override
    public String getDescription() {
        return "True Response Inconsistency";
    }

    @Override
    public String toString() {
        return "TRIN";
    }

    public static List<RINPair> getTRINPairs() {
        List<RINPair> pairs = new ArrayList<>();
        pairs.add(new RINPair(3,true,39,true,1));
        pairs.add(new RINPair(12,true,166,true,1));
        pairs.add(new RINPair(40,true,176,true,1));
        pairs.add(new RINPair(48,true,184,true,1));
        pairs.add(new RINPair(63,true,27,true,1));
        pairs.add(new RINPair(65,true,95,true,1));
        pairs.add(new RINPair(73,true,239,true,1));
        pairs.add(new RINPair(83,true,288,true,1));
        pairs.add(new RINPair(99,true,314,true,1));
        pairs.add(new RINPair(125,true,195,true,1));
        pairs.add(new RINPair(209,true,351,true,1));
        pairs.add(new RINPair(359,true,367,true,1));
        pairs.add(new RINPair(377,true,534,true,1));
        pairs.add(new RINPair(556,true,560,true,1));
        pairs.add(new RINPair(9,false,56,false,-1));
        pairs.add(new RINPair(65,false,95,false,-1));
        pairs.add(new RINPair(125,false,195,false,-1));
        pairs.add(new RINPair(140,false,196,false,-1));
        pairs.add(new RINPair(152,false,464,false,-1));
        pairs.add(new RINPair(265,false,360,false,-1));
        pairs.add(new RINPair(359,false,367,false,-1));
        return pairs;
    }
}
