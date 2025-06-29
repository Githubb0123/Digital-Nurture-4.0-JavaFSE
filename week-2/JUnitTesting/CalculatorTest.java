
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    public int add(int a, int b) {
        return a + b;
    }

    @Test
    public void testAddition() {
        CalculatorTest calculator = new CalculatorTest();
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }
}
