import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher {
    private ExecutorService executor;
    private List<Agent> agents;

    /**
     * Initialize the thread pool (with a size of 10) and create a list of agents.
     * Around 6 cashiers, 3 supervisors and 1 director
     */
    public Dispatcher() {
        executor = Executors.newFixedThreadPool(10);
        agents = new ArrayList<>();
        createCashiers(6);
        createSupervisors(3);
        createDirectors(1);
    }

    /**
     * Looking for available agents and assign clients to them until the number of clients on wait will be zero.
     * If there aren't available agents then the client will be put on wait until an agent is vacated.
     * Every that a future response from an agent is given, this response is shown on console.
     *
     * @param client The client on wait to be attend
     */
    public void attend(Client client) {
        Agent availableAgent = lookingForAvailableAgent();
        if (availableAgent != null) {
            assignClientToAgent(availableAgent, client);
            CompletableFuture
                    .supplyAsync(availableAgent, executor)
                    .thenAccept(response -> System.out.println(response +
                            " The operation was performed in " + availableAgent.getAttentionTime() + " seconds."));
        } else {
            putClientOnWait(client);
        }
    }

    /**
     * Check the list of agents and return the value of one agent available
     * @return An available agent
     */
    private Agent lookingForAvailableAgent() {
        return agents.stream().filter(Agent::isAvailability).findFirst().orElse(null);
    }

    /**
     * Assign client to agent and change availability of this agent as false
     * @param agent Available Agent
     * @param client Client assigned to this agent
     */
    private void assignClientToAgent(Agent agent, Client client) {
        agent.setAssignedClient(client);
        agent.setAvailability(false);
    }

    /**
     * Sleep the main thread for 10 milliseconds and try again to attend the client on wait
     * @param client Client on wait
     */
    private void putClientOnWait(Client client) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        attend(client);
    }

    /**
     * Finish the executor service
     */
    public void finishDispatchersJob() {
        executor.shutdown();
    }

    /**
     * Add X number of cashiers to list of agents
     * @param numberOfCashiers Number of clients which will be added to the list of agents
     */
    private void createCashiers(int numberOfCashiers) {
        for (int i = 1; i <= numberOfCashiers; i++)
            agents.add(new Cashier("Cashier#" + i, true, null));
    }

    /**
     * Add X number of supervisors to list of agents
     * @param numberOfSupervisors Number of supervisors which will be added to the list of agents
     */
    private void createSupervisors(int numberOfSupervisors) {
        for (int i = 1; i <= numberOfSupervisors; i++)
            agents.add(new Supervisor("Supervisor#" + i, true, null));
    }

    /**
     * Add X number of directors to list of agents
     * @param numberOfDirectors Number of directors which will be added to the list of agents
     */
    private void createDirectors(int numberOfDirectors) {
        for (int i = 1; i <= numberOfDirectors; i++)
            agents.add(new Director("Director#" + i, true, null));
    }
}
