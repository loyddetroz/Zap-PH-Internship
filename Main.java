import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String currentCommand = "";
    private static int currentState = 0;
    private static State[] states = new State[3];

    public static void main(String[] args) {
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();

        while (true) {
            String input = scanner.nextLine();
            State state = states[currentState];
            ResultState resultState = state.process(input, currentCommand, currentState);

            String output = resultState.getNextMessage();
            currentCommand = resultState.getCommand();
            currentState = resultState.getNextState();
            System.out.println(currentState + " " + currentCommand + " " + output);

        }

    }
}

