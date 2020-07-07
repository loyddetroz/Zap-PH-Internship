import java.util.*;

public class State3 extends State {
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

        // How do you validate the inputs?
        // Works with 3 fields only--must fix this.
        // Create a function that will validate the string based on the command
        if (Arrays.asList(commands).contains(input)) {
            resultState.setNextState(2);
            resultState.setCommand(input);
            resultState.setNextMessage(printCommandResponse(input));
        } else if (modifiedEntries.length == 3) {
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

    private static String printCommandResponse(String command) {
        if (command.toLowerCase().contains("get balance")) {
            return "Alright, kindly enter the following BALANCE<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: BALANCE 09176780012 123456 Angus";
        } else if (command.toLowerCase().contains("get coupons")) {
            return "Alright, kindly enter the following COUPONS<SPACE>phoneNumber<SPACE>pin. Example: COUPONS 09176780012 123456";
        } else if (command.toLowerCase().contains("open branches nearby")) {
            return "Alright, kindly enter the following OPEN_BRANCHES<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: OPEN_BRANCHES 09176780012 123456 Angus";
        } else if (command.toLowerCase().contains("my rank")) {
            return "Alright, kindly enter the following RANK<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: RANK 09176780012 123456 Angus";
        } else if (command.toLowerCase().contains("ongoing promotions")) {
            return "Alright, kindly enter the following PROMOTIONS<SPACE>merchantName<SPACE>branch1<SPACE>branch.. (Separated by space for each branch) Example: PROMOTIONS Angus Branch1 Branch2 Branch3..)";
        } else if (command.toLowerCase().contains("void transaction")) {
            return "Alright, kindly enter the following VOID_TX<SPACE>refNo<SPACE>reason Example: VOID_TX 1234567 Reason";
        } else if (command.toLowerCase().contains("deactivate user")) {
            return "Alright, kindly enter the following DEACTIVATE<SPACE>UserId Example: DEACTIVATE 123456";
        } else if (command.toLowerCase().contains("list branches")) {
            return "Alright, kindly enter the following LIST_BRANCHES<SPACE>merchantName Example: LIST_BRANCHES Angus";
        } else if (command.toLowerCase().contains("branch address")) {
            return "Alright, kindly enter the following ADDRESS_BRANCH<SPACE>branchName EXAMPLE: ADDRESS_BRANCH Angus";
        } else if (command.toLowerCase().contains("opening hours of branch")) {
            return "Alright, kindly enter the following OPENING_HOURS<SPACE>branchName Example: OPENING_HOURS Angus";
        }

        return "";
    }

//    private static String validate(String[] entries, String command) {
//
//    }
}
