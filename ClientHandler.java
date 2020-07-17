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
    final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
      
  
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();
    } 
  
    @Override
    public void run()  
    {  
        String input = "";
        boolean isFirstTime = true;
        // reads message from client until "Over" is sent
        while (!input.equals("Over"))
        {
            try
            {
                input = dis.readUTF();
                if (!input.equals("") && isFirstTime) {
                    dos.writeUTF("User: " + input + "\n");
                    dos.writeUTF("Zap: " + "How can I help you?" + "\n");
                    isFirstTime = false;
                } else {
                    StateS state = states[currentState];
                    ResultState resultState = state.process(input, currentCommand);
                    String output = resultState.getNextMessage();
                    currentCommand = resultState.getCommand();
                    currentState = resultState.getNextState();

                    dos.writeUTF("User: " + input + "\n");
                    dos.writeUTF("Zap: " + output + "\n");
                }
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