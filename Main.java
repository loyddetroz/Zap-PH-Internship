import java.util.Arrays;
import java.util.Scanner;

//    get balance : PhoneNumber,Pin,MerchantName
//    get coupons : PhoneNumber,Pin,MerchantName
//    open branches nearby : MerchantName,Long,Lat
//    my rank : PhoneNumber,Pin,MerchantName
//    ongoing promotions : Merchant,Branch,Branch,Branch,...
//    void transaction (or void tx) : refNo,reason
//    deactivate user : PhoneNumber
//    list branches : MerchantName
//    branch address : BranchName
//    opening hours of branch : BranchName

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean terminate = false;
        boolean firstTime = true;
        while (!terminate) {
            String input = scanner.nextLine();

            if (firstTime) {
                System.out.println("How can I help you?");
                firstTime = false;
            } else {
                if (input.toLowerCase().contains("bye")) {
                    System.out.println("Good bye!");
                    terminate = true;
                } else if (input.toLowerCase().contains("get balance")) {
                    if (confirm(input)) {
                        getBalance();
                    }
                } else {
                    System.out.println("Commands:");
                    System.out.println("get balance");
                    System.out.println("get coupons");
                    System.out.println("open branches nearby");
                    System.out.println("my rank");
                    System.out.println("ongoing promotions");
                    System.out.println("void transactions");
                    System.out.println("deactivate user");
                    System.out.println("list branches");
                    System.out.println("branch address");
                    System.out.println("opening hours of branch");
                }
            }



        }

    }

    public static boolean confirm(String i) {
        System.out.println("I would like to confirm if you wish to " + i.toLowerCase() + ".");
        String input = scanner.nextLine();

        if (input.toLowerCase().contains("yes") || input.toLowerCase().contains("oo") || input.toLowerCase().contains("yup")) {
            return true;
        } else {
            return false;
        }
    }

    public static void getBalance() {
        while (true) {
            System.out.println("Alright, kindly enter the following BALANCE<SPACE>pin<SPACE>merchant. Example: 09176780012 123456 Angus");
            String entry = scanner.nextLine();

            System.out.println("Confirming request: " + entry + ".");
            String input = scanner.nextLine();

            if (input.toLowerCase().contains("yes") || input.toLowerCase().contains("oo") || input.toLowerCase().contains("yup")) {
                String[] entries = entry.split(" ");
                String[] modifiedEntries = Arrays.copyOfRange(entries, 1, entries.length);
//                for (int j = 0; j < modifiedEntries.length; j++) {
//                    System.out.println(modifiedEntries[j]);
//                }
                if (modifiedEntries.length == 3) {
                    System.out.println("Success! Your balance is [123] " + modifiedEntries[2] + " points.");
                    break;
                } else if (modifiedEntries.length < 3) {
                    // To do //
                    System.out.println("You lack <Field>");
                }
            } else {
                System.out.println("Please try again.");
            }
        }

    }


}
