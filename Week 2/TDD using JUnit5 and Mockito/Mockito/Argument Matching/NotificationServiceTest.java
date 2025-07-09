import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.Test;

public class NotificationServiceTest {

    @Test
    public void testArgumentMatching() {
        
        Messenger mockMessenger = mock(Messenger.class);

        NotificationService service = new NotificationService(mockMessenger);
        service.notifyUser();

        verify(mockMessenger).sendMessage(eq("irfaan@example.com"), eq("Welcome to our service!"));
        
    }
}
