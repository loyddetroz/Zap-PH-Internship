import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class DataRetrieverClient {

    public static String getString() {
		return string;
	}

	public static String[] data;
    private Socket socket = null;
    private static DataInputStream input = null;
    private DataOutputStream out = null;
    private static String string = "";
    
 // constructor to put ip address and port 
    public DataRetrieverClient(String address, int port)
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
//            System.out.println("Connected");
  
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
        String[] data = ChatServer.getData();
        String command = ChatServer.getCurrentCommand();
        StringBuilder builder = new StringBuilder();
        for(String s : data) {
            builder.append(s + " ");
        }
        String dataToClient = builder.toString();
        String responseFromServer = "";
        // keep reading until "Over" is input 
        while (!dataToClient.equals("Over"))
        { 
            try
            {
            	out.writeUTF(command);
            	out.writeUTF(dataToClient);
            	responseFromServer = input.readUTF();
            	string = responseFromServer;
            	dataToClient = "Over";
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
