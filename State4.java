import java.util.Arrays;

public class State4 extends StateS {

    @Override
    public ResultState process(String input, String command) {
    	String[] commands = CommandList.getCommands();
        String[] keys = CommandList.getKeys();
        String[] entries = input.split(" ");
        String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);
        ResultState resultState = new ResultState();
        
        if (input.toLowerCase().contains("yes")) {
            resultState.setNextState(0);
            resultState.setCommand(command);
            DataRetrieverClient client = new DataRetrieverClient("localhost", 5000);
            String num = DataRetrieverClient.getString();
            resultState.setNextMessage(num + "\n"  + "Anything else I can help you with?");
        } 
        else if (input.toLowerCase().contains("no")) {
        	resultState.setNextState(2);
            resultState.setCommand(command);
            resultState.setNextMessage(CommandList.printCommandResponse(command));
        } 
        else if (Arrays.asList(CommandList.getCommands()).contains(input.toLowerCase())) {
            resultState.setNextState(2);
            resultState.setCommand(input);
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        }
        else if (Arrays.asList(keys).contains(entries[0].toUpperCase())) {
        	for (int i = 0; i < keys.length; i++) {
    			if(entries[0].toUpperCase().equals(keys[i])) {
    				entries[0] = commands[i];
    			}
    		}
        	int m = CommandList.validateKeywords(entries[0], modifiedEntries);
        	if (modifiedEntries.length == m) {
                ChatServer.setData(modifiedEntries);
                resultState.setNextState(3);
                resultState.setCommand(entries[0]);
                resultState.setNextMessage("Confirming request to " + input + ".");
            } else if (modifiedEntries.length < m){
                resultState.setNextState(0);
                resultState.setCommand(entries[0]);
                resultState.setNextMessage("You lack a field kindly re-enter the requested information.");
            } else {
                resultState.setNextState(0);
                resultState.setCommand(entries[0]);
                resultState.setNextMessage("Invalid input. Please try again.");
            }
        }
        else {
        	resultState.setNextState(0);
        	resultState.setCommand("");
        	resultState.setNextMessage("How can I help you?");
        }
        return resultState;

    }

}
