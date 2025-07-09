import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AdditionTest {
    Calculator calc = new Calculator();

    @Test
    void testAddPositiveNumbers() {
        assertEquals(7, calc.add(3, 4));
    }

    @Test
    void testAddWithZero() {
        assertEquals(5, calc.add(5, 0));
    }
}
