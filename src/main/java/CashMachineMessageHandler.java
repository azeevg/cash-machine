import org.jetbrains.annotations.NotNull;

public class CashMachineMessageHandler {

    private final CashMachine cashMachine;
    public CashMachineMessageHandler(@NotNull Integer[] denominations) {
        cashMachine = new CashMachine(denominations);
    }

    public void run() {

    }

    // package visibility only for testing

    String handle(@NotNull final String input) {
        return "res";
    }

    String put(@NotNull final Integer denomination, @NotNull final Integer count) {
        return "res";
    }

    String get(@NotNull final Integer amount) {
        return "res";

    }

    String dump() {
        return "res";

    }

    String state() {
        return "res";
    }

    String help() {
        return "help";
    }


}
