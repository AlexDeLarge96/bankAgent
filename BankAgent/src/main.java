import java.util.ArrayList;
import java.util.List;

public class main {

    private static List<Client> clients = new ArrayList<>();

    /**
     * Create a list of clients and a dispatcher object, then perform the operation of each client
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t----Beginning of the main----");
        createClients(10);
        Dispatcher dispatcher=new Dispatcher();
        for(Client client:clients) {
            client.performOperation(dispatcher);
        }
        dispatcher.finishDispatchersJob();
        System.out.println("\t----Ending of the main----");
    }

    /**
     * Add clients with a random operation type in the list of clients
     * @param numberOfClients Number of clients that will be added to the list of clients
     */
    private static void createClients(int numberOfClients) {
        for (int i = 1; i <= numberOfClients; i++) {
            clients.add(new Client("Client#" + i, getRandomOperationType()));
        }
    }

    /**
     * Get a random operation type between DEPOSIT, WITHDRAWAL or SOLVE_ISSUE for create a new client
     * @return A random operation type
     */
    private static OperationType getRandomOperationType() {
        switch ((int) (Math.random() * 3) + 1) {
            case 1:
                return OperationType.DEPOSIT;
            case 2:
                return OperationType.WITHDRAWAL;
            case 3:
                return OperationType.SOLVE_ISSUE;
            default:
                return null;
        }
    }
}
