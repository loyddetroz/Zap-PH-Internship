import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CommandList {
    private static ArrayList<String> commands = new ArrayList<>();
    private static ArrayList<String> keys = new ArrayList<>();
    private static Hashtable<String, String> responses = new Hashtable<>();
    private static Hashtable<String, Integer> fields = new Hashtable<>();
    private static boolean isFirstTime = true;

    public static ArrayList<String> getCommands() {
        if (isFirstTime) {
            readFile();
            isFirstTime = false;
        }

        return commands;
    }
    
    public static ArrayList<String> getKeys() {
        if (isFirstTime) {
            readFile();
            isFirstTime = false;
        }
        return keys;
    }
    
    public static String printCommandResponse(String command) {
        if (responses.get(command) == null) {
            return "Sorry something went wrong. Please try another command.";
        } else {
            return responses.get(command);
        }

    }

    public static int validateKeywords(String command, String[] args) {


        return fields.get(command);
    }

    public static void readFile() {
        try {
            FileReader  read = new FileReader("commands.txt");
            Scanner scanner = new Scanner(read);
            while(scanner.hasNextLine()) {
                String line[] = scanner.nextLine().split("\\|");
                commands.add(line[0]);
                keys.add(line[1]);
                responses.put(line[0], line[2]);
                fields.put(line[0], Integer.parseInt(line[3]));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
