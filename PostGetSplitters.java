import java.util.Arrays;
public class PostGetSplitters{

    public PostGetSplitters(){

    }

    public static String[] doGet(String inputLine){
        String[] fields = new String[10];
        fields = inputLine.split(" ");

        if(fields[1].length() > 1){
            fields = fields[1].split("\\?");
            System.out.println(Arrays.toString(fields));
            fields = fields[1].split("&");
            System.out.println(Arrays.toString(fields));
        }
        else{
            System.out.println("Success");
        }

        return fields;
    }

    public static String[] doPost(String inputLine){
        String[] fields = new String[10];
        fields = inputLine.split("&");
        System.out.println(Arrays.toString(fields));

        return fields;
    }
}