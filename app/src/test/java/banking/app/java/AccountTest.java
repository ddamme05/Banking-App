package banking.app.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("testAccount");
    }

    @Test
    void testDeposit() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testWithdraw() {
        account.deposit(200.0);
        account.withdraw(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void testWithdrawMoreThanBalance() {
        account.deposit(100.0);
        assertFalse(account.withdraw(150.0));
    }

    @Test
    void testGetAccountId() {
        assertEquals("testAccount", account.getAccountId());
    }

    @Test
    void testGetBalance() {
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void testGetTransactions() {
        assertTrue(account.getTransactions().isEmpty());
        account.deposit(50.0);
        assertFalse(account.getTransactions().isEmpty());
    }
}
