package banking.app.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class QueryHandlerTest {
    private Bank bank;
    private QueryHandler queryHandler;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        queryHandler = new QueryHandler(bank);
    }

    @Test
    public void testCreateAccount() {
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"CREATE_ACCOUNT", "1", "Alice"});
        String result = queryHandler.handleQueries(queries).get(0);
        assertTrue(bank.getAllAccounts().containsKey("Alice"));
        assertEquals("true", result);
    }

    @Test
    public void testDeposit() {
        bank.createAccount("Alice");
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"DEPOSIT", "2", "Alice", "100"});
        String result = queryHandler.handleQueries(queries).get(0);
        assertEquals("100.0", result);
        assertEquals(100.0, bank.getBalance("Alice"), 0.01);
    }

    @Test
    public void testWithdraw() {
        bank.createAccount("Bob");
        bank.deposit("Bob", 200.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"WITHDRAW", "3", "Bob", "50"});
        String result = queryHandler.handleQueries(queries).get(0);
        assertEquals("150.0", result);
        assertEquals(150.0, bank.getBalance("Bob"), 0.01);
    }

    @Test
    public void testTransfer() {
        bank.createAccount("Charlie");
        bank.createAccount("David");
        bank.deposit("Charlie", 500.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"TRANSFER", "4", "Charlie", "David", "200"});
        String result = queryHandler.handleQueries(queries).get(0);
        assertEquals("300.0", result);
        assertEquals(300.0, bank.getBalance("Charlie"), 0.01);
        assertEquals(200.0, bank.getBalance("David"), 0.01);
    }

    @Test
    public void testShopCashback() {
        bank.createAccount("Eve");
        bank.deposit("Eve", 2000.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"SHOP_CASHBACK", "Eve", "Amazon", "2000"});
        String result = queryHandler.handleQueries(queries).get(0);
        assertEquals("10.0", result);
    }


    @Test
    public void testMergeAccounts() {
        bank.createAccount("Frank");
        bank.createAccount("Grace");
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"MERGE_ACCOUNTS", "Frank", "Grace"});
        String result = queryHandler.handleQueries(queries).get(0);
        assertEquals("Merge Successful!", result);
    }
}
