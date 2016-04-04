import exceptions.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CashMachineTest {

    private static final Integer[] DENOMINATIONS = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};

    // constructor tests
    @Test
    public void constructor() throws Exception {
        Integer[] denominations = {5000, 1000, 500, 250, 100, 50};
        new CashMachine(denominations);
        denominations = new Integer[]{10};
        new CashMachine(denominations);
    }

    @Test(expected = EmptyDenominationArrayException.class)
    public void emptyDenominationsArrayException() {
        Integer[] denominations = new Integer[]{};
        new CashMachine(denominations);
    }

    @Test(expected = NotDecreasingDenominationValuesException.class)
    public void notDecreasingDenominationsException() {
        Integer[] denominations = new Integer[]{1000, 500, 600};
        new CashMachine(denominations);
    }

    @Test(expected = NotPositiveValueException.class)
    public void notPositiveDenominationException() {
        Integer[] denominations = new Integer[]{-10, 2};
        new CashMachine(denominations);
    }

    @Test(expected = NotUniqueDenominationValuesException.class)
    public void NotUniqueDenominationsException() {
        Integer[] denominations = new Integer[]{100, 50, 50, 30};
        new CashMachine(denominations);
    }

    // getState test
    @Test
    public void getStateZero() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        Assert.assertEquals(cashMachine.getState().intValue(), 0);
    }

    @Test
    public void getStateNegative() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(5000, 1);
        cashMachine.put(100, 4);
        cashMachine.put(10, 10);
        Assert.assertTrue(cashMachine.getState() >= 0);
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    public void rightState() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(500, 1);
        cashMachine.put(100, 2);
        cashMachine.put(10, 6);
        cashMachine.put(1, 5);
        Assert.assertEquals(cashMachine.getState().intValue(), 100  * 2 + 10 * 6 + 500 * 1 + 1 * 5);
    }
        // put tests
    @Test(expected = WrongDenominateValueException.class)
    public void putWrongDenominateValue() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(42, 4);
    }

    @Test(expected = NotPositiveValueException.class)
    public void putNegativeDenominateValue() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(-10, 4);
    }

    @Test(expected = NotPositiveValueException.class)
    public void putNegativeCountValue() throws Exception {
        final Integer[] denominations = {10, 5};
        final CashMachine cashMachine = new CashMachine(denominations);
        cashMachine.put(10, -4);
    }

    @Test(expected = NotPositiveValueException.class)
    public void putZeroCountValue() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(10, 0);
    }

    @Test
    public void put() throws Exception {
        final Integer[] denominations = {10, 5};
        final CashMachine cashMachine = new CashMachine(denominations);
        cashMachine.put(10, 2);
        Assert.assertEquals(cashMachine.getState().intValue(), 10 * 2);
    }

    // getStoredCash test
    @Test
    public void getStoredCash() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        Arrays.stream(DENOMINATIONS).forEach(denomination ->
                cashMachine.put(denomination, 1));
        final LinkedHashMap<Integer, Integer> initialStoredCash = cashMachine.getStoredCash();
        Arrays.stream(DENOMINATIONS).forEach(denomination ->
                cashMachine.put(denomination, 1));
        final LinkedHashMap<Integer, Integer> storedCash = cashMachine.getStoredCash();

        Arrays.stream(DENOMINATIONS).forEach(denomination -> {
                    final int current = storedCash.get(denomination);
                    final int initial = initialStoredCash.get(denomination);
                    Assert.assertEquals(current / 2, initial);
                }
        );
    }

    @Test
    public void getStoredCashNull() {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        System.out.println(cashMachine.getStoredCash());
        LinkedHashMap<Integer, Integer> storedCash = cashMachine.getStoredCash();
        storedCash.put(5000, 10000);
        System.out.println(cashMachine.getStoredCash());
        Assert.assertNotEquals(cashMachine.getStoredCash().get(5000).intValue(), 10000);
    }

    // get
    @Test(expected = NotPositiveValueException.class)
    public void getZeroAmount() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(10, 0);
        cashMachine.get(0);
    }

    @Test(expected = NotPositiveValueException.class)
    public void getNegativeAmount() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(10, 0);
        cashMachine.get(-10);
    }

    @Test(expected = NoCashException.class)
    public void getEmptyCashMachine() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.get(0);
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    @Test
    public void getExactAmount() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(500, 1);
        cashMachine.put(100, 2);
        cashMachine.put(10, 6);
        cashMachine.put(1, 5);

        final Integer amount = 615;
        final LinkedHashMap<Integer, Integer> cash = cashMachine.get(amount);
        Assert.assertEquals(amount, CashMachine.calculateAmount(cash));

        final LinkedHashMap<Integer, Integer> receivedCash = new LinkedHashMap<>(DENOMINATIONS.length / 2);
        receivedCash.put(500, 1);
        receivedCash.put(100, 1);
        receivedCash.put(10, 1);
        receivedCash.put(1, 5);

        Assert.assertTrue(receivedCash.equals(cash));

        final LinkedHashMap<Integer, Integer> storedCash = cashMachine.getStoredCash();
        Assert.assertEquals(storedCash.get(500), Integer.valueOf(0));
        Assert.assertEquals(storedCash.get(100), Integer.valueOf(1));
        Assert.assertEquals(storedCash.get(10), Integer.valueOf(5));
        Assert.assertEquals(storedCash.get(1), Integer.valueOf(0));
    }

    @Test
    public void getAllCash() throws Exception {
        final CashMachine cashMachine = new CashMachine(DENOMINATIONS);
        cashMachine.put(500, 1);
        cashMachine.put(100, 2);
        cashMachine.put(10, 6);
        cashMachine.put(1, 5);

        final Integer amount = 1000;
        final LinkedHashMap<Integer, Integer> cash = cashMachine.get(amount);
        Assert.assertEquals(amount, CashMachine.calculateAmount(cash));

        final LinkedHashMap<Integer, Integer> receivedCash = new LinkedHashMap<>(DENOMINATIONS.length);
        receivedCash.put(500, 1);
        receivedCash.put(100, 2);
        receivedCash.put(10, 6);
        receivedCash.put(1, 5);

        Assert.assertTrue(receivedCash.equals(cash));
        Assert.assertTrue(cashMachine.getState().equals(0));
    }
}