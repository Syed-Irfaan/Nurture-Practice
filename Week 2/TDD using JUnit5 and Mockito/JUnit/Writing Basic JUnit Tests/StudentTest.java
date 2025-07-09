import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void testIsAdult_True() {
        Student student = new Student("Gowtham", 20);
        assertTrue(student.isAdult());
    }

    @Test
    public void testIsAdult_False() {
        Student student = new Student("Kumar", 15);
        assertFalse(student.isAdult());
    }

    @Test
    public void testGetUpperCaseName() {
        Student student = new Student("sam", 22);
        assertEquals("SAM", student.getUpperCaseName());
    }

    @Test
    public void testGetAgeInMonths() {
        Student student = new Student("Rahul", 2);
        assertEquals(24, student.getAgeInMonths());
    }
}
