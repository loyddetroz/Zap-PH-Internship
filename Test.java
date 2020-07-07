import java.util.*;

public class Test {
    public static void main(String[] args) {
        String[] commands = new String[10];
        commands[0] = "get balance";
        commands[1] = "get coupons";
        commands[2] = "get open branches nearby";
        commands[3] = "get my rank";
        commands[4] = "know ongoing promotions";
        commands[5] = "void transaction";
        commands[6] = "deactivate user";
        commands[7] = "list branches";
        commands[8] = "get branch address";
        commands[9] = "get opening hours of branch";
        String[] confirms = new String[4];
        confirms[0] = "yes";
        confirms[1] = "yup";
        confirms[2] = "oo";
        confirms[3] = "yessss";
        Scanner scan= new Scanner(System.in);

        while(scan.hasNextLine()) {
            if (scan.nextLine() != " ")
            {
                System.out.println("How can I help you?");
            }
            boolean check = false;
            boolean proceed = false;
            String command = scan.nextLine();
            for (String str : commands) {
                if (command.equalsIgnoreCase(str)){
                    check = true;
                }
            }
            if ((check == true) && (proceed == false)) {
                System.out.println("I would like to confirm if you wish to " + command);
                String confirm = scan.nextLine();
                for (String str2 : confirms) {
                    if (confirm.equalsIgnoreCase(str2)){
                        proceed = true;
                        if ((check == true) && (proceed == true)) {
                            if (command.equalsIgnoreCase(commands[0]) || command.equalsIgnoreCase(commands[1]) || command.equalsIgnoreCase(commands[3])) {
                                System.out.println("alright, kindly enter the following PhoneNumber<SPACE>Pin<SPACE>Merchant. Example: 09176780012 123456 Angus");
                            }
                            else if (command.equalsIgnoreCase(commands[2])) {
                                System.out.println("alright, kindly enter the following Merchant<SPACE>Long<SPACE>Lat. Example: Angus 13 14");
                            }
                            else if (command.equalsIgnoreCase(commands[4])) {
                                System.out.println("alright, kindly enter the following Merchant<SPACE>Branch1<SPACE>Branch.. (Separated by space for each branch) Example: Angus Branch1 Branch..");
                            }
                            else if (command.equalsIgnoreCase(commands[5])) {
                                System.out.println("alright, kindly enter the following RefNo<SPACE>Reason Example: 1234567 Reason");
                            }
                            else if (command.equalsIgnoreCase(commands[6])) {
                                System.out.println("alright, kindly enter your PhoneNumber Example: 09176780012");
                            }
                            else if (command.equalsIgnoreCase(commands[7])) {
                                System.out.println("alright, kindly enter the MerchantName Example: Angus");
                            }
                            else if (command.equalsIgnoreCase(commands[8]) || command.equalsIgnoreCase(commands[9])) {
                                System.out.println("alright, kindly enter the BranchName Example: Tomas Morato");
                            }
                        }
                    }
                }
            }
            else {
                System.out.println("List of Request Commands: ");
                for (String str : commands) {
                    System.out.println(str);
                }
                command = scan.nextLine();
                for (String str : commands) {
                    if (command.equalsIgnoreCase(str)){
                        check = true;
                    }
                }
                if ((check == true) && (proceed == false)) {
                    System.out.println("I would like to confirm if you wish to " + command);
                    String confirm = scan.nextLine();
                    for (String str2 : confirms) {
                        if (confirm.equalsIgnoreCase(str2)){
                            proceed = true;
                            if ((check == true) && (proceed == true)) {
                                if (command.equalsIgnoreCase(commands[0]) || command.equalsIgnoreCase(commands[1]) || command.equalsIgnoreCase(commands[3])) {
                                    System.out.println("alright, kindly enter the following PhoneNumber<SPACE>Pin<SPACE>Merchant. Example: 09176780012 123456 Angus");
                                }
                                else if (command.equalsIgnoreCase(commands[2])) {
                                    System.out.println("alright, kindly enter the following Merchant<SPACE>Long<SPACE>Lat. Example: Angus 13 14");
                                }
                                else if (command.equalsIgnoreCase(commands[4])) {
                                    System.out.println("alright, kindly enter the following Merchant<SPACE>Branch1<SPACE>Branch.. (Separated by space for each branch) Example: Angus Branch1 Branch..");
                                }
                                else if (command.equalsIgnoreCase(commands[5])) {
                                    System.out.println("alright, kindly enter the following RefNo<SPACE>Reason Example: 1234567 Reason");
                                }
                                else if (command.equalsIgnoreCase(commands[6])) {
                                    System.out.println("alright, kindly enter your PhoneNumber Example: 09176780012");
                                }
                                else if (command.equalsIgnoreCase(commands[7])) {
                                    System.out.println("alright, kindly enter the MerchantName Example: Angus");
                                }
                                else if (command.equalsIgnoreCase(commands[8]) || command.equalsIgnoreCase(commands[9])) {
                                    System.out.println("alright, kindly enter the BranchName Example: Tomas Morato");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}