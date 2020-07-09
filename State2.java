import java.util.*;

public class State2 extends State {

    @Override
    public ResultState process(String input, String command) {
        String[] commands = CommandList.getCommands();
        String[] keys = CommandList.getKeys();
        ResultState resultState = new ResultState();

        resultState.setNextMessage("How can I help you?");
        resultState.setCommand("");
        resultState.setNextState(0);
	
	if (Arrays.asList(keys).contains(input.toUpperCase())) {
        	for (int i = 0; i < keys.length; i++) {
    			if(input.toUpperCase().equals(keys[i])) {
    				input = commands[i];
    			}
    		}
        	resultState.setNextState(2);
            resultState.setCommand(input);
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        }    
        else if (Arrays.asList(commands).contains(input.toLowerCase())) {
            resultState.setNextState(1);
            resultState.setCommand(input);
            resultState.setNextMessage("I would like to confirm if you wish to " + input + ".");
        } else if (input.toLowerCase().contains("yes") && Arrays.asList(commands).contains(command)) {
            resultState.setNextState(2);
            resultState.setCommand(command.toLowerCase());
            resultState.setNextMessage(CommandList.printCommandResponse(command));
        }

        return resultState;
    }


}
