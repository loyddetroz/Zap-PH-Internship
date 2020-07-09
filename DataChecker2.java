import java.io.*;
import java.util.ArrayList;
public class DataChecker2 {

	public static String display(String number){
		ArrayList<String> listOfLines = new ArrayList<String>();
		BufferedReader in = null;
		try {
		    in = new BufferedReader(new FileReader("data/balance"));
		    String read = null;
		    while ((read = in.readLine()) != null) {
		        String[] splited = read.split("\\|");
		        for (String part : splited) {
		            //System.out.println(part);
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
		System.out.println(listOfLines.get(0));
        if (listOfLines.contains(number)) {
            return listOfLines.get(listOfLines.indexOf(number)+2);
        }
		return null;
	}
}
