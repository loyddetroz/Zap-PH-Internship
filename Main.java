import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String currentCommand = "";
    private static int currentState = 0;
    private static State[] states = new State[4];

    public static void main(String[] args) {
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();
        
        if (scanner.nextLine() != "") {
        	System.out.println("How can I help you?");
        }
        
        while (true) {
            State state = states[currentState];
            String input = scanner.nextLine();
            ResultState resultState = state.process(input, currentCommand, currentState);

            String output = resultState.getNextMessage();
            currentCommand = resultState.getCommand();
            currentState = resultState.getNextState();
            System.out.println(currentState + " " +  currentCommand + " " + output);

        }

    }
}

