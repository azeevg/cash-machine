import exceptions.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@SuppressWarnings("WeakerAccess")
public class CashMachine {

    private final LinkedHashMap<Integer, Integer> storedCash;

    public CashMachine(@NotNull final Integer[] denominations)
            throws EmptyDenominationArrayException, NotPositiveValueException,
            NotDecreasingValuesException, NotUniqueValuesException {

        if (denominations.length == 0) {
            throw new EmptyDenominationArrayException();
        }

        for (Integer denomination : denominations) {
            if (denomination <= 0)
                throw new NotPositiveValueException();
        }

        for (int i = 0; i < denominations.length - 1; i++) {
            if (denominations[i] < denominations[i + 1])
                throw new NotDecreasingValuesException();
            if (denominations[i].equals(denominations[i + 1]))
                throw new NotUniqueValuesException();
        }

        storedCash = new LinkedHashMap<>(denominations.length);
        Arrays.stream(denominations).forEach(value -> storedCash.put(value, 0));
    }

    public static Integer calculateAmount(@NotNull final Map<Integer, Integer> cash) {
        final Integer[] amount = {0};
        cash.forEach((denomination, count) -> amount[0] += denomination * count);
        return amount[0];
    }

    public Integer getState() {
        return calculateAmount(storedCash);
    }

    @SuppressWarnings("unchecked")
    public LinkedHashMap<Integer, Integer> getDump() {
        return (LinkedHashMap<Integer, Integer>) storedCash.clone();
    }

    public void put(@NotNull final Integer denomination, @NotNull final Integer count)
            throws NotPositiveValueException, WrongDenominateValueException {
        if (denomination <= 0 || count <= 0)
            throw new NotPositiveValueException();
        if (!storedCash.containsKey(denomination))
            throw new WrongDenominateValueException();

        storedCash.put(denomination, storedCash.get(denomination) + count);
    }

    public LinkedHashMap<Integer, Integer> get(@NotNull final Integer amount)
            throws NoCashException, NotPositiveValueException {
        if (amount <= 0)
            throw new NotPositiveValueException();
        if (getState().equals(0))
            throw new NoCashException();

        final int[] sum = {0};
        final LinkedHashMap<Integer, Integer> cash = new LinkedHashMap<>(storedCash.size() / 2);

        storedCash.forEach((denomination, maxCount) -> {

            if (denomination <= amount - sum[0] && maxCount != 0) {
                final int neededCount = (amount - sum[0]) / denomination;
                final int count = (maxCount < neededCount) ? maxCount : neededCount;

                sum[0] += denomination * count;
                storedCash.put(denomination, maxCount - count);
                cash.put(denomination, count);
            }
        });

        return cash;
    }


}
