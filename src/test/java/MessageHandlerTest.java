import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

@RunWith(Parameterized.class)
public class MessageHandlerTest {
    private static final Integer[] DENOMINATIONS = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};

    private static final ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle_en_EN");

    private static final String PUT_REGEX = "^\\d+$";
    private static final String GET_REGEX = "(\\d+=\\d+, )+" + bundle.getString("inTotal") + " \\d+\n?(" + bundle.getString("outOf") + " \\d+)?";
    //    private static final String GET_REGEX = "(\\d+=\\d+,\\s)+(in)\\s(total)\\s\\d+\n?(out\\sof\\s\\d+)?";
    private static final String DUMP_REGEX = "((\\d+(\\s+)\\d+)\\s?)+";
    private static final String STATE_REGEX = PUT_REGEX;
    private static final String HELP = bundle.getString("helpMessage");
    private static final String EMPTY = bundle.getString("empty");
    private static final String NOT_POSITIVE = bundle.getString("err.put.wrongArguments");
    private static final String WRONG_DENOMINATION = bundle.getString("err.put.wrongDenomination");
    private static final String NO_APPROPRIATE_BANKNOTES = bundle.getString("noAppropriateBanknotes");
    private static final String WRONG_AMOUNT = bundle.getString("err.get.wrongAmount");
    private static final String INCORRECT_INPUT = bundle.getString("err.incorrectInput");
    private static final String ARGUMENTS_NUMBER = bundle.getString("err.argumentsNumber");

    private static final Object[][] TEST_DATA = {
            {"get 1000", EMPTY},
            {"get -123", WRONG_AMOUNT},
            {"get 0", WRONG_AMOUNT},
            {"get .45", WRONG_AMOUNT + "\n" + HELP},
            {"state", "0"},
            {"dump", DUMP_REGEX},
            {"foo foo", INCORRECT_INPUT + "\n" + HELP},
            {"get 500 11", ARGUMENTS_NUMBER + "\n" + HELP},
            {"100", INCORRECT_INPUT + "\n" + HELP},
            {"put", ARGUMENTS_NUMBER + "\n" + HELP},
            {"put 11343 4", WRONG_DENOMINATION},
            {"put -12 4", NOT_POSITIVE},
            {"put 0 4", NOT_POSITIVE},
            {"put 10 -5", NOT_POSITIVE},
            {"put 2hfsgh", ARGUMENTS_NUMBER + "\n" + HELP},
            {"put 1 5", PUT_REGEX},
            {"put 3 2", PUT_REGEX},
            {"put 5 1", PUT_REGEX},
            {"put 10 6", PUT_REGEX},
            {"put 25 3", PUT_REGEX},
            {"put 50 6", PUT_REGEX},
            {"put 100 3", PUT_REGEX},
            {"put 500 2", PUT_REGEX},
            {"put 1000 3", PUT_REGEX},
            {"put 5000 2", PUT_REGEX},
            {"dump", DUMP_REGEX},
            {"state", STATE_REGEX},
            {"get", ARGUMENTS_NUMBER + "\n" + HELP},
            {"get -14", WRONG_AMOUNT},
            {"get 627", GET_REGEX},
            {"get 11", GET_REGEX},
            {"get 1491", GET_REGEX},
            {"state", STATE_REGEX},
            {"dump", DUMP_REGEX},
            {"get 15000", GET_REGEX},
            {"get 12", EMPTY},
            {"get 1", EMPTY},
            {"quit now", ARGUMENTS_NUMBER + "\n" + HELP},
            {"quit", "quit"}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(TEST_DATA);
    }

    private String input;
    private String regex;


    public MessageHandlerTest(final String input, final String regex) {
        this.input = input;
        this.regex = regex;
    }

    static final CashMachineMessageHandler handler = new CashMachineMessageHandler(DENOMINATIONS);

    @Test
    public void handle() throws Exception {
        final String output = handler.handle(input);
        if (!output.matches(regex))
            System.out.println(input + " : " + output);
        Assert.assertTrue(output.matches(regex));
    }


}