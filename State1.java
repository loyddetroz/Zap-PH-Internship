import java.util.*;

public class State1 extends State {
    private String[] commands = new String[10];

    public State1() {
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

        resultState.setNextMessage("How can I help you?");
        resultState.setCommand("");
        resultState.setNextState(0);

        if (Arrays.asList(commands).contains(input)) {
            resultState.setNextState(1);
            resultState.setCommand(input);
            resultState.setNextMessage("I would like to confirm if you wish to " + input + ".");
        }


        return resultState;
    }


}
