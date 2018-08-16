public class main {

    /**
     * Create clients, employees and a dispatcher object, then perform the operation of each client
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t----Beginning of the main----");
        Employees employees=new Employees();
        Dispatcher dispatcher = new Dispatcher(employees);
        Client actualClient;
        for(int i=1;i<=20;i++)
        {
            actualClient=createClient(i);
            actualClient.performOperation(dispatcher);
        }
        System.out.println("\t----Ending of the main----");
    }

    /**
     * Create client with a random operation type.
     * @param numberOfClient Client's ID.
     * @return A new Client which was created.
     */
    private static Client createClient(int numberOfClient) {
        return new Client("Client#" + numberOfClient, getRandomOperationType());
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
