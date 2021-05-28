import org.junit.Test;
import junit.framework.Assert;

public class StringCalculatorTest {



    @Test
    public final void whenStringIsEmpty(){
        Assert.assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public final void whenAnyNumberOfNumbersUsed() {
        Assert.assertEquals(3+6+15+18+46+33, StringCalculator.add("3,6,15,18,46,33"));
    }

    @Test
    public final void whenNewLineIsUsed() {
        Assert.assertEquals(9+2+5, StringCalculator.add("9,2n5"));
    }

    @Test
    public final void whenDelimiterIsSpecified() {
        Assert.assertEquals(1+2, StringCalculator.add("//;\n1;2"));
    }
}