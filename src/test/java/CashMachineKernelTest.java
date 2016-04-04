import exceptions.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;

public class CashMachineKernelTest {

    private static final Integer[] DENOMINATIONS = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};

    // constructor tests
    @Test
    public void constructor() throws Exception {
        Integer[] denominations = {5000, 1000, 500, 250, 100, 50};
        new CashMachineKernel(denominations);
        denominations = new Integer[]{10};
        new CashMachineKernel(denominations);
    }

    @Test(expected = EmptyDenominationArrayException.class)
    public void emptyDenominationsArrayException() {
        Integer[] denominations = new Integer[]{};
        new CashMachineKernel(denominations);
    }

    @Test(expected = NotDecreasingDenominationValuesException.class)
    public void notDecreasingDenominationsException() {
        Integer[] denominations = new Integer[]{1000, 500, 600};
        new CashMachineKernel(denominations);
    }

    @Test(expected = NotPositiveValueException.class)
    public void notPositiveDenominationException() {
        Integer[] denominations = new Integer[]{-10, 2};
        new CashMachineKernel(denominations);
    }

    @Test(expected = NotUniqueDenominationValuesException.class)
    public void NotUniqueDenominationsException() {
        Integer[] denominations = new Integer[]{100, 50, 50, 30};
        new CashMachineKernel(denominations);
    }

    // getState test
    @Test
    public void getStateZero() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        Assert.assertEquals(cashMachine.getState().intValue(), 0);
    }


    // depositCash tests
    @Test(expected = WrongDenominateValueException.class)
    public void depositCashWrongDenominateValue() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        cashMachine.depositCash(42, 4);
    }

    @Test(expected = WrongDenominateValueException.class)
    public void depositCashNegativeDenominateValue() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        cashMachine.depositCash(-10, 4);
    }

    @Test(expected = NotPositiveValueException.class)
    public void depositCashNegativeCountValue() throws Exception {
        final Integer[] denominations = {10, 5};
        final CashMachineKernel cashMachine = new CashMachineKernel(denominations);
        cashMachine.depositCash(10, -4);
    }

    @Test(expected = NotPositiveValueException.class)
    public void depositCashZeroCountValue() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        cashMachine.depositCash(10, 0);
    }

    @Test
    public void depositCash() throws Exception {
        final Integer[] denominations = {10, 5};
        final CashMachineKernel cashMachine = new CashMachineKernel(denominations);
        cashMachine.depositCash(10, 2);
        Assert.assertEquals(cashMachine.getState().intValue(), 10 * 2);
    }

    // getStoredCash test
    @Test
    public void getStoredCash() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        Arrays.stream(DENOMINATIONS).forEach(denomination ->
                cashMachine.depositCash(denomination, 1));
        final LinkedHashMap<Integer, Integer> initialStoredCash = cashMachine.getStoredCash();
        Arrays.stream(DENOMINATIONS).forEach(denomination ->
                cashMachine.depositCash(denomination, 1));
        final LinkedHashMap<Integer, Integer> storedCash = cashMachine.getStoredCash();

        Arrays.stream(DENOMINATIONS).forEach(denomination -> {
                    final int current = storedCash.get(denomination);
                    final int initial = initialStoredCash.get(denomination);
                    Assert.assertEquals(current / 2, initial);
                }
        );
    }

    // withdrawCash
    @Test(expected = NotPositiveValueException.class)
    public void withdrawCashZeroAmount() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        cashMachine.depositCash(10, 0);
        cashMachine.withdrawCash(0);
    }

    @Test(expected = NotPositiveValueException.class)
    public void withdrawCashNegativeAmount() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        cashMachine.depositCash(10, 0);
        cashMachine.withdrawCash(-10);
    }

    @Test(expected = EmptyCashMachineException.class)
    public void withdrawCashEmptyCashMachine() throws Exception {
        final CashMachineKernel cashMachine = new CashMachineKernel(DENOMINATIONS);
        cashMachine.withdrawCash(0);
    }


}