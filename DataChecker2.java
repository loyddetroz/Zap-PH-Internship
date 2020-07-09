import java.io.*;
import java.util.ArrayList;
public class DataChecker2 {

    public static void main(String[] args) {
        DataChecker2 dataChecker2 = new DataChecker2();
        System.out.println(getBalance("639000000009", "100008", "Angus_Steakhouse"));
    }

	public static String getBalance(String number, String pin, String merchantName){
        ArrayList users = readFile("data/users");
        ArrayList merchants = readFile("data/merchants");
        ArrayList balance = readFile("data/balance");
        String merchantCode = merchants.get(merchants.indexOf(merchantName)-1).toString();

        merchantName = merchantName.replace("_", " ");

        if (users.contains(number) && users.get(users.indexOf(number)+1).toString().equals(pin) && merchants.contains(merchantName) && balance.contains(number) && balance.contains(merchantCode)) {
            // to do
            // How can we retrieve a balance where a user is registered with two merchants?
        } else {
            return "Parameter not found in the database.";
        }

        if (balance.contains(number)) {
            return balance.get(balance.indexOf(number)+2).toString();
        }
		return null;
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
