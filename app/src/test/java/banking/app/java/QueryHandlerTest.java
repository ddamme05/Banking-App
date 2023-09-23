package banking.app.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryHandlerTest {

    private QueryHandler queryHandler;
    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        queryHandler = new QueryHandler(bank);
    }

    @Test
    void testHandleCreateAccountQuery() {
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"CREATE_ACCOUNT", "1", "testAccount"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("true"), "Failed to create account.");
    }

    @Test
    void testHandleDepositQuery() {
        bank.createAccount("testAccount2");
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"DEPOSIT", "1", "testAccount2", "200"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("200.0"), "Failed to deposit the correct amount.");
    }

    @Test
    void testHandleTransferQuery() {
        bank.createAccount("sender");
        bank.createAccount("receiver");
        bank.deposit("sender", 300.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"TRANSFER", "1", "sender", "receiver", "100"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("200.0"), "Transfer not executed correctly.");
    }

    @Test
    void testHandleTopSpendersQuery() {
        bank.createAccount("account1");
        bank.createAccount("account2");
        bank.createAccount("account3");
        bank.deposit("account1", 500.0);
        bank.deposit("account2", 300.0);
        bank.transfer("account1", "account2", 250.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"TOP_SPENDERS", "2"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("account1(250.0), account2(0.0)"), "Top spenders query not executed correctly.");
    }

    @Test
    void testHandleCashbackQuery() {
        bank.createAccount("cashbackAccount");
        bank.deposit("cashbackAccount", 1000.0);
        bank.transfer("cashbackAccount", "someOtherAccount", 500.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"CASHBACK", "cashbackAccount"});
        List<String> results = queryHandler.handleQueries(queries);
        System.out.println(results);
        assertTrue(results.contains("2.5"), "Cashback query not executed correctly.");
    }

    @Test
    void testHandleMergeAccountsQuery() {
        bank.createAccount("accToMerge1");
        bank.createAccount("accToMerge2");
        bank.deposit("accToMerge1", 1000.0);
        bank.deposit("accToMerge2", 500.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"MERGE_ACCOUNTS", "accToMerge1", "accToMerge2"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("Merge Successful!"), "Merge accounts query not executed correctly.");
        assertEquals(1500.0, bank.getBalance("accToMerge2"), "After merge, balance not reflected correctly.");
    }

    @Test
    void testHandleWithdrawQuery() {
        bank.createAccount("withdrawAccount");
        bank.deposit("withdrawAccount", 500.0);
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"WITHDRAW", "1", "withdrawAccount", "200"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("300.0"), "Failed to withdraw the correct amount.");
    }

    @Test
    void testHandleUnsupportedQuery() {
        List<String[]> queries = new ArrayList<>();
        queries.add(new String[]{"MILLION_DOLLARS"});
        List<String> results = queryHandler.handleQueries(queries);
        assertTrue(results.contains("Unsupported query!"), "Unsupported query not handled correctly.");
    }
}
