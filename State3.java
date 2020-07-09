import java.util.*;

public class State3 extends State {

    @Override
    public ResultState process(String input, String command) {
        String[] commands = CommandList.getCommands();
        ResultState resultState = new ResultState();
        String[] entries = input.split(" ");
        String[] keys = CommandList.getKeys();
        String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);
        int n = CommandList.validateKeywords(command, modifiedEntries);

//        for (int i = 0; i < keys.length; i++) {
//            if (entries[0].toUpperCase().equals(keys[i])) {
//                input = commands[i];
//            }
//        }
//
//        System.out.println(input);

        if (Arrays.asList(commands).contains(input)) {
            resultState.setNextState(2);
            resultState.setCommand(input.toLowerCase());
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        } else if (input.toLowerCase().contains("no")) {
            System.out.println("How can I help you?");
        } else if (Arrays.asList(keys).contains(entries[0]) && modifiedEntries.length == n) {
            Main.setData(modifiedEntries);
            resultState.setNextState(3);
            resultState.setCommand(command);
            resultState.setNextMessage("Confirming request to " + input + ".");
        } else if (Arrays.asList(keys).contains(entries[0]) && modifiedEntries.length < n){
            resultState.setNextState(2);
            resultState.setCommand(command);
            resultState.setNextMessage("You lack a field kindly re-enter the requested information.");
        } else if (Arrays.asList(keys).contains(entries[0]) && modifiedEntries.length > n) {
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
