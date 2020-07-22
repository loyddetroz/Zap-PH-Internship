import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

class ClientHandler implements Runnable 
{ 
	private static String currentCommand = "";
    private static int currentState = 0;
    private static StateS[] states = new StateS[4];
    public static String[] data;
    DataInputStream dis; 
    DataOutputStream dos; 
    Socket s; 
    boolean isLoggedIn;
    String name;
      
  
    // Constructor 
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos;
        this.name = name;
        this.isLoggedIn = true;
        
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();
    } 
  
    @Override
    public void run()  
    {  
    	String input = "";
        // reads message from client until "Over" is sent
        while (!input.equals("Over")) {
            try {
                input = dis.readUTF();
                StateS state = states[currentState];
                ResultState resultState = state.process(input, currentCommand);
                String output = resultState.getNextMessage();
                currentCommand = resultState.getCommand();
                currentState = resultState.getNextState();
                for (ClientHandler mc : ChatServer.ar)  
                { 
                    if (ChatServer.ids.contains(name) && mc.isLoggedIn == true)  
                    {
                    	mc.dos.writeUTF("User: " + input + "\n");
                        mc.dos.writeUTF("Zap: " + output + "\n");
                    } 
                }
                System.out.println("Done sending updates to clients");
                } 
            catch (IOException e) { 
                e.printStackTrace(); 
            } 
        }  
		
		  try { 
			  // closing resources 
			  this.dis.close(); 
			  this.dos.close();
		  
		  }catch(IOException e){ e.printStackTrace(); }
		 
    } 
    
    public static String[] getData() {
        return data;
    }

    public static void setData(String[] data) {
        ClientHandler.data = data;
    }

    public static String getCurrentCommand() {
        return currentCommand;
    }
} 