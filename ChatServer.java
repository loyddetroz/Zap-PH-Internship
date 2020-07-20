import java.net.*;
import java.io.*;

public class ChatServer extends Login{
	private static String currentCommand = "";
    private static int currentState = 0;
    private static StateS[] states = new StateS[4];
    public static String[] data;
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private String id = Login.getPhoneNumber().getText();
    private MultiMap<String, Socket> multimap = new MultiMap<>();
    
    
    // starts server and waits for a connection
    public ChatServer(int port) throws IOException {
    	states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Chat Server started");

            System.out.println("Waiting for a client ...");
            while(true) {
            socket = server.accept();
            System.out.println("Chat Client accepted");
            multimap.put(id, socket);
            
            // takes input from the client socket
            dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            dos = new DataOutputStream(socket.getOutputStream());
            // reads message from client until "Over" is sent

            Thread process = new Thread(new Runnable() {
                @Override
                public void run() {
                    String input = "";
                    boolean isFirstTime = true;
                    if (multimap.containsKey(id)) {
                        isFirstTime = false;
                    }

                    while (!input.equals("Over")) {
                        try
                        {
                            input = dis.readUTF();
                            if (!input.equals("") && isFirstTime == true) {
                                dos.writeUTF("User: " + input + "\n");
                                dos.writeUTF("Zap: " + "How can I help you?" + "\n");
                                isFirstTime = false;
                            } else {
                                StateS state = states[currentState];
                                ResultState resultState = state.process(input, currentCommand);
                                String output = resultState.getNextMessage();
                                currentCommand = resultState.getCommand();
                                currentState = resultState.getNextState();

                                for (Socket socket: multimap.get(id)) {
                                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                                    dos.writeUTF("User: " + input + "\n");
                                    dos.writeUTF("Zap: " + output + "\n");
                                }
                            }
                        }
                        catch(IOException i)
                        {
                            System.out.println(i);
                        }
                    }
                }
            });

            process.start();

            
            System.out.println("Assigning new thread for this number");
            for (Socket socket : multimap.get(id)) {
            	
            System.out.println(socket);
            // create a new thread object
//            Thread t = new ClientHandler(socket, dis, dos);
            // Invoking the start() method
//            t.start();
            }
            //System.out.println("Closing connection");

            // close connection
            //socket.close();
            //in.close();
        }
        }
        catch (Exception e){
            // SOCKET.CLOSE is not working Gives out a null pointer exception.
//            socket.close();
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException
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
}
