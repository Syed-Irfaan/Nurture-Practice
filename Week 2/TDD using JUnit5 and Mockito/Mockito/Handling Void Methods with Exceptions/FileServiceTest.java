import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FileServiceTest {

    @Test
    public void testVoidMethodThrowsException() throws Exception {
        
        FileWriter mockWriter = mock(FileWriter.class);

        doThrow(new RuntimeException("Disk full")).when(mockWriter).write("Hello");

        FileService service = new FileService(mockWriter);
        service.save("Hello");

        verify(mockWriter).write("Hello");
    }
}
