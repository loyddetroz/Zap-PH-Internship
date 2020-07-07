import java.util.*;

public class State3 extends State {

    @Override
    public ResultState process(String input, String command) {
        String[] commands = CommandList.getCommands();
        ResultState resultState = new ResultState();
        String[] entries = input.split(" ");
        String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);

        // How do you validate the inputs?
        // Works with 3 fields only--must fix this.
        // Create a function that will validate the string based on the command
        if (Arrays.asList(commands).contains(input)) {
            resultState.setNextState(2);
            resultState.setCommand(input.toLowerCase());
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        } else if (modifiedEntries.length == 3) {
            Main.setData(modifiedEntries);
            resultState.setNextState(3);
            resultState.setCommand(command);
            resultState.setNextMessage("Confirming request to " + input + ".");
        } else if (modifiedEntries.length < 3 ){
            resultState.setNextState(2);
            resultState.setCommand(command);
            resultState.setNextMessage("You lack a field kindly re-enter the requested information.");
        } else {
            resultState.setNextState(2);
            resultState.setCommand(command);
            resultState.setNextMessage("Invalid input. Please try again.");
        }

        return resultState;
    }

//    private static boolean validate(String[] entries, String command) {
//        Hashtable<String, Integer> hashtable = new Hashtable<>();
//        hashtable.put();
//    }
}
