package pr3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(scanner.nextLine());

            System.out.print("Оберіть операцію (+, -, *, /, sqrt): ");
            String operation = scanner.nextLine().trim();

            double result;

            switch (operation) {
                case "+":
                    result = calculator.add(a, b);
                    System.out.println("Результат: " + result);
                    break;
                case "-":
                    result = calculator.subtract(a, b);
                    System.out.println("Результат: " + result);
                    break;
                case "*":
                    result = calculator.multiply(a, b);
                    System.out.println("Результат: " + result);
                    break;
                case "/":
                    result = calculator.divide(a, b);
                    System.out.println("Результат: " + result);
                    break;
                case "sqrt":
                    result = calculator.sqrt(a);
                    System.out.println("Квадратний корінь з " + a + " = " + result);
                    break;
                default:
                    System.out.println("Невідома операція!");
            }
             
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введено некоректне число!");
        } catch (ArithmeticException e) {
            System.out.println("Помилка арифметичної операції: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        } finally {
            System.out.println("Обробку запиту завершено");
            scanner.close();
        }

    }
}
