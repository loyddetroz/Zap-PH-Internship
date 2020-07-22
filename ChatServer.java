import java.net.*;
import java.util.ArrayList;
import java.util.Vector;
import java.io.*;

public class ChatServer {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    // Vector to store active clients 
    static Vector<ClientHandler> ar = new Vector<>();

    static ArrayList<String> ids = new ArrayList<String>();

    // starts server and waits for a connection
    public ChatServer(int port) throws IOException {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Chat Server started");

            System.out.println("Waiting for a client ...");
            while (true) {
                socket = server.accept();
                System.out.println("Chat Client accepted" + socket);

                // takes input from the client socket
                dis = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));

                dos = new DataOutputStream(socket.getOutputStream());

                String id = dis.readUTF();

                String input = "";
                if (!ids.contains(id)) {
                    input = dis.readUTF();

                    ids.add(id);
                    if (!input.equals("edf6cc9f248bb5717158dc24496746a2d4d93b41")) {
                        dos.writeUTF("User: " + input + "\n");
                        dos.writeUTF("Zap: " + "How can I help you?" + "\n");
                    }

                    // Create a new handler object for handling this request.
                    ClientHandler mtch = new ClientHandler(socket, id, dis, dos);

                    // Create a new Thread with this object.
                    Thread t = new Thread(mtch);

                    System.out.println("Adding this client to active client list");

                    // add this client to active clients list
                    ar.add(mtch);

                    // start the thread.
                    t.start();
                } else {
                    // Create a new handler object for handling this request.
                    ClientHandler mtch = new ClientHandler(socket, id, dis, dos);

                    // Create a new Thread with this object.
                    Thread t = new Thread(mtch);

                    System.out.println("Adding this client to active client list");

                    // add this client to active clients list
                    ar.add(mtch);

                    // start the thread.
                    t.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        ChatServer server = new ChatServer(3000);
    }

}
