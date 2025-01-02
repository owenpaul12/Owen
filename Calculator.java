package calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0;

        System.out.println("Enter the operator (+, -, *, /, sin, cos, tan, log, square, %, !, round):");
        String operator = scanner.nextLine().trim().toLowerCase();

        try {
            switch (operator) {
                case "+":
                case "-":
                case "*":
                case "/":
                    System.out.println("Please enter the first number:");
                    double num1 = scanner.nextDouble();
                    System.out.println("Please enter the second number:");
                    double num2 = scanner.nextDouble();

                    result = switch (operator) {
                        case "+" -> num1 + num2;
                        case "-" -> num1 - num2;
                        case "*" -> num1 * num2;
                        case "/" -> {
                            if (num2 == 0) {
                                System.out.println("Division by zero is not allowed.");
                                yield Double.NaN;
                            } else {
                                yield num1 / num2;
                            }
                        }
                        default -> 0;
                    };
                    System.out.printf("The result is: %.2f%n", result);
                    break;

                case "sin":
                case "cos":
                case "tan":
                    System.out.println("Please enter the angle in degrees (e.g., 30):");
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Invalid input. Please enter a valid angle.");
                        break;
                    }

                    input = input.replace("\u00b0", "").trim();

                    try {
                        double angle = Double.parseDouble(input);
                        angle = Math.toRadians(angle);

                        result = switch (operator) {
                            case "sin" -> Math.sin(angle);
                            case "cos" -> Math.cos(angle);
                            case "tan" -> Math.tan(angle);
                            default -> 0;
                        };

                        System.out.printf("The result is: %.2f%n", result);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid angle format. Please enter a valid numeric value.");
                    }
                    break;

                case "log":
                    System.out.println("Please enter the number:");
                    double number = scanner.nextDouble();
                    if (number > 0) {
                        result = Math.log10(number);
                        System.out.printf("The result is: %.2f%n", result);
                    } else {
                        System.out.println("Logarithm of non-positive numbers is not defined.");
                    }
                    break;

                case "square":
                    System.out.println("Please enter the number:");
                    double base = scanner.nextDouble();
                    result = base * base;
                    System.out.printf("The result is: %.2f%n", result);
                    break;

                case "%":
                    System.out.println("Please enter the number:");
                    double value = scanner.nextDouble();
                    result = value / 100;
                    System.out.printf("The result is: %.2f%n", result);
                    break;

                case "!":
                    System.out.println("Please enter a non-negative integer:");
                    int factorialInput = scanner.nextInt();
                    if (factorialInput >= 0) {
                        result = factorial(factorialInput);
                        System.out.println("The result is: " + result);
                    } else {
                        System.out.println("Factorial of a negative number is not defined.");
                    }
                    break;

                case "round":
                    System.out.println("Please enter the number to round off:");
                    double roundNumber = scanner.nextDouble();
                    result = Math.round(roundNumber);
                    System.out.println("The rounded result is: " + (long) result);
                    break;

                default:
                    System.out.println("Invalid operator. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
