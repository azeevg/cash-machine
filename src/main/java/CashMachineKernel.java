import exceptions.EmptyDenominationArrayException;
import exceptions.NotDecreasingDenominationValuesException;
import exceptions.NotPositiveValueException;
import exceptions.NotUniqueDenominationValuesException;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

public class CashMachineKernel {

    //    private static final Integer[] denominations = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};
    private final LinkedHashMap<Integer, Integer> storedCash;

    public CashMachineKernel(@NotNull final Integer[] denominations) {

        if (denominations.length == 0) {
            throw new EmptyDenominationArrayException();
        }

        for (Integer denomination : denominations) {
            if (denomination <= 0)
                throw new NotPositiveValueException();
        }

        for (int i = 0; i < denominations.length - 1; i++) {
            if (denominations[i] < denominations[i + 1])
                throw new NotDecreasingDenominationValuesException();
            if (denominations[i].equals(denominations[i + 1]))
                throw new NotUniqueDenominationValuesException();
        }

        storedCash = new LinkedHashMap<>(denominations.length);
        Arrays.stream(denominations).forEach(value -> storedCash.put(value, 0));
    }


    public Integer getState() {
        final Integer[] amount = {0};
        storedCash.forEach((denomination, count) -> amount[0] += denomination * count);
        return amount[0];
    }

    @NotNull
    public LinkedHashMap<Integer, Integer> getStoredCash() {
        // or storedCash.clone(); ???
        return storedCash;
    }


    public void depositCash(@NotNull final Integer denomination, @NotNull final Integer count) {
        int i = denomination;
    }

    public Collection<Integer> withdrawCash(final int amount) {
        return storedCash.values();
    }

}
