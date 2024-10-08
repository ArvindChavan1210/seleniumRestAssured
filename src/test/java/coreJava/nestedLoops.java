package coreJava;

public class nestedLoops {

    public void nestedLoopConcept() {
        /* Here we can see how inner and outer loop execution takes place
         * */
        for (int i = 1; i <= 5; i++) {
            System.out.println("* Outer Loop Execution Iteration :" + i + " *");
            for (int j = 1; j <= 5; j++) {
                System.out.println("Inner Loop Execution Iteration :" + j);
            }
        }

    }

    public void incremental_inner_loop() {
        /*
         *  *
         *  * *
         *  * * *
         *  * * * *
         *  * * * * *
         * */
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print("* ");
                if (i == j) {
                    break;
                }
            }
            System.out.println(" ");
        }
    }

    public void decremental_inner_loop() {
        /*
         *  * * * * *
         *  * * * *
         *  * * *
         *  * *
         *  *
         *
         * */

        for (int i = 1; i <= 5; i++) {
            for (int j = i; j <= 5; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public void inverted_decremental_inner_loop() {
        /*
         *  * * * * *
         *    * * * *
         *      * * *
         *        * *
         *          *
         */

        for (int i = 0; i < 5; i++) {
            // Print leading spaces
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            // Print stars
            for (int k = 5 - i; k > 0; k--) {
                System.out.print("* ");
            }
            // Move to the next line
            System.out.println();
        }
    }

    public void inverted_incremental_inner_loop() {
        for(int i=1; i<=5; i++){
            for(int j=i; j<5; j++){
                System.out.print("  ");
            }
            for(int k=1; k<=i; k++){
                System.out.print("* ");
           }
            System.out.println();
        }
    }

    public void numberPyramid(){
        /* 1  2  3  4
           5  6  7
           8  9
           10
        * */
        int k=1;
        for(int i=1; i<5; i++){
            for(int j=i; j<5; j++){
                System.out.print(k+"   ");
                k++;
            }
            System.out.println();
            System.out.println();
        }

    }

    public void invertedNumberPyramid(){
        int k=1;
        for(int i=1; i<5; i++){
            for(int j=1; j<5; j++){
                System.out.print(k+"  ");
                k++;
                if(j==i){
                    break;
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        nestedLoops nl = new nestedLoops();
//        nl.nestedLoopConcept();
//    nl.incremental_inner_loop();
//    nl.decremental_inner_loop();
//        nl.inverted_decremental_inner_loop();
//        nl.inverted_incremental_inner_loop();
//        nl.numberPyramid();
        nl.invertedNumberPyramid();
    }


}
