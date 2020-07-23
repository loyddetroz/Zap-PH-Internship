import java.util.*;

public class State3 extends StateS {

    @Override
    public ResultState process(String input, String command) {
        ArrayList<String> commands = CommandList.getCommands();
        ResultState resultState = new ResultState();
        String[] entries = input.split(" ");
        ArrayList<String> keys = CommandList.getKeys();
        String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);
        int n = CommandList.validateKeywords(command, modifiedEntries);

        if (commands.contains(input)) {
            resultState.setNextState(2);
            resultState.setCommand(input.toLowerCase());
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        } else if (keys.contains(entries[0].toUpperCase()) && modifiedEntries.length == n || keys.contains(entries[0].toUpperCase()) && n == 123456) {
            for (int i = 0; i < keys.size(); i++) {
                if (entries[0].toUpperCase().equals(keys.get(i))) {
                    entries[0] = commands.get(i);
                }
            }

            ClientHandler.setData(modifiedEntries);
            resultState.setNextState(3);
            resultState.setCommand(entries[0]);
            resultState.setNextMessage("Confirming request to " + input + ".");
        } else if (keys.contains(entries[0].toUpperCase()) && modifiedEntries.length < n){
            resultState.setNextState(2);
            resultState.setCommand(command);
            resultState.setNextMessage("You lack a field kindly re-enter the requested information.");
        } else if (keys.contains(entries[0].toUpperCase()) && modifiedEntries.length > n) {
            resultState.setNextState(2);
            resultState.setCommand(command);
            resultState.setNextMessage("Invalid input. Please try again.");
        } else {
            resultState.setNextState(0);
            resultState.setCommand("");
            resultState.setNextMessage("How can I help you?");
        }

        return resultState;
    }

}
