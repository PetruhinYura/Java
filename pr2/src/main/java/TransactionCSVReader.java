package pr2;

import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    public static List<Transaction> parseTransactions(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            try {
                String date = parts[0].trim();
                double amount = Double.parseDouble(parts[1].trim());
                String description = parts[2].trim();

                transactions.add(new Transaction(date, amount, description));
            } catch (NumberFormatException e) {
                System.err.println("Помилка парсингу рядка: " + line);
            }
        }

        return transactions;
    }
}
