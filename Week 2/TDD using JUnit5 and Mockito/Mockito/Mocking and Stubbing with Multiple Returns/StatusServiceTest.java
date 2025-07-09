import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StatusServiceTest {

    @Test
    public void testMultipleReturnsFromMock() {
        
        ExternalApi mockApi = mock(ExternalApi.class);

        when(mockApi.getStatus())
            .thenReturn("Loading")
            .thenReturn("Processing")
            .thenReturn("Completed");

        StatusService service = new StatusService(mockApi);
        String[] result = service.checkStatusMultipleTimes();

        assertEquals("Loading", result[0]);
        assertEquals("Processing", result[1]);
        assertEquals("Completed", result[2]);

        verify(mockApi, times(3)).getStatus();
    }
}
