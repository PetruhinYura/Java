package duikt.java.com;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TestFSM fsm = new TestFSM();
        
        System.out.println("=== Скінчений автомат для розпізнавання слова TEST ===");
        System.out.println("Введіть рядок (або 'exit' для виходу):");
        
        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Дякуємо за використання програми!");
                break;
            }
            
            String result = fsm.process(input);
            
            System.out.println("Вхід: \"" + input + "\"");
            System.out.println("Результат: " + result);
            
            if (result.equals("F")) {
                System.out.println("Слово TEST знайдено у рядку!");
            } else {
                System.out.println("Слово TEST не знайдено. Кінцевий стан: " + result);
            }
        }
        
        scanner.close();
    }
}
