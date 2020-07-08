import java.util.Arrays;

public class State4 extends State {

    @Override
    public ResultState process(String input, String command) {
        ResultState resultState = new ResultState();

        if (input.toLowerCase().contains("yes")) {
            resultState.setNextState(0);
            resultState.setCommand(command);
            String[] data = Main.getData();
            resultState.setNextMessage(command + " " + data[0] + "\n" + "Anything else I can help you with?");
        } 
        else if (Arrays.asList(CommandList.getCommands()).contains(input.toLowerCase())) {
            resultState.setNextState(2);
            resultState.setCommand(input);
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        }
        else {
        	resultState.setNextState(0);
        	resultState.setCommand("");
        	resultState.setNextMessage("How can I help you?");           
        }
        return resultState;

    }

}
