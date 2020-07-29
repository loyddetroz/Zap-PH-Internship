import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginForm2 {
	private Socket socket = null;
    private ServerSocket server = null;
    private static String currentCommand = "";
    private static int currentState = 0;
    private static StateS[] states = new StateS[4];
    public static String[] data;
    private Socket clientSocket = null;
    private DataOutputStream dos = null;
    private DataInputStream dis = null;

	public LoginForm2(int port) throws IOException{
		states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();

        try {
            socket = new Socket("localhost", 3000);

            dis = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            // sends output to the socket
            dos = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

		try
        {
            server = new ServerSocket(port); 
            StringBuilder builder1 = new StringBuilder();
            String textHTML = "";
            
            while(true) {
            socket = server.accept();
            
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintStream out = new PrintStream(socket.getOutputStream());
            
            String htmlForm = "<html>\n" +
                    "  <head>\n" +
                    "    <title>Log in</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div>\n" +
                    "      <form action=\"/login\" method=\"POST\">\n" +
                    "        <label for=\"phoneNumber\">Phone</label>\n" +
                    "        <br />\n" +
                    "        <input type=\"text\" name=\"phoneNumber\" />\n" +
                    "        <br />\n" +
                    "        <label for=\"pin\">Pin</label>\n" +
                    "        <br />\n" +
                    "        <input type=\"password\" name=\"pin\" />\n" +
                    "        <br />\n" +
                    "        <button class=\"submit\">Log in</button>\n" +
                    "      </form>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";
            
            String htmlFormNumber = "<html>\n" +
                    "  <head>\n" +
                    "    <title>Log in</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div>\n" +
                    "      <h2>Invalid Number</h2>\n" +
                    "      <form action=\"/login\" method=\"POST\">\n" +
                    "        <label for=\"phoneNumber\">Phone</label>\n" +
                    "        <br />\n" +
                    "        <input type=\"text\" name=\"phoneNumber\" />\n" +
                    "        <br />\n" +
                    "        <label for=\"pin\">Pin</label>\n" +
                    "        <br />\n" +
                    "        <input type=\"password\" name=\"pin\" />\n" +
                    "        <br />\n" +
                    "        <button class=\"submit\">Log in</button>\n" +
                    "      </form>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";
            
            String htmlFormPin = "<html>\n" +
                    "  <head>\n" +
                    "    <title>Log in</title>\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <div>\n" +
                    "      <h2>Invalid Pin</h2>\n" +
                    "      <form action=\"/login\" method=\"POST\">\n" +
                    "        <label for=\"phoneNumber\">Phone</label>\n" +
                    "        <br />\n" +
                    "        <input type=\"text\" name=\"phoneNumber\" />\n" +
                    "        <br />\n" +
                    "        <label for=\"pin\">Pin</label>\n" +
                    "        <br />\n" +
                    "        <input type=\"password\" name=\"pin\" />\n" +
                    "        <br />\n" +
                    "        <button class=\"submit\">Log in</button>\n" +
                    "      </form>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>";
            
            String htmlChat = "<html>\r\n" + 
            		"  <head>\r\n" + 
            		"    <meta charset=\"UTF-8\" />\r\n" + 
            		"    <title>Zap ChatBot</title>\r\n" + 
            		"  </head>\r\n" + 
            		"  <body>\r\n" + 
            		"    <textarea name=\"chatDisplay\" cols=\"70\" rows=\"40\">" + textHTML + "</textarea>\r\n" + 
            		"    <form action=\"/chat\" method=\"POST\">\r\n" + 
            		"      <input type=\"text\" name=\"chatMessage\" size=\"65\" />\r\n" + 
            		"      <button type=\"submit\" id=\"sendButton\">Send</button>\r\n" + 
            		"\r\n" +  
            		"    </form>\r\n" + 
            		"  </body>\r\n" + 
            		"</html>";
            
            String inputLine;
            
            String outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + htmlForm;
           
            String getRequest = "";
            
            String postInfo = "";
            
            while((inputLine = input.readLine()) != null)
            {	 
            	//System.out.println(inputLine);
            	if(inputLine.length() > 5) {
	            	if (inputLine.substring(0,4).equals("POST")) {
                        getRequest = inputLine;
                        //System.out.println(postInfo);	                    
	            	}
            	}
            	
            	//System.out.println(inputLine);
            	
            	if(inputLine.isEmpty()){
                    while(input.ready()){
                        postInfo += ((char) input.read());
                    }
                    //System.out.println(postInfo);
                    break;
                }
            }
                       
            if (!postInfo.equals("")) {
                String[] fields = new String[10];
                postInfo = postInfo.replaceAll("\\+", " ");
                fields = PostGetSplitters.doPost(postInfo);
                //System.out.println(Arrays.toString(fields));	                    
                //System.out.println("Get Request " + getRequest);
                String text = fields[0].split("=")[1];
                if (getRequest.split("\\s")[1].equals("/chat") ) {
                    // sends message to server;
                    dos.writeUTF(text);
                                    
                    // write these to text file
                    String user = dis.readUTF();
                    String bot = dis.readUTF();
                    
                    builder1.append(user);
                    builder1.append(bot); 
                    textHTML = builder1.toString();
                    
                    //System.out.println(user);
                    //System.out.println(bot);
                    //System.out.println(textHTML);
                    outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + LoginForm2.updateChatHTML(textHTML);
                }
                else {
                if (Login.getCredentials(fields[0].split("=")[1], fields[1].split("=")[1]).equalsIgnoreCase("success") && getRequest.toString().split("\\s")[1].equals("/login")) {
                    //ChatClient chatClient = new ChatClient("localhost", 3000, pNumber);
                    dos.writeUTF(fields[0].split("=")[1]);
                	outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + htmlChat;
                }
                else if (Login.getCredentials(fields[0].split("=")[1], fields[1].split("=")[1]).equalsIgnoreCase("invalid pin") && getRequest.toString().split("\\s")[1].equals("/login")) {
                    outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + htmlFormPin;
                }
                else {
                    outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + htmlFormNumber;
                }
                }
            }
            
            out.println(outputLine);
            
            socket.close();
            }
        }
        catch (Exception e){  
            e.printStackTrace(); 
        } 
    }

    public static void main(String args[]) throws IOException
    {
        LoginForm2 form = new LoginForm2(4000);
    }
	
	public static ArrayList<String> readFile(String path) {
        ArrayList<String> listOfLines = new ArrayList<String>();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(path));
            String read = null;
            while ((read = in.readLine()) != null) {
                String[] split = read.split("\\|");
                for (String part : split) {
                    listOfLines.add(part);
                }
            }
        } catch (IOException e) {
            System.out.println("There was a problem: " + e);
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }

        return listOfLines;
    }
	
	public static String getCredentials(String number, String pin) {
        ArrayList users = readFile("data/users");

        if (users.contains(number)) {
            if (users.get(users.indexOf(number)+1).toString().equals(pin) ){
            	return "success";
            }
            else {
            	return "invalid pin";
            }
        }
        else {
        	return "invalid number";
        }
    }

    public static String[] doGet(String inputLine){
        String[] fields = new String[10];
        fields = inputLine.split(" ");

        if(fields[1].length() > 1){
            fields = fields[1].split("?");
            System.out.println(fields.toString());
        }

        return fields;
    }
    
    public static String updateChatHTML(String chat) {
    	String htmlChat = "<html>\r\n" + 
        		"  <head>\r\n" + 
        		"    <meta charset=\"UTF-8\" />\r\n" + 
        		"    <title>Zap ChatBot</title>\r\n" + 
        		"  </head>\r\n" + 
        		"  <body>\r\n" + 
        		"    <textarea name=\"chatDisplay\" cols=\"70\" rows=\"40\">" + chat + "</textarea>\r\n" + 
        		"    <form action=\"/chat\" method=\"POST\">\r\n" + 
        		"      <input type=\"text\" name=\"chatMessage\" size=\"65\" />\r\n" + 
        		"      <button type=\"submit\" id=\"sendButton\">Send</button>\r\n" + 
        		"\r\n" +  
        		"    </form>\r\n" + 
        		"  </body>\r\n" + 
        		"</html>";
    	return htmlChat;
    }
}
