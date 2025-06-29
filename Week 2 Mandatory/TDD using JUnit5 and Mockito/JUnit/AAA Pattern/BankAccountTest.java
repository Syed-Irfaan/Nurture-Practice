import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount account;

    
    @Before
    public void setUp() {
        account = new BankAccount(100); 
        System.out.println("Setup: Account created with balance ₹100");
    }

    
    @After
    public void tearDown() {
        account = null;
        System.out.println("Teardown: Account object destroyed");
    }

    @Test
    public void testDeposit() {
        
        account.deposit(50);
        
        assertEquals(150, account.getBalance());
    }

    @Test
    public void testSuccessfulWithdrawal() {
        
        boolean result = account.withdraw(40);
        
        assertTrue(result);
        assertEquals(60, account.getBalance());
    }

    @Test
    public void testFailedWithdrawal() {
        
        boolean result = account.withdraw(200);
        
        assertFalse(result);
        assertEquals(100, account.getBalance());
    }
}
