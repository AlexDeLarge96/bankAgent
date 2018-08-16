import java.util.function.Supplier;

public class Agent implements Supplier<Agent> {

    private String name;
    private TypeOfAgent typeOfAgent;
    private Client assignedClient;
    private String responseForClient;
    private int attentionTime;

    public Agent(String name, TypeOfAgent typeOfAgent) {
        this.name = name;
        this.typeOfAgent = typeOfAgent;
        this.assignedClient = null;
    }

    /**
     * Override the get method from the Supplier Class for attend the clients
     *
     * @return The response of the method attend client
     */
    @Override
    public Agent get() {
        return attendClient();
    }

    /**
     * Attend the client for about a random time and give him an answer.
     * Then release the client.
     *
     * @return The agent which attend the client.
     */
    private Agent attendClient() {
        try {
            setAttentionTime((int) (Math.random() * 5) + 10);
            Thread.sleep(attentionTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setResponseForClient(assignedClient.getName() + " has been attended by " + name
                + " and has performed a " + assignedClient.getOperation() + " operation.");
        setAssignedClient(null);
        return this;
    }

    public String getResponseForClient() { return responseForClient; }

    public void setResponseForClient(String responseForClient) { this.responseForClient = responseForClient; }

    public TypeOfAgent getTypeOfAgent() { return typeOfAgent; }

    public int getAttentionTime() { return attentionTime; }

    public void setAttentionTime(int attentionTime) { this.attentionTime = attentionTime; }

    public void setAssignedClient(Client assignedClient) { this.assignedClient = assignedClient; }
}
