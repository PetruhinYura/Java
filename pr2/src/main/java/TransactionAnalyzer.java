package pr2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TransactionAnalyzer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        return (int) transactions.stream()
                .filter(t -> {
                    LocalDate date = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    String tMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                    return tMonthYear.equals(monthYear);
                })
                .count();
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Optional<Transaction> findMaxExpense(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .min(Comparator.comparing(Transaction::getAmount));
    }

    public static Optional<Transaction> findMinExpense(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
        LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);

        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .filter(t -> {
                    LocalDate d = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                    return !d.isBefore(start) && !d.isAfter(end);
                })
                .max(Comparator.comparing(Transaction::getAmount));
    }

    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> map = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                map.put(t.getDescription(),
                        map.getOrDefault(t.getDescription(), 0.0) + Math.abs(t.getAmount()));
            }
        }
        return map;
    }

    public static Map<String, Double> calculateExpensesByMonth(List<Transaction> transactions) {
        Map<String, Double> map = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                LocalDate d = LocalDate.parse(t.getDate(), DATE_FORMATTER);
                String monthYear = d.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                map.put(monthYear,
                        map.getOrDefault(monthYear, 0.0) + Math.abs(t.getAmount()));
            }
        }
        return map;
    }
}
