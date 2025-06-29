import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.Test;

public class ProcessorTest {

    @Test
    public void testVoidMethodCalled() {
        
        Logger mockLogger = mock(Logger.class);
        
        Processor processor = new Processor(mockLogger);
        processor.process();

        verify(mockLogger).log("Processing...");
    }
}
