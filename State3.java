import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class State3 extends State {
    Scanner scanner = new Scanner(System.in);
    private String[] commands = new String[10];

    public State3() {
        commands[0] = "get balance";
        commands[1] = "get coupons";
        commands[2] = "get open branches nearby";
        commands[3] = "get my rank";
        commands[4] = "know ongoing promotions";
        commands[5] = "void transaction";
        commands[6] = "deactivate user";
        commands[7] = "list branches";
        commands[8] = "get branch address";
        commands[9] = "get opening hours of branch";
    }

    @Override
    public ResultState process(String input, String command, int prevState) {
        ResultState resultState = new ResultState();
        String[] entries = input.split(" ");
        String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);
        System.out.println(modifiedEntries.length);

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
