import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class TransactionServiceTest {

    @Test
    public void testMethodCallOrder() {
        
        TransactionManager mockManager = mock(TransactionManager.class);

        TransactionService service = new TransactionService(mockManager);
        service.performTransaction();

        InOrder inOrder = inOrder(mockManager);
        inOrder.verify(mockManager).start();
        inOrder.verify(mockManager).log("Transaction started");
        inOrder.verify(mockManager).commit();
    }
}
