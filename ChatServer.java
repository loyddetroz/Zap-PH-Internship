import java.net.*;
import java.io.*;

public class ChatServer {
    private static String currentCommand = "";
    private static int currentState = 0;
    private static State[] states = new State[4];
    public static String[] data;
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    // starts server and waits for a connection
    public ChatServer(int port) {
        boolean isFirstTime = true;
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("ChatClient accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            String input = "";

            // reads message from client until "Over" is sent
            while (!input.equals("Over"))
            {
                try
                {
                    input = in.readUTF();
                    if (!input.equals("") && isFirstTime) {
                        out.writeUTF("User: " + input);
                        out.writeUTF("Zap: " + "How can I help you?" + "\n");
                        isFirstTime = false;
                    } else {
                        State state = states[currentState];
                        ResultState resultState = state.process(input, currentCommand);

                        String output = resultState.getNextMessage();
                        currentCommand = resultState.getCommand();
                        currentState = resultState.getNextState();

                        out.writeUTF("User: " + input);
                        out.writeUTF("Zap: " + output + "\n");
                    }
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        ChatServer server = new ChatServer(3000);
    }

    public static String[] getData() {
        return data;
    }

    public static void setData(String[] data) {
        ChatServer.data = data;
    }

    public static String getCurrentCommand() {
        return currentCommand;
    }

    public static void setCurrentCommand(String currentCommand) {
        ChatServer.currentCommand = currentCommand;
    }
}
