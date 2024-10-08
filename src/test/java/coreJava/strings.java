package coreJava;

public class strings {
    String name = "";

    StringBuilder sb;

    public strings(String input) {
        this.name = input;
        sb = new StringBuilder(name);
    }

    public static void main(String[] args) {
        strings s = new strings("Hello World!");
        s.reverse_a_string();
        s.reverse_a_string_using_stringBuilder();
        s.change_string();
        s.palindrome_without_StringBuilder("121");
        s.palindrome_with_stringBuilder("aba");
    }

    public void reverse_a_string() {
        String new_string = "";
        for (int i = name.length(); i > 0; i--) {
            new_string += name.charAt(i - 1);
        }
        System.out.println(new_string);
    }

    public void reverse_a_string_using_stringBuilder() {
        System.out.println(sb.reverse());
    }

    public void change_string(){
        String new_string=name.replace(name.substring(6,12),"Nanded!");
        System.out.println(new_string);
    }

    public void palindrome_without_StringBuilder(String input) {
        String new_string="";
        for(int i=input.length(); i>0; i--){
            new_string+=input.charAt(i-1);
        }
        if(new_string.equals(input)){
            System.out.println("Input String is PALINDROME");
        } else {
            System.out.println("Input String is not PALINDROME");
        }
    }

    public void palindrome_with_stringBuilder(String input){
        if(sb.reverse().equals(input)){
            System.out.println("Input String is PALINDROME");
        } else {
            System.out.println("Input String is not PALINDROME");
        }
    }

}
