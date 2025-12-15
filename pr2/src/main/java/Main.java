package pr2;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<String> lines = DataReader.readLines(filePath);

        List<Transaction> transactions = TransactionCSVReader.parseTransactions(lines);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        System.out.println("\n--- Аналіз за період 01-01-2024 до 31-03-2024 ---");
        TransactionAnalyzer.findMaxExpense(transactions, "01-01-2024", "31-03-2024")
                .ifPresent(t -> System.out.println("Найбільша витрата: " + t));
        TransactionAnalyzer.findMinExpense(transactions, "01-01-2024", "31-03-2024")
                .ifPresent(t -> System.out.println("Найменша витрата: " + t));

        Map<String, Double> expensesByCategory = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        Map<String, Double> expensesByMonth = TransactionAnalyzer.calculateExpensesByMonth(transactions);

        TransactionReportGenerator.printExpensesByCategory(expensesByCategory);
        TransactionReportGenerator.printExpensesByMonth(expensesByMonth);
    }
}
