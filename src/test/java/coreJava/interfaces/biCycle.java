package coreJava.interfaces;

public class biCycle implements vehicles {

    static int speed;
    static int gear;

    @Override
    public void changeGear(int newGear) {
        gear=newGear;
    }

    @Override
    public void speedUp(int increment) {
        speed=speed+increment;
    }

    @Override
    public void applyBrakes(int decrement) {
        speed=speed-decrement;
    }

    public void printState() {
        System.out.println("Speed is :"+speed+" Gear is :"+gear);
    }

    public static void main(String[] args) {
        vehicles v=new biCycle();
        v.speedUp(5);
        v.applyBrakes(2);
        v.changeGear(4);

        biCycle b=new biCycle();
        b.printState();
    }

}
