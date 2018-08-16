public class Client {

    private String name;
    private OperationType operation;

    public Client(String name, OperationType operation) {
        this.name = name;
        this.operation = operation;
    }

    /**
     * Perform the client's operation through a dispatcher
     * @param dispatcher Dispatcher object that attend the client on first instance
     */
    public void performOperation(Dispatcher dispatcher) { dispatcher.attend(this); }

    public String getName() { return name; }

    public OperationType getOperation() { return operation; }
}
