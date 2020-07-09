import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class DataRetriever {
    public static void display() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/balance"));
            String string = "";
            while( (string = br.readLine()) != null ) {
                String[] data = string.split("\\|");
                System.out.println([data]);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
