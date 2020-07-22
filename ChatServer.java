import java.net.*;
import java.util.ArrayList;
import java.util.Vector;
import java.io.*;

public class ChatServer extends Login {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    // Vector to store active clients 
    static Vector<ClientHandler> ar = new Vector<>(); 
    static ArrayList<String> ids = new ArrayList<String>();
    
    static String id = "";

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
            System.out.println("Chat Client accepted " + socket);
            id = Login.getPhoneNumber().getText();
            // takes input from the client socket
            dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            dos = new DataOutputStream(socket.getOutputStream());
            
            String input = "";
            input = dis.readUTF();
            if (!input.equals("") && !ids.contains(id)) {
                dos.writeUTF("User: " + input + "\n");
                dos.writeUTF("Zap: " + "How can I help you?" + "\n");
                ids.add(id);
                System.out.println("Creating a new handler for this client..."); 
                
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
            else {
            System.out.println("Creating a new handler for this client..."); 
            
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
        }
        catch (Exception e){ 
            //socket.close(); 
            e.printStackTrace(); 
        } 
    }

    public static void main(String args[]) throws IOException
    {
        ChatServer server = new ChatServer(3000);
    }
    
    
}
