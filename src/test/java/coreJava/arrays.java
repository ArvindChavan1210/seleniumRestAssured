package coreJava;

import java.util.ArrayList;
import java.util.Collections;

public class arrays {

    public void simpleArray() {
        int[] a = new int[5];
        a[0] = 2;
        a[1] = 5;
        a[2] = 7;
        a[3] = 11;
        a[4] = 1;

        for (int j : a) {
            System.out.println(j);
        }

    }

    public void arrayLiteral_ofSimpleArray() {
        int[] a = new int[]{2, 5, 12, 17, 40};
        for (int j : a) {
            System.out.println(j);
        }
    }

    public void array_operation() {
        //we can change the value of in array
        int[] a = new int[]{1, 5, 7, 9, 11};
        System.out.println("Value at index 3 before change :" + a[3]);
        a[3] = 25;
        System.out.println("Value at index 3 after change :" + a[3]);
    }

    public int[] arrayList_to_array() {
        ArrayList<Integer> numbers = new ArrayList<>();

        // Adding numbers from 1 to 9 to the ArrayList
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        // Initialize the array with the same size as the ArrayList
        int[] num = new int[numbers.size()];

        // Copy elements from the ArrayList to the array
        for (int j = 0; j < numbers.size(); j++) {
            num[j] = numbers.get(j);
        }

        // Print the array elements
        for (int k : num) {
            System.out.println(k);
        }
        return num;
    }

    public void array_to_arrayList() {
        int[] numArray = arrayList_to_array();
        ArrayList<Integer> numbers = new ArrayList<>();

        // Adding elements from array to ArrayList
        for (int i = 0; i < numArray.length; i++) {
            numbers.add(numArray[i]);
        }

        // Print the ArrayList
        System.out.println(numbers);
    }

    public void simple_multiDimensional_Array() {
        int[][] a = new int[2][3];
        //first one [2] -> ROW
        //second one [3] => COLUMN;
        a[0][0] = 2;
        a[0][1] = 3;
        a[0][2] = 5;
        a[1][0] = 7;
        a[1][1] = 4;
        a[1][2] = 9;

        for (int i = 0; i < a.length; i++) { //outer for loop for iteration through columns
            for (int j = 0; j < a[i].length; j++) { //inner loop for iteration through rows
                System.out.println(a[i][j]);
            }
        }


    }

    public void multi_dimensional_arrayLiteral() {
        int[][] a = new int[][]{{2, 3, 5}, {7, 4, 9}};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.println(a[i][j]);
            }
        }
    }

    public void find_lowest_value_from_array() {
        int[][] a = new int[][]{{2, 3, 5}, {7, 4, 9}, {1, 2, 6}};
        int minValue = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[0][0] > a[i][j]) {
                    minValue = a[i][j];
                }
            }
        }
        System.out.println("Minimum Value from Array " + minValue);
    }

    public void find_maximum_value_from_array() {
        int[][] a = new int[][]{{2, 3, 5}, {7, 4, 9}, {1, 2, 6}};
        int maxValue = Integer.MIN_VALUE; // Start with the smallest possible value
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) { // Corrected 'i' to 'j' in loop condition
                if (a[i][j] > maxValue) { // Compare with maxValue
                    maxValue = a[i][j];
                }
            }
        }

        System.out.println(maxValue);
    }

    public void sort_array_using_Bubble_sort() {
        int[] a = new int[]{3, 4, 2, 1, 7, 6, 9, 5};
        int temp = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public void sort_array_using_array_list() {
        int[] a = new int[]{3, 4, 2, 1, 7, 6, 9, 5};
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            numbers.add(a[i]);
        }
        Collections.sort(numbers);
        System.out.println(numbers);
    }

    public void find_lowest_row_value_from_Column_having_highest_column_value() {
        int[][] a = new int[][]{{2, 3, 5}, {7, 4, 9}, {1, 2, 6}};
        int maxValue = Integer.MIN_VALUE;
        int columnNumber = -1; // Use -1 to indicate no column found yet
        int minValue=0;

        // Find the column with the highest value
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] > maxValue) {
                    maxValue = a[i][j];
                    columnNumber = j; // Store the column index of the max value
                }
            }
        }
        // Find the lowest value in the identified column

            for (int i = 0; i < a.length; i++) {
                 minValue =a[0][columnNumber];
                if (a[i][columnNumber] < minValue) { // Update minValue if the current value is lower
                    minValue = a[i][columnNumber];
                }
            }


        // Only print if a valid minValue was found
        if (minValue != Integer.MAX_VALUE) {
            System.out.println(minValue);
        } else {
            System.out.println("No valid values found in the column.");
        }
    }



    public static void main(String[] args) {
        arrays a = new arrays();
        a.simpleArray();
        a.arrayLiteral_ofSimpleArray();
        a.array_operation();
        a.array_to_arrayList();
        a.simple_multiDimensional_Array();
        a.multi_dimensional_arrayLiteral();
        a.find_lowest_value_from_array();
        a.find_maximum_value_from_array();
        a.sort_array_using_Bubble_sort();
        a.sort_array_using_array_list();
        a.find_lowest_row_value_from_Column_having_highest_column_value();
    }
}
