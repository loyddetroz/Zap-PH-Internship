import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class DataRetriever {

	private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null;
    private DataOutputStream output     = null; 
	
 // constructor with port 
    public DataRetriever(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            
            // sends output to the socket 
            output = new DataOutputStream(socket.getOutputStream()); 
  
            String line = "";
            String line2 = "";
  
            // reads message from client until "Over" is sent 
            while (!line.equals("Over")) 
            { 
                try
                { 
                    line = in.readUTF();
                    System.out.println(line);
                    String[] info = line.split(" ");
                    //System.out.println(getBalance(info[0], info[1], info[2]));
                    line2 = getBalance(info[0], info[1], info[2]);
                    output.writeUTF(line2);
                    line = "Over";
  
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            //close connection 
            socket.close(); 
            in.close();
            output.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
	
    public static void main(String[] args) {
	    DataRetriever server = new DataRetriever(5000);
    }

	public static String getBalance(String number, String pin, String merchantName){
        ArrayList users = readFile("data/users");
        ArrayList merchants = readFile("data/merchants");
        ArrayList balance = readFile("data/balance");
        merchantName = merchantName.replace("_", " ");
        String merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();

        if (users.contains(number)) {
            if (users.get(users.indexOf(number)+1).toString().equals(pin) ){
                if (merchants.contains(merchantName)) {
                    if (balance.contains(number) && balance.contains(merchantCode)) {
                        for (int i = 0; i < balance.size(); i++) {
                            if (balance.get(i).toString().equals(number) && balance.get(i+1).toString().equals(merchantCode)) {
                                return balance.get(i+2).toString();
                            }
                        }
                    } else {
                        return "The number or merchant is not in the database.";
                    }
                } else {
                    return "Invalid merchant name.";
                }
            } else {
                return "Incorrect pin.";
            }

        } else {
            return "Invalid number.";
        }

		return "Balance not found";
	}

	public static String getCoupons(String number, String pin, String merchantName) {
        ArrayList users = readFile("data/users");
        ArrayList merchants = readFile("data/merchants");

        return "Functionality not supported as of the moment.";

    }

    public static String getOpenBranches() {
        return "Functionality not supported as of the moment.";
    }

    public static String getRank(String number, String pin, String merchantName) {
        ArrayList users = readFile("data/users");
        ArrayList merchants = readFile("data/merchants");
        ArrayList userRanks = readFile("data/user_ranks");
        ArrayList ranks = readFile("data/ranks");

        if (users.contains(number)) {
            if (users.get(users.indexOf(number)+1).toString().equals(pin) ){
                if (merchants.contains(merchantName)) {

                } else {
                    return "Invalid merchant name.";
                }
            } else {
                return "Incorrect pin.";
            }
        } else {
            return "Invalid number.";
        }

        return "Rank not available";
    }

    public static String getPromotions() {
        return "Functionality not supported as of the moment.";
    }

    public static String voidTX() {
        return "Functionality not supported as of the moment.";
    }

    public static String deactivate() {
        return "Functionality not supported as of the moment.";
    }

    public static String listBranches(String merchantName) {
        ArrayList branches = readFile("data/branches");
        ArrayList merchants = readFile("data/merchants");
        merchantName = merchantName.replace("_", " ");
        String merchantCode = "";
        String branchList = "";

        if (merchants.contains(merchantName)) {
            merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();
        } else {
            return "Invalid merchant name.";
        }

        if (branches.contains(merchantCode)) {
            for (int i = 0; i < branches.size(); i++) {
                if (branches.get(i).toString().contains(merchantName)) {
                    branchList = branchList + branches.get(i).toString() + "\n";
                }
            }
        } else {
            return "Invalid merchant name.";
        }

        if (branchList.equals("")) {
            return "No branch was found";
        } else {
            return branchList;
        }

    }

    public static String getAddressBranch(String merchantName) {
        ArrayList branches = readFile("data/branches");
        ArrayList merchants = readFile("data/merchants");
        merchantName = merchantName.replace("_", " ");
        String branchName = merchantName.replaceAll("(\\s)([A-Za-z]*$)", " - $2");
        merchantName = merchantName.replaceAll("\\s[A-Za-z]*$", "");
        String merchantCode = "";

        if (merchants.contains(merchantName)) {
            merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();
        } else {
            return "Invalid merchant name.";
        }

        if (branches.contains(merchantCode)) {
            for (int i = 0; i < branches.size(); i++) {
                if (branches.get(i).toString().contains(branchName)) {
                    return branches.get(i+1).toString();
                }
            }
        } else {
            return "Invalid merchant name.";
        }

        return "No branch was found";
    }

    public static String getOpeningHours(String merchantName) {
        ArrayList branches = readFile("data/branches");
        ArrayList merchants = readFile("data/merchants");
        merchantName = merchantName.replace("_", " ");
        String branchName = merchantName.replaceAll("(\\s)([A-Za-z]*$)", " - $2");
        merchantName = merchantName.replaceAll("\\s[A-Za-z]*$", "");
        String merchantCode = "";

        if (merchants.contains(merchantName)) {
            merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();
        } else {
            return "Invalid merchant name.";
        }

        if (branches.contains(merchantCode)) {
            for (int i = 0; i < branches.size(); i++) {
                if (branches.get(i).toString().contains(branchName)) {
                    return branches.get(i+2).toString() + " " + branches.get(i+3).toString();
                }
            }
        } else {
            return "Invalid merchant name.";
        }

        return "No branch was found";
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
}
