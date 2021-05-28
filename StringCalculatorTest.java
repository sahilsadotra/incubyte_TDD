import org.junit.Test;

public class StringCalculatorTest {

    @Test(expected = RuntimeException.class)
    public final void whenMoreThanTwoNumberUsed(){
        StringCalculator.add("1,4,6,7");
    }
}