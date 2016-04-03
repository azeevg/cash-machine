import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by gleb on 4/3/16.
 */

public class CashMachineKernel {

    //    private static final Integer[] denominations = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};
    private final LinkedHashMap<Integer, Integer> storedCash;

    public CashMachineKernel(@NotNull final Integer[] denominations) {

        if (denominations.length == 0) {
            throw new IllegalArgumentException();
        }

        storedCash = new LinkedHashMap<>(denominations.length);

        // check no repeated numbers and negatives
//        Arrays.stream(denominations).forEach(value -> storedCash.put(value, 0));
    }


    public long getState() {
        return 42;
    }

    @NotNull
    public LinkedHashMap<Integer, Integer> getStoredCash() {
        // or storedCash.clone(); ???
        return storedCash;
    }


    public long depositCash(@NotNull final Integer denomination, @NotNull final Integer count) {
        int i = denomination;
        return getState();
    }

    @NotNull
    public Collection<Integer> withdrawCash(@NotNull final Long amount) {
        return storedCash.values();
    }

}
