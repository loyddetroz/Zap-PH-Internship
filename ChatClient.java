import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {

    public static String getNumber() {
		return number;
	}

	public static String[] data;
    private Socket socket            = null; 
    private static DataInputStream  input   = null; 
    private DataOutputStream out     = null;
    private static String number = "";
    
 // constructor to put ip address and port 
    public ChatClient(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from server
            input = new DataInputStream( 
                    new BufferedInputStream(socket.getInputStream()));
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String[] data = Main.getData();
        String com = Main.getCurrentCommand();
        StringBuilder builder = new StringBuilder();
        for(String s : data) {
            builder.append(s + " ");
        }
        String line = builder.toString();
        String line2 = "";
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            {
            	out.writeUTF(com);
            	out.writeUTF(line);
            	line2 = input.readUTF();
            	System.out.println(line2);
            	number = line2;
            	line = "Over";
            }  
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    }

}
