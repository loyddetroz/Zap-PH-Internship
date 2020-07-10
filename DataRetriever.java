import java.io.*;
import java.util.ArrayList;
public class DataRetriever {

    public static void main(String[] args) {
//        System.out.println(getBalance("639000000010", "100009", "Angus_Steakhouse"));
        System.out.println(listBranches("Angus_Steakhouse"));
        System.out.println(getAddressBranch("Angus_Steakhouse_Pasig"));
        System.out.println(getOpeningHours("Angus_Steakhouse_Pasig"));
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
