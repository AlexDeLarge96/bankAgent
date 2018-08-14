public class Client {

    private String name;
    private OperationType operation;

    public Client(String name, OperationType operation) {
        this.name = name;
        this.operation = operation;
    }

    /**
     * Perform a client's operation through the Dispatcher
     * @param dispatcher Dispatcher that attend the clients
     */
    public void performOperation(Dispatcher dispatcher)
    {
        dispatcher.attend(this);
    }

    public String getName() { return name; }

    public OperationType getOperation() { return operation; }
}
