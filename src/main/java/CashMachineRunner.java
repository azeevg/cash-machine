import java.io.InputStream;
import java.io.OutputStream;

public class CashMachineRunner {

    public static void main(String[] args) {
        final Integer[] denominations = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};
        final InputStream inputStream = System.in;
        final OutputStream outputStream = System.out;
        final CashMachineMessageHandler cashMachineMessageHandler;

        cashMachineMessageHandler = new CashMachineMessageHandler(inputStream, outputStream, denominations);
        cashMachineMessageHandler.run();
    }
}
