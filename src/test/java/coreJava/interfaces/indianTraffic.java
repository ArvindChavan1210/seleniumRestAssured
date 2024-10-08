package coreJava.interfaces;

public class indianTraffic implements worldTrafficRules, asianTrafficRules{
    @Override
    public void goGreen() {
        System.out.println("India agrees with 'GO' on Green Signal ");
    }

    @Override
    public void watchYellow() {
        System.out.println("India agrees with 'Watch' on Yellow Signal ");
    }

    @Override
    public void stopRed() {
        System.out.println("India agrees with 'Stop' on Red Signal ");
    }

    public static void agreement() {
        System.out.println("Agreement to traffic rules ");
    }

    @Override
    public void amberLight() {
        System.out.println("As of Now India dosen't want Amber traffic Light");
    }

    public static void main(String[] args) {
        worldTrafficRules wT=new indianTraffic();
        wT.goGreen();
        wT.stopRed();
        wT.watchYellow();
        asianTrafficRules aT=new indianTraffic();
        aT.amberLight();
        //indianTraffic iT=new indianTraffic();
        agreement();
    }
}
