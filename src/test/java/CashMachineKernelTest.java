import exceptions.EmptyDenominationArrayException;
import exceptions.NotPositiveDenominationException;
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

    @Test
    public void constructor() throws Exception {
        Integer[] denominations = {5000, 1000, 500, 250, 100, 50};
        new CashMachineKernel(denominations);

        denominations = new Integer[]{10};
        new CashMachineKernel(denominations);
    }

    @Test(expected = EmptyDenominationArrayException.class)
    public void emptyDenominationsArrayExceptionTest() {
        Integer[] denominations = new Integer[]{};
        new CashMachineKernel(denominations);
    }
    @Test(expected = NotDecreasingDenominationsException.class)
    public void notDecreasingDenominationsExceptionTest() {
        Integer[] denominations = new Integer[]{1000, 500, 600};
        new CashMachineKernel(denominations);
    }
    @Test(expected = NotPositiveDenominationException.class)
    public void notPositiveDenominationExceptionTest() {
        Integer[] denominations = new Integer[]{-10, 2};
        new CashMachineKernel(denominations);
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