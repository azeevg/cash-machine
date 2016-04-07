import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MessageHandlerTest {
    private static final Integer[] DENOMINATIONS = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};

    private static final String PUT_REGEX = "^\\d$";
    private static final String GET_REGEX = "^(\\d=\\d,)+ in all \\d\n(out of \\d)?$";
    private static final String DUMP_REGEX = "^(\\d=\\d)\n?{" + DENOMINATIONS.length + "}$";
    private static final String STATE_REGEX = PUT_REGEX;
    private static final String HELP = "help";
    private static final String EMPTY = "empty";
    private static final String NOT_POSITIVE = "not positive";
    private static final String WRONG_DENOMINATION = "wrong denomination";
    private static final String NO_APPROPRIATE_BANKNOTES = "no appropriate banknotes";

    private static final Object[][] TEST_DATA = {
            {"get 1000", EMPTY},
            {"get -123", EMPTY},
            {"get 0", EMPTY},
            {"get .45", HELP},
            {"state", "0"},
            {"dump", DUMP_REGEX},
            {"foo foo", HELP},
            {"get 500 11", HELP},
            {"100", HELP},
            {"put", HELP},
            {"put 11343 4", WRONG_DENOMINATION},
            {"put -12 4", NOT_POSITIVE},
            {"put 0 4", NOT_POSITIVE},
            {"put 10 -5", NOT_POSITIVE},
            {"put 2hfsgh", HELP},
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
            {"get", HELP},
            {"get -14", NOT_POSITIVE},
            {"get 627", GET_REGEX},
            {"get 11", GET_REGEX},
            {"get 1491", GET_REGEX},
            {"state", STATE_REGEX},
            {"dump", DUMP_REGEX},
            {"get 15000", GET_REGEX},
            {"get 12", NO_APPROPRIATE_BANKNOTES},
            {"get 1", NO_APPROPRIATE_BANKNOTES},
            {"quit now", HELP}
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

    @Test
    public void handle() throws Exception {
        final InputStream inputStream = System.in;
        final OutputStream outputStream = System.out;
        final CashMachineMessageHandler handler = new CashMachineMessageHandler(DENOMINATIONS);

        Assert.assertTrue(handler.handle(input).matches(regex));
    }


}