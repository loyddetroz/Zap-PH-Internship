import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataRetriever {
    public static String display(String number) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/balance"));
            String string = "";
            while( (string = br.readLine()) != null ) {
                String[] data = string.split("\\|");
                System.out.println(data[0]);
                if (data[0].equals(number)) {
                    return data[2];
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

}
