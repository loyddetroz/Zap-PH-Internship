import java.io.*;
import java.util.ArrayList;
public class DataChecker2 {

	public static void main(String a[]){
		ArrayList<String> listOfLines = new ArrayList<String>();
		BufferedReader in = null;
		try {
		    in = new BufferedReader(new FileReader("merchants.txt"));
		    String read = null;
		    while ((read = in.readLine()) != null) {
		        String[] splited = read.split(" ");
		        for (String part : splited) {
		            System.out.println(part);
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
	}
}
