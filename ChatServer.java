import java.net.*;
import java.io.*;

public class ChatServer {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    // starts server and waits for a connection
    public ChatServer(int port) throws IOException {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Chat Server started");

            System.out.println("Waiting for a client ...");
            while(true) {
            socket = server.accept();
            System.out.println("Chat Client accepted");

            // takes input from the client socket
            dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            dos = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Assigning new thread for this client");        
            
            // create a new thread object 
            Thread t = new ClientHandler(socket, dis, dos); 

            // Invoking the start() method 
            t.start();
            
            //System.out.println("Closing connection");

            // close connection
            //socket.close();
            //in.close();
        }
        }
        catch (Exception e){ 
            socket.close(); 
            e.printStackTrace(); 
        } 
    }

    public static void main(String args[]) throws IOException
    {
        ChatServer server = new ChatServer(3000);
    }

}
