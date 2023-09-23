package banking.app.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
    }

    @Test
    void testCreateAccount() {
        assertTrue(bank.createAccount("testAccount1"));
        assertFalse(bank.createAccount("testAccount1")); // Cannot create an account with the same ID
    }

    @Test
    void testDeposit() {
        bank.createAccount("testAccount2");
        assertEquals(100.0, bank.deposit("testAccount2", 100.0));
    }

    @Test
    void testTransfer() {
        bank.createAccount("backend");
        bank.createAccount("frontend");
        bank.deposit("backend", 200.0);
        double updatedBalance = bank.transfer("backend", "frontend", 100.0);
        assertEquals(100.0, updatedBalance, "Updated balance after transfer should be 100.0");
        assertEquals(100.0, bank.getAccount("backend").getBalance());
        assertEquals(100.0, bank.getAccount("frontend").getBalance());
    }


    @Test
    void testGetAllAccounts() {
        bank.createAccount("acc1");
        bank.createAccount("acc2");
        assertEquals(2, bank.getAllAccounts().size());
    }

    @Test
    void testGetAccountBalance() {
        bank.createAccount("balanceTest");
        bank.deposit("balanceTest", 300.0);
        assertEquals(300.0, bank.getBalance("balanceTest"));
    }
}
