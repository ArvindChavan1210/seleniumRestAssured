package coreJava.interfaces;

public class motorCycle implements vehicles{
    static int speed;
    static int gear;


    @Override
    public void changeGear(int NewGear) {
        gear=NewGear;
    }

    @Override
    public void speedUp(int increment) {
        System.out.println("Increment "+speed);
        speed=speed+increment;
    }

    @Override
    public void applyBrakes(int decrement) {
        System.out.println("Decrement "+speed);
        speed=speed-decrement;
    }

    public void currentState(){
        System.out.println("Current Speed is "+speed+" Current Gear is "+gear);
    }

    public static void main(String[] args) {
        vehicles v=new motorCycle();
        motorCycle m=new motorCycle();
        v.applyBrakes(5);
        v.changeGear(5);
        v.speedUp(20);
        m.currentState();
    }

}
