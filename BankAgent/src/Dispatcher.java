import java.util.concurrent.*;

public class Dispatcher extends DispatcherObserver {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private BlockingQueue<Client> clientsRow = new LinkedBlockingQueue<>();

    /**
     * Initialize the dispatcher object adding a subject
     * and then adding the dispatcher like a subject's observer.
     * @param employees Subject for the dispatcher (observer).
     */
    public Dispatcher(Employees employees) {
        this.employees = employees;
        this.employees.addObserver(this);
    }

    /**
     * Looking for available agents and assign clients to them until the number of clients on wait will be zero.
     * If there aren't available agents then the client will be put on a waiting row until an agent is vacated.
     * Every that a future response from an agent is given, this response is shown on console.
     *
     * @param client The client on wait to be attend
     */
    public void attend(Client client) {
        Agent availableAgent = employees.getOneAgent();
        if (availableAgent != null) {
            availableAgent.setAssignedClient(client);
            CompletableFuture
                    .supplyAsync(availableAgent, executor)
                    .thenAccept(freeAgent -> {
                        System.out.println(freeAgent.getResponseForClient()
                                + "The operation was performed in " + freeAgent.getAttentionTime() + " seconds.\n");
                        employees.addAgent(freeAgent);
                    });
        } else {
            putClientInRow(client);
        }
    }

    @Override
    public void update() {
        attendNextClient();
    }

    private void putClientInRow(Client newClient) {
        clientsRow.add(newClient);
    }

    /**
     * Attend the next client on the waiting row until there aren't clients on hold.
     * Then shutdown the executor service.
     */
    private void attendNextClient() {
        if (clientsRow.size() > 0) {
            attend(clientsRow.remove());
        } else {
            executor.shutdown();
        }
    }
}
