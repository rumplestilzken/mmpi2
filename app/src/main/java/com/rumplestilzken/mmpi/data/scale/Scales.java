package com.rumplestilzken.mmpi.data.scale;

import java.util.ArrayList;
import java.util.List;

public class Scales {
    public static List<Scale> getScales() {
        List<Scale> scales = new ArrayList<>();
        scales.add(new True());
        scales.add(new False());
        scales.add(new QuestionScale());
        scales.add(new VRIN());
        scales.add(new TRIN());
        scales.add(new F());
        scales.add(new Fb());
        scales.add(new Fp());
        scales.add(new L());
        scales.add(new K());
        scales.add(new S());
        scales.add(new Hs());
        scales.add(new D());
        scales.add(new Hy());
        scales.add(new Pd());
        scales.add(new Mf_Male());
        scales.add(new Mf_Female());
        scales.add(new Pa());
        scales.add(new Pt());
        scales.add(new Sc());
        scales.add(new Ma());
        scales.add(new Si());
        scales.add(new D1());
        scales.add(new D2());
        scales.add(new D3());
        scales.add(new D4());
        scales.add(new D5());
        scales.add(new Hy1());
        scales.add(new Hy2());
        scales.add(new Hy3());
        scales.add(new Hy4());
        scales.add(new Hy5());
        scales.add(new Pd1());
        scales.add(new Pd2());
        scales.add(new Pd3());
        scales.add(new Pd4());
        scales.add(new Pd5());
        scales.add(new Pa1());
        scales.add(new Pa2());
        scales.add(new Pa3());
        scales.add(new Sc1());
        scales.add(new Sc2());
        scales.add(new Sc3());
        scales.add(new Sc4());
        scales.add(new Sc5());
        scales.add(new Sc6());
        scales.add(new Ma1());
        scales.add(new Ma2());
        scales.add(new Ma3());
        scales.add(new Ma4());
        scales.add(new Si1());
        scales.add(new Si2());
        scales.add(new Si3());
        scales.add(new ANX());
        scales.add(new FRS());
        scales.add(new OBS());
        scales.add(new DEP());
        scales.add(new HEA());
        scales.add(new BIZ());
        scales.add(new ANG());
        scales.add(new CYN());
        scales.add(new ASP());
        scales.add(new TPA());
        scales.add(new LSE());
        scales.add(new FAM());
        scales.add(new WRK());
        scales.add(new TRT());
        scales.add(new A());
        scales.add(new R());
        scales.add(new Es());
        scales.add(new MAC_R());
        scales.add(new AAS());
        scales.add(new APS());
        scales.add(new MDS());
        scales.add(new Ho());
        scales.add(new O_H());
        scales.add(new Do());
        scales.add(new Re());
        scales.add(new Mt());
        scales.add(new GM());
        scales.add(new GF());
        scales.add(new PK());
        scales.add(new PS());
        scales.add(new D_O());
        scales.add(new D_S());
        scales.add(new Hy_O());
        scales.add(new Hy_S());
        scales.add(new Pd_O());
        scales.add(new Pd_S());
        scales.add(new Pa_O());
        scales.add(new Pa_S());
        scales.add(new Ma_O());
        scales.add(new Ma_S());
        scales.add(new dem());
        scales.add(new som());
        scales.add(new lpe());
        scales.add(new cyn());
        scales.add(new asb());
        scales.add(new per());
        scales.add(new dne());
        scales.add(new abx());
        scales.add(new hpm());
        scales.add(new AGGR());
        scales.add(new PSYC());
        scales.add(new DISC());
        scales.add(new NEGE());
        scales.add(new INTR());
        scales.add(new FRS1());
        scales.add(new FRS2());
        scales.add(new DEP1());
        scales.add(new DEP2());
        scales.add(new DEP3());
        scales.add(new DEP4());
        scales.add(new HEA1());
        scales.add(new HEA2());
        scales.add(new HEA3());
        scales.add(new BIZ1());
        scales.add(new BIZ2());
        scales.add(new ANG1());
        scales.add(new ANG2());
        scales.add(new CYN1());
        scales.add(new CYN2());
        scales.add(new ASP1());
        scales.add(new ASP2());
        scales.add(new TPA1());
        scales.add(new TPA2());
        scales.add(new LSE1());
        scales.add(new LSE2());
        scales.add(new SOD1());
        scales.add(new SOD2());
        scales.add(new FAM1());
        scales.add(new FAM2());
        scales.add(new TRT1());
        scales.add(new TRT2());
        return scales;
    }

    public static List<CriticalScale> getCriticalScales() {
        List<CriticalScale> list = new ArrayList<>();
        list.add(new KB1());
        list.add(new KB2());
        list.add(new KB3());
        list.add(new KB4());
        list.add(new KB5());
        list.add(new KB6());
        list.add(new LW1());
        list.add(new LW2());
        list.add(new LW3());
        list.add(new LW4());
        list.add(new LW5());
        list.add(new LW6());
        list.add(new LW7());
        list.add(new LW8());
        list.add(new LW9());
        list.add(new LW10());
        list.add(new LW11());
        return list;
    }
}