import org.junit.Test;
import junit.framework.Assert;
public class StringCalculatorTest {
	

    @Test
    public final void whenDelimiterIsSpecified() {
        Assert.assertEquals(1+2+5, StringCalculator.add("//;n1;2;5"));
    }
}