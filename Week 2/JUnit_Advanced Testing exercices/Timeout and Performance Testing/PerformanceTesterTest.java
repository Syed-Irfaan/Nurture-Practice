import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;

public class PerformanceTesterTest {

    PerformanceTester tester = new PerformanceTester();

    @Test
    void testPerformTaskCompletesWithinOneSecond() {
        assertTimeout(Duration.ofSeconds(1), () -> {
            tester.performTask();
        });
    }

    /* @Test
    void testPerformTaskFailsIfTakesTooLong() {
        
        assertTimeout(Duration.ofMillis(200), () -> {
            tester.performTask(); 
        });
    } */
}
