package coreJava;

public class swapNumbers {

    public void swap_using_third_variable(int first, int second){
        int temp=0;
        int a=first;
        int b=second;
        System.out.println("Value of A before Swap :"+first+" & Value of B before Swap :"+second);
        temp=a;
        a=b;
        b=temp;
        System.out.println("Value of A after Swap :"+a+" & Value of B after Swap :"+b);
    }

    public void swap_without_using_third_variable(int first, int second) {
        int a = first;
        int b = second;
        System.out.println("Value of A before Swap :"+first+" & Value of B before Swap :"+second);
        if (a > b) {
            a = a + b;
            b = a - b;
            a = a - b;
        } else {
            b=a+b;
            a=b-a;
            b=b-a;
        }
        System.out.println("Value of A after Swap :"+a+" & Value of B after Swap :"+b);
    }

    public static void main(String[] args) {
        swapNumbers sN=new swapNumbers();
        sN.swap_using_third_variable(4,5);
        sN.swap_without_using_third_variable(9,8);
    }

}
