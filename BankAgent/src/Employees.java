import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Employees {
    private List<DispatcherObserver> observers = new ArrayList<>();
    private BlockingQueue<Agent> cashiers = new LinkedBlockingQueue<>();
    private BlockingQueue<Agent> supervisors = new LinkedBlockingQueue<>();
    private BlockingQueue<Agent> directors = new LinkedBlockingQueue<>();

    public Employees() {
        createCashiers(6);
        createSupervisors(3);
        createDirectors(1);
    }

    public void addObserver(DispatcherObserver dispatcher) {
        observers.add(dispatcher);
    }

    /**
     * After an agents has attended a client, is put on the queue new.
     *
     * @param agent An agent that now is free after attend a client.
     */
    public void addAgent(Agent agent) {
        if (agent.getTypeOfAgent() == TypeOfAgent.CASHIER) {
            cashiers.add(agent);
        } else if (agent.getTypeOfAgent() == TypeOfAgent.SUPERVISOR) {
            supervisors.add(agent);
        } else {
            directors.add(agent);
        }
        execute();
    }

    /**
     * Check the list of agents and return the value of one agent available.
     * The returned agents is removed of the agents queue.
     *
     * @return An available agent
     */
    public Agent getOneAgent() {
        if (cashiers.size() > 0) {
            return cashiers.remove();
        } else if (supervisors.size() > 0) {
            return supervisors.remove();
        } else if (directors.size() > 0) {
            return directors.remove();
        } else {
            return null;
        }
    }

    /**
     * Notify the observers that the subject has updated its state
     */
    private void execute() {
        for (DispatcherObserver dispatcher : observers) dispatcher.update();
    }

    private void createCashiers(int numberOfCashiers) {
        for (int i = 1; i <= numberOfCashiers; i++)
            cashiers.add(new Agent("Cashier#" + (cashiers.size() + 1), TypeOfAgent.CASHIER));
    }

    private void createSupervisors(int numberOfSupervisors) {
        for (int i = 1; i <= numberOfSupervisors; i++)
            supervisors.add(new Agent("Supervisor#" + (supervisors.size() + 1), TypeOfAgent.SUPERVISOR));
    }

    private void createDirectors(int numberOfDirectors) {
        for (int i = 1; i <= numberOfDirectors; i++)
            directors.add(new Agent("Director#" + (directors.size() + 1), TypeOfAgent.DIRECTOR));
    }
}
