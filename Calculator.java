import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    // Variables
    private double firstNum = 0;
    private double secondNum = 0;
    private double result = 0;
    private char operator = ' ';
    static final Scanner scanner = new Scanner(System.in);

    public void start() {
        try{
            System.out.println("Welcome to the calculator!");

            firstNum = getNumberInput("Enter your first number: ");
            operator = getOperatorInput();
            secondNum = getNumberInput("Enter your second number: ");
    
            calculate();
   
            System.out.println("Thank you for using the calculator!"); 
        } finally {
            scanner.close();
        }   
    }

    // getNumberInput
    private double getNumberInput(String prompt) {
        while (true) { 
            System.out.println(prompt);
            try {
                return scanner.nextDouble();  
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a valid number: ");
                scanner.nextLine();  
            }
        }
    }

    // getOperatorInput
    private char getOperatorInput() {
        String validOperators = "+-*/";
        while (true) {
            System.out.println("Enter your operator (+ - * /): ");
            String input = scanner.next();
            if (input.length() == 1 && validOperators.contains(input)) {
                return input.charAt(0);
            }
            System.out.println("Invalid operator. Enter a valid operator (+ - * /): ");
            scanner.nextLine();
        }
    }

    // calculate
    private void calculate(){
        try{
            result = switch (operator) {
                case '+' -> add(firstNum, secondNum);
                case '-' -> subtract(firstNum, secondNum);
                case '*' -> multiply(firstNum, secondNum);
                case '/' -> divide(firstNum, secondNum);
                default -> {
                    System.out.println("Invalid operator.");
                    yield 0;    
            }};

            displayResult();
        } catch (ArithmeticException e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }

    // displayResult
    private void displayResult() {
        if (Double.isFinite(result)) {
            if (result == (long) result) {
                System.out.printf("%d %c %d = %d%n", 
                    (long)firstNum, operator, (long)secondNum, (long)result);
            } else {
                System.out.printf("%.2f %c %.2f = %.2f%n", 
                    firstNum, operator, secondNum, result);
            }
        }
    }
    // Add
    private double add(double a, double b) {
        return a + b;
    }

    // Subtract
    private double subtract(double a, double b) {
        return a - b;
    }

    // Multiply
    private double multiply(double a, double b) {
        return a * b;
    }

    // Divide
    private double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }  

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }
}
