import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DataRetrieverServer {
    private ArrayList<String> commandsD = CommandList.getCommands();
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream output = null;
	
 // constructor with port 
    public DataRetrieverServer(int port)
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Data Server started"); 
  
            System.out.println("Waiting for a client ...");
            while (true) {
            socket = server.accept(); 
            System.out.println("Data Client accepted " + socket);
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            
            // sends output to the socket 
            output = new DataOutputStream(socket.getOutputStream()); 
  
            String dataFromClient = "";
            String dataToClient = "";
            String commandFromClient = "";
            // reads message from client until "Over" is sent 
            while (!dataFromClient.equals("Over"))
            {
                try
                {
                    commandFromClient = in.readUTF();
                    dataFromClient = in.readUTF();
                    String[] info = dataFromClient.split(" ");
                    if(commandFromClient.equalsIgnoreCase(commandsD.get(0))) {
	                    dataToClient = getBalance(info[0], info[1], info[2]);
	                    output.writeUTF(dataToClient);
	                    dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(1))) {
                        dataToClient = getCoupons(info[0], info[1], info[2]);
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(2))) {
                        dataToClient = getOpenBranches();
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(3))) {
                        dataToClient = getRank(info[0], info[1], info[2]);
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(4))) {
                        dataToClient = getPromotions();
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(5))) {
                        dataToClient = voidTX();
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(6))) {
                        dataToClient = deactivate();
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(7))) {
                        dataToClient = listBranches(info[0]);
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(8))) {
                        dataToClient = getAddressBranch(info[0]);
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                    else if(commandFromClient.equalsIgnoreCase(commandsD.get(9))) {
                        dataToClient = getOpeningHours(info[0]);
                        output.writeUTF(dataToClient);
                        dataFromClient = "Over";
                    }
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            }
            }
            }
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
	
    public static void main(String[] args) {
	    DataRetrieverServer server = new DataRetrieverServer(8000);
    }

	public static String getBalance(String number, String pin, String merchantName){
        ArrayList users = readFile("data/users");
        ArrayList merchants = readFile("data/merchants");
        ArrayList balance = readFile("data/balance");
        merchantName = merchantName.replace("_", " ");

        if (users.contains(number)) {
            if (users.get(users.indexOf(number)+1).toString().equals(pin) ){
                if (merchants.contains(merchantName)) {
                    String merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();
                    if (balance.contains(number) && balance.contains(merchantCode)) {
                        for (int i = 0; i < balance.size(); i++) {
                            if (balance.get(i).toString().equals(number) && balance.get(i+1).toString().equals(merchantCode)) {
                                return "Your balance is " + balance.get(i+2).toString() + ".";
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
        merchantName = merchantName.replace("_", " ");

        if (users.contains(number)) {
            if (users.get(users.indexOf(number)+1).toString().equals(pin) ){
                if (merchants.contains(merchantName)) {
                    String merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();
                    if (userRanks.contains(number) && userRanks.contains(merchantCode)) {
                        for (int i = 0; i < userRanks.size(); i++) {
                            if (userRanks.get(i).toString().equals(number) && userRanks.get(i+2).toString().equals(merchantCode)) {
                                return "Your rank is "+ userRanks.get(i+1).toString();
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
            return "Invalid branch name.";
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
            return "Invalid branch name.";
        }

        if (branches.contains(merchantCode)) {
            for (int i = 0; i < branches.size(); i++) {
                if (branches.get(i).toString().contains(branchName)) {
                    return branchName + " is open on " + branches.get(i+2).toString() + ".";
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
