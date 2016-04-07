import exceptions.NoCashException;
import exceptions.WrongDenominateValueException;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CashMachineMessageHandler {

    private final CashMachine cashMachine;
    final ResourceBundle bundle;

    public CashMachineMessageHandler(@NotNull final Integer[] denominations) {
        this.cashMachine = new CashMachine(denominations);
        this.bundle = ResourceBundle.getBundle("MessagesBundle_en_EN");
    }

    public void run() {
        try (final Scanner scanner = new Scanner(System.in)) {
            System.out.print("> ");
            while (scanner.hasNextLine()) {
                final String output = handle(scanner.nextLine());

                if (output != null) {
                    if (output.equals("quit")) {
                        return;
                    }

                    System.out.println(output);
                }
                System.out.print("> ");
            }
        }
    }

    // package visibility only for testing

    String handle(@NotNull final String input) {
        String response = null;
        final String[] parsedInput = input.trim().split("\\s+");

        if (parsedInput.length < 1 || parsedInput.length > 3) {
            System.out.println(bundle.getString("err.incorrectInput"));
            System.out.println(help());
        }

        switch (parsedInput[0].toLowerCase()) {
            case "quit":
                if (parsedInput.length == 1)
                    response = "quit";
                else
                    response = bundle.getString("err.argumentsNumber") + "\n" + help();
                break;
            case "put":
                if (parsedInput.length == 3) {
                    try {
                        final Integer denomination = Integer.parseInt(parsedInput[1]);
                        final Integer count = Integer.parseInt(parsedInput[2]);
                        response = put(denomination, count);
                    } catch (NumberFormatException e) {
                        response = bundle.getString("err.put.wrongArguments");
                    }
                    break;
                } else {
                    response = bundle.getString("err.argumentsNumber") + "\n" + help();
                }

                break;
            case "get":
                try {
                    if (parsedInput.length == 2) {
                        final Integer amount = Integer.parseInt(parsedInput[1]);
                        response = get(amount);
                    } else {
                        response = bundle.getString("err.argumentsNumber") + "\n" + help();
                    }
                } catch (NumberFormatException e) {
                    response = bundle.getString("err.get.wrongAmount") + "\n" + help();
                }
                break;
            case "dump":
                response = dump();
                break;
            case "state":
                response = state();
                break;
            default:
                response = bundle.getString("err.incorrectInput") + "\n" + help();
                break;
        }

        return response;
    }

    private String put(@NotNull final Integer denomination, @NotNull final Integer count) {
        if (denomination <= 0 || count <= 0)
            return bundle.getString("err.put.wrongArguments");

        try {
            cashMachine.put(denomination, count);
        } catch (WrongDenominateValueException e) {
            return bundle.getString("err.put.wrongDenomination");
        }

        return state();
    }

    private String get(@NotNull final Integer amount) {
        if (amount <= 0)
            return bundle.getString("err.get.wrongAmount");

        try {
            final LinkedHashMap<Integer, Integer> cash = cashMachine.get(amount);
            if (!cash.isEmpty()) {
                final StringJoiner result = new StringJoiner(", ");
                final Integer realAmount = CashMachine.calculateAmount(cash);
                final Integer shortage = amount - realAmount;

                cash.forEach((denomination, count) -> result.add(denomination + "=" + count));
                result.add(bundle.getString("inTotal") + " " + realAmount);

                if (shortage.equals(0))
                    return result.toString();
                else
                    return result.toString() + "\n" + bundle.getString("outOf") + " " + shortage.toString();

            } else {
                return bundle.getString("noAppropriateBanknotes");
            }
        } catch (NoCashException e) {
            return bundle.getString("empty");
        }
    }

    private String dump() {
        final StringJoiner result = new StringJoiner("\n");

        cashMachine.getDump().forEach((denomination, count) ->
                result.add(String.format("%-4d %d", denomination, count)));

        return result.toString();
    }

    private String state() {
        return cashMachine.getState().toString();
    }

    private String help() {
        return bundle.getString("helpMessage");
    }
}
