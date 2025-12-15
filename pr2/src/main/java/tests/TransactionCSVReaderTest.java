package pr2.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pr2.Transaction;
import pr2.TransactionCSVReader;

import java.util.Arrays;
import java.util.List;

class TransactionCSVReaderTest {
    @Test
    public void testParseTransactions() {
        List<String> lines = Arrays.asList(
                "01-01-2024,1000,Зарплата",
                "02-01-2024,-200,Продукти"
        );

        List<Transaction> transactions = TransactionCSVReader.parseTransactions(lines);

        Assertions.assertEquals(2, transactions.size(), "Кількість транзакцій неправильна");
        Assertions.assertEquals("Зарплата", transactions.get(0).getDescription());
        Assertions.assertEquals(-200, transactions.get(1).getAmount());
    }
}
