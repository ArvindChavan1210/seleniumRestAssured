package org.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateUtility {

    public static String currentDateTime(){
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        return sdf.format(d);
    }


    public static void main(String[] args) {
        System.out.println(currentDateTime());
    }
}
