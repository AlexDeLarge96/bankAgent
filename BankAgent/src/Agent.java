import java.util.function.Supplier;

public class Agent implements Supplier<String> {

    private String name;
    private boolean availability;
    private Client assignedClient;
    private int attentionTime;

    public Agent(String name, boolean availability, Client assignedClient) {
        this.name = name;
        this.availability = availability;
        this.assignedClient = assignedClient;
    }

    /**
     * Override the get method from the Supplier Class for attend the clients
     * @return The response of the method attend client
     */
    @Override
    public String get() {
        return attendClient();
    }

    /**
     * Attend the client for about a random time and give him an answer
     * @return The response for the client (String)
     */
    public String attendClient() {
        try {
            setAttentionTime((int) (Math.random() * 5) + 10);
            Thread.sleep(attentionTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = assignedClient.getName() + " has been attended by " + name
                + " and has performed a " + assignedClient.getOperation() + " operation.";
        releaseClient();
        return result;
    }


    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public void setAttentionTime(int attentionTime) { this.attentionTime = attentionTime; }

    public int getAttentionTime() { return attentionTime; }

    /**
     * Release the client that was attended and set availability of the agent as true
     */
    private void releaseClient() {
        setAssignedClient(null);
        setAvailability(true);
    }
}
