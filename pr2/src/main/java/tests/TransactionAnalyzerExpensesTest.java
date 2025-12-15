package pr2.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pr2.Transaction;
import pr2.TransactionAnalyzer;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerExpensesTest {
    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2024", -100.0, "Кава"),
                new Transaction("02-01-2024", -500.0, "Одяг"),
                new Transaction("03-01-2024", -50.0, "Солодке"),
                new Transaction("04-01-2024", -800.0, "Телефон"),
                new Transaction("05-01-2024", -300.0, "Їжа")
        );

        List<Transaction> top = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(5, top.size(), "Неправильна кількість витрат");
        Assertions.assertEquals(-800.0, top.get(0).getAmount(), "Найбільша витрата не перша");
    }
}
