package org.utilities;

import java.io.BufferedReader;
import java.io.FileReader;

public class txtReader {

    public String readFile(String s) {
        try {
            FileReader fr = new FileReader(s);
            BufferedReader br = new BufferedReader(fr);
            return br.readLine();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        txtReader tr=new txtReader();
      String line=  tr.readFile("src/test/resources/messageBoxText.txt");
        System.out.println(line);
    }

}
