import org.junit.Test;

import junit.framework.Assert;

public class StringCalculatorTest {
    

    @Test
    public final void whenAnyNumberOfNumbersUsed() {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3,6,15,18,46,33"));
    }
}