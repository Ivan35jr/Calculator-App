
package com.mycompany.laby1g2;
import java.util.*;

public class LabY1G2 {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> history = new ArrayList<>();
    public static void main(String[] args) {
        boolean running = true;
        char operator = 0;
        double num1 = 0, num2 = 0, result = 0;
        while(running){
            int choice = menu();
            switch(choice){

                case 1:
                    System.out.println("***Calculator***");
                    System.out.println("""
                            List of available operators and their symbols:
                           +(plus)
                           -(minus)
                           *(times)
                           /(divide)
                           %(modulus)
                           ^(exponent)
                           r(square root)
                           A(absolute value)
                           L(log base 10)
                           """);
                    num1 = first(num1);
                    operator = op(operator);
                    if(operator != 'r' &&
                            operator != 'A' &&
                            operator != 'L'){
                        num2 = second(num2, operator);
                    }
                    result = calculate(num1, num2, operator);
                    saveHistory(num1, num2, result, operator);
                    displayResult(num1, num2, result, operator);

                    System.out.print("Use previous answer? (Y/N): ");
                    char usePrevious = scan.next().charAt(0);
                    if(usePrevious == 'Y' || usePrevious == 'y'){

                        num1 = result;

                        operator = op(operator);

                        if(operator != 'r' &&
                                operator != 'A' &&
                                operator != 'L'){
                            num2 = second(num2, operator);
                        }
                        result = calculate(num1, num2, operator);
                        saveHistory(num1, num2, result, operator);
                        displayResult(num1, num2, result, operator);
                    }
                    break;

                case 2:
                    viewHistory();
                    break;

                case 3:
                    System.out.println("Thank you for using the calculator.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
//        System.out.println("***Calculator***");
//        System.out.println("""
//                            List of available operators and their symbols:
//                           +(plus)
//                           -(minus)
//                           *(times)
//                           /(divide)
//                           %(modulus)
//                           ^(exponent)
//                           r(square root)
//                           A(absolute value)
//                           L(log base 10)
//                           """);
//        char operator = 0, cont;
//        double num1 = 0, num2 = 0, result = 0;
//        do{
//            num1 = first(num1);
//            operator = op(operator);
//            if(operator != 'r' &&
//                operator != 'A' &&
//                operator != 'L'){
//                num2 = second(num2, operator);
//            }
//            result = calculate(num1, num2, operator);
//            saveHistory(num1, num2, result, operator);
//            displayResult(num1, num2, result, operator);
//
//            System.out.print("Use previous answer? (Y/N): ");
//            char usePrevious = scan.next().charAt(0);
//            if(usePrevious == 'Y' || usePrevious == 'y'){
//
//                num1 = result;
//
//                operator = op(operator);
//
//                if(operator != 'r' &&
//                        operator != 'A' &&
//                        operator != 'L'){
//                    num2 = second(num2, operator);
//                }
//                result = calculate(num1, num2, operator);
//                saveHistory(num1, num2, result, operator);
//                displayResult(num1, num2, result, operator);
//            }
//            System.out.println("Press c to continue:");
//            cont = scan.next().charAt(0);
//            System.out.println(" ");
//            if(cont!='c' && cont!='C'){
//                System.out.println("Program ended. Thank you for using the system.");
//                break;
//            }
//        }while(cont=='c' || cont=='C');
    }

    public static double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '%':
                return num1 % num2;
            case '^':
                return Math.pow(num1, num2);
            case 'r':
                return Math.sqrt(num1);
            case 'A':
                return Math.abs(num1);
            case 'L':
                return Math.log10(num1);
            default:
                return 0;
        }
    }

    public static double first (double num1){
        System.out.println("Enter first value:");
        while (!scan.hasNextDouble()){
            System.out.println("Invalid input!");
            System.out.println("Enter first value:");
            scan.next();
        }
        num1=scan.nextDouble();
        return num1;
    }
    
    public static char op (char operator){
        System.out.println("Enter operator:");
        operator = scan.next().charAt(0);
        while(operator != '+' &&
              operator != '-' &&
              operator != '*' &&
              operator != '/' &&
              operator != '%' &&
              operator != '^' &&
              operator != 'r' &&
              operator != 'A' &&
              operator != 'L'){
            System.out.println("Invalid input!");
            System.out.println("Enter operator:");
            operator=scan.next().charAt(0);
        }
        return operator;
    }
    
    public static double second (double num2, char operator){
        boolean isZero=true;
        while(isZero){
            System.out.println("Enter second value:");
            if(scan.hasNextDouble()){
                num2 = scan.nextDouble();
                if (operator == '/' && num2 == 0){
                    isZero=true;
                    System.out.println("Denominator can't be zero.");
                }
                else if (operator == '/' && num2 != 0){
                    isZero = false;
                    System.out.println("Not zero.");
                }else{
                    isZero = false;
                }
            }else{
                isZero = true;
                System.out.println("Invalid input!");
                scan.next();
            }
        }
        return num2;
    }

    public static void displayResult(double num1, double num2, double result, char operator){
        if(operator == 'r' ||
                operator == 'A' ||
                operator == 'L'){

            System.out.println(operator + "(" + num1 + ") = " + result);
        }else{
            System.out.println(num1 + " "
                    + operator + " "
                    + num2 + " = "
                    + result);
        }
    }

    public static void saveHistory(double num1, double num2, double result, char operator){
        if(operator == 'r' ||
                operator == 'A' ||
                operator == 'L'){

            history.add(operator + "(" + num1 + ") = " + result);
        }else{
            history.add(num1 + " "
                    + operator + " "
                    + num2 + " = "
                    + result);

        }
    }

    public static void viewHistory(){

        if(history.isEmpty()){
            System.out.println("\nNo calculations yet.\n");
            return;
        }

        System.out.println("\n===== Calculation History =====");

        for(int i = 0; i < history.size(); i++){
            System.out.println((i + 1) + ". " + history.get(i));
        }

        System.out.println();
    }

    public static int menu(){

        System.out.println("""
            
            ===== Calculator Menu =====
            1. New Calculation
            2. View History
            3. Exit
            """);

        System.out.print("Choose: ");

        while(!scan.hasNextInt()){
            System.out.println("Invalid choice.");
            scan.next();
        }

        return scan.nextInt();
    }
}
