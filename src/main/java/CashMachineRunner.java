public class CashMachineRunner {

    public static void main(String[] args) {
        final Integer[] denominations = {5000, 1000, 500, 100, 50, 25, 10, 5, 3, 1};
        final CashMachineMessageHandler cashMachineMessageHandler;

        cashMachineMessageHandler = new CashMachineMessageHandler(denominations);
        cashMachineMessageHandler.run();
    }
}
