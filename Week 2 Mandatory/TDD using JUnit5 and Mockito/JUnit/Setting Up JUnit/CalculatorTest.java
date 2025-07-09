import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calc = new Calculator();
        int result = calc.add(10, 5);
        assertEquals(15, result);
    }

    @Test
    public void testMultiplication() {
        Calculator calc = new Calculator();
        int result = calc.multiply(4, 3);
        assertEquals(12, result);
    }
}
