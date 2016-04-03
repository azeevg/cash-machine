import exceptions.EmptyDenominationArrayException;
import exceptions.NegativeDenominationException;
import exceptions.NotDecreasingDenominationsException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by gleb on 4/3/16.
 */

@RunWith(Parameterized.class)
public class CashMachineKernelTest {

//    public static final Boolean IS_CORRECT = true;
//    public static final Boolean IS_INCORRECT = false;

    public static Object getIntegersArray(final int[] ints) {
        final Integer[] integers = new Integer[ints.length];
        for (int i = 0; i < ints.length; i++) {
            integers[i] = ints[i];
        }
        return integers;
    }

    public static final Object[][] CONSTRUCTOR_TEST_DATA = {
            {null, getIntegersArray(new int[]{5000, 1000, 500, 250, 100,  50})},
            {null, getIntegersArray(new int[]{10})},
            {EmptyDenominationArrayException.class, getIntegersArray(new int[]{})},
            {NegativeDenominationException.class, getIntegersArray(new int[]{-100, 10})},
            {NotDecreasingDenominationsException.class, getIntegersArray(new int[]{10, 1000, 20})}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(CONSTRUCTOR_TEST_DATA);
    }

    private Class exceptionClass;
    private Integer[] denominations;

    public CashMachineKernelTest(final Class exceptionClass, final Integer[] denominations) {
        this.exceptionClass = exceptionClass;
        this.denominations = denominations;
    }

    @Test
    public void constructor() throws Exception {
        if (exceptionClass != null) {
            CashMachineKernel kernel = new CashMachineKernel(denominations);
        } else {
            try {
                CashMachineKernel kernel = new CashMachineKernel(denominations);
            } catch (IllegalArgumentException exc) {
                Assert.assertEquals(exc.getClass(), exceptionClass);
            }
        }
    }


    @Test
    public void getState() throws Exception {

    }

    @Test
    public void getStoredCash() throws Exception {

    }

    @Test
    public void depositCash() throws Exception {

    }

    @Test
    public void withdrawCash() throws Exception {

    }

}