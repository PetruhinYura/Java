package pr2;

import java.util.Map;
import java.util.List;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance + " грн");
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount() + " грн");
        }
    }

    public static void printExpensesByCategory(Map<String, Double> map) {
        System.out.println("\nВитрати по категоріях:");
        for (String category : map.keySet()) {
            double amount = map.get(category);
            int stars = (int) (amount / 1000); // 1 зірка = 1000грн
            System.out.printf("%-15s | %s (%.2f грн)%n", category, "*".repeat(stars), amount);
        }
    }

    public static void printExpensesByMonth(Map<String, Double> map) {
        System.out.println("\nВитрати по місяцях:");
        for (String month : map.keySet()) {
            double amount = map.get(month);
            int stars = (int) (amount / 1000);
            System.out.printf("%s | %s (%.2f грн)%n", month, "*".repeat(stars), amount);
        }
    }
}
