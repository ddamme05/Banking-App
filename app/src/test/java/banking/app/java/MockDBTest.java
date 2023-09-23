package banking.app.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MockDBTest {

    private MockDB mockDB;

    @BeforeEach
    void setUp() {
        mockDB = new MockDB();
    }

    @Test
    void testAddAndGetAccount() {
        Account account = new Account("testAccount");
        assertTrue(mockDB.addAccount(account), "Failed to add account.");
        assertEquals(account, mockDB.getAccount("testAccount"), "Failed to retrieve the added account.");
    }

    @Test
    void testGetNonexistentAccount() {
        assertNull(mockDB.getAccount("nonexistent"), "Retrieved an account that shouldn't exist.");
    }

    @Test
    void testRemoveAccount() {
        Account account = new Account("testAccount");
        mockDB.addAccount(account);
        assertTrue(mockDB.removeAccount("testAccount"), "Failed to remove the account.");
        assertFalse(mockDB.removeAccount("testAccount"), "Account was removed twice.");
    }

    @Test
    void testAccountExists() {
        Account account = new Account("testAccount");
        mockDB.addAccount(account);
        assertTrue(mockDB.accountExists("testAccount"), "Account should exist.");
        assertFalse(mockDB.accountExists("nonexistent"), "Account shouldn't exist.");
    }

    @Test
    void testGetAllAccountObjects() {
        Account account1 = new Account("acc1");
        Account account2 = new Account("acc2");
        mockDB.addAccount(account1);
        mockDB.addAccount(account2);
        Collection<Account> accountCollection = mockDB.getAllAccountObjects();
        assertTrue(accountCollection.contains(account1) && accountCollection.contains(account2), "Failed to retrieve all added account objects.");
    }

    @Test
    void testGetAllAccounts() {
        Account account1 = new Account("acc1");
        Account account2 = new Account("acc2");
        mockDB.addAccount(account1);
        mockDB.addAccount(account2);
        Map<String, Double> accountBalances = mockDB.getAllAccounts();
        assertTrue(accountBalances.containsKey("acc1") && accountBalances.containsKey("acc2"), "Failed to retrieve all added accounts.");
    }
}
