import java.util.*;

public class State1 extends State {

    @Override
    public ResultState process(String input, String command) {
        String[] commands = CommandList.getCommands();
        String[] keys = CommandList.getKeys();
        ResultState resultState = new ResultState();
        String list = "List of Request Commands: ";
        for (String str : commands) {
            list = list + "\n" + str;
	}

        resultState.setNextMessage(list);
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
            resultState.setCommand(input.toLowerCase());
            resultState.setNextMessage("I would like to confirm if you wish to " + input.toLowerCase() + ".");
        }


        return resultState;
    }


}
