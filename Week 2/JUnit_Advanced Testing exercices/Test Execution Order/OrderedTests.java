import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    void initialize() {
        System.out.println("1 - Initialization");
        assertTrue(true);
    }

    @Test
    @Order(2)
    void processData() {
        System.out.println("2 - Processing Data");
        assertEquals(10, 5 + 5);
    }

    @Test
    @Order(3)
    void finalizeProcess() {
        System.out.println("3 - Finalizing Process");
        assertNotNull("Test Finished");
    }
}
