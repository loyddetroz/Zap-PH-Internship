import java.util.*;

public class State1 extends State {

    @Override
    public ResultState process(String input, String command) {
        String[] commands = CommandList.getCommands();
        ResultState resultState = new ResultState();

        resultState.setNextMessage("List of Request Commands: " + "\n" + commands[0] + "\n" + commands[1] + "\n" + commands[2] + "\n" + commands[3] + "\n" + commands[4] + "\n" + commands[5] + "\n" + commands[6] + "\n" + commands[7] + "\n" + commands[8] + "\n" + commands[9]);
        resultState.setCommand("");
        resultState.setNextState(0);

        if (Arrays.asList(commands).contains(input.toLowerCase())) {
            resultState.setNextState(1);
            resultState.setCommand(input.toLowerCase());
            resultState.setNextMessage("I would like to confirm if you wish to " + input.toLowerCase() + ".");
        }


        return resultState;
    }


}
