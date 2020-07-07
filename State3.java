import java.util.*;

public class State3 extends State {

    @Override
    public ResultState process(String input, String command, int prevState) {
        ResultState resultState = new ResultState();
        String[] entries = input.split(" ");
        String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);

        // How do you validate the inputs?
        // Works with 3 fields only--must fix this.
        // Create a function that will validate the string based on the command
        if (modifiedEntries.length == 3) {
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

//    private static String validate(String[] entries, String command) {
//
//    }
}
