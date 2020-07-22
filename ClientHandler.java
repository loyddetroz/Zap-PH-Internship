import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

class ClientHandler extends Thread  
{ 
	private static String currentCommand = "";
    private static int currentState = 0;
    private static StateS[] states = new StateS[4];
    public static String[] data;
    private String name;
    final DataInputStream dis; 
    final DataOutputStream dos; 
    Socket s; 
    boolean isLoggedIn;
      
  
    // Constructor 
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s; 
        this.name = name;
        this.dis = dis; 
        this.dos = dos; 
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
        while (!input.equals("edf6cc9f248bb5717158dc24496746a2d4d93b41"))
        {
            try
            {
            		input = dis.readUTF();
                    StateS state = states[currentState];
                    ResultState resultState = state.process(input, currentCommand);
                    String output = resultState.getNextMessage();
                    currentCommand = resultState.getCommand();
                    currentState = resultState.getNextState();
                    for (ClientHandler mc : ChatServer.ar)  
                    { 
                        if (mc.name.equals(this.name) && mc.isLoggedIn == true)  
                        {
                            if (!input.equals("edf6cc9f248bb5717158dc24496746a2d4d93b41")) {
                                mc.dos.writeUTF("User: " + input + "\n");
                                mc.dos.writeUTF("Zap: " + output + "\n");
                            }
                        } 
                    }
                    System.out.println("Done sending updates to clients");
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        } 
          
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
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