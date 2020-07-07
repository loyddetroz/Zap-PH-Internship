import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
    private static Scanner scanner = new Scanner(System.in);

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
        boolean isFirstTime = true;
        boolean flag = true;

        while (true) {
            String input = scanner.nextLine();

            if (isFirstTime) {
                System.out.println("How can I help you?");
                isFirstTime = false;
            } else {

            }

        }
    }

    private static String printCommandResponse(String command) {
        if (command.toLowerCase().contains("get balance")) {
            return "Alright, kindly enter the following BALANCE<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: BALANCE 09176780012 123456 Angus";
        } else if (command.toLowerCase().contains("get coupons")) {
            return "Alright, kindly enter the following COUPONS<SPACE>phoneNumber<SPACE>pin. Example: COUPONS 09176780012 123456";
        } else if (command.toLowerCase().contains("open branches nearby")) {
            return "Alright, kindly enter the following OPEN_BRANCHES<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: OPEN_BRANCHES 09176780012 123456 Angus";
        } else if (command.toLowerCase().contains("my rank")) {
            return "Alright, kindly enter the following RANK<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: RANK 09176780012 123456 Angus";
        } else if (command.toLowerCase().contains("ongoing promotions")) {
            return "Alright, kindly enter the following PROMOTIONS<SPACE>merchantName<SPACE>branch1<SPACE>branch.. (Separated by space for each branch) Example: PROMOTIONS Angus Branch1 Branch2 Branch3..)";
        } else if (command.toLowerCase().contains("void transaction")) {
            return "Alright, kindly enter the following VOID_TX<SPACE>refNo<SPACE>reason Example: VOID_TX 1234567 Reason";
        } else if (command.toLowerCase().contains("deactivate user")) {
            return "Alright, kindly enter the following DEACTIVATE<SPACE>UserId Example: DEACTIVATE 123456";
        } else if (command.toLowerCase().contains("list branches")) {
            return "Alright, kindly enter the following LIST_BRANCHES<SPACE>merchantName Example: LIST_BRANCHES Angus";
        } else if (command.toLowerCase().contains("branch address")) {
            return "Alright, kindly enter the following ADDRESS_BRANCH<SPACE>branchName EXAMPLE: ADDRESS_BRANCH Angus";
        } else if (command.toLowerCase().contains("opening hours of branch")) {
            return "Alright, kindly enter the following OPENING_HOURS<SPACE>branchName Example: OPENING_HOURS Angus";
        }

        return "";
    }
}
