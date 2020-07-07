public class ResultState {
    String nextMessage;
    String command;
    int nextState;

    public void setNextMessage(String nextMessage) {
        this.nextMessage = nextMessage;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public String getNextMessage() {
        return nextMessage;
    }

    public String getCommand() {
        return command;
    }

    public int getNextState() {
        return nextState;
    }
}
