import java.util.*;

public class CommandList {
    private static String[] commands = new String[10];

    public static String[] getCommands() {
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

        return commands;
    }

    public static String printCommandResponse(String command) {
        Hashtable<String, String> hashtable = new Hashtable<>();
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

        hashtable.put(commands[0], "Alright, kindly enter the following BALANCE<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: BALANCE 09176780012 123456 Angus");
        hashtable.put(commands[1], "Alright, kindly enter the following COUPONS<SPACE>phoneNumber<SPACE>pin. Example: COUPONS 09176780012 123456");
        hashtable.put(commands[2], "Alright, kindly enter the following OPEN_BRANCHES<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: OPEN_BRANCHES 09176780012 123456 Angus");
        hashtable.put(commands[3], "Alright, kindly enter the following RANK<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: RANK 09176780012 123456 Angus");
        hashtable.put(commands[4], "Alright, kindly enter the following PROMOTIONS<SPACE>merchantName<SPACE>branch1<SPACE>branch.. (Separated by space for each branch) Example: PROMOTIONS Angus Branch1 Branch2 Branch3..)");
        hashtable.put(commands[5], "Alright, kindly enter the following VOID_TX<SPACE>refNo<SPACE>reason Example: VOID_TX 1234567 Reason");
        hashtable.put(commands[6], "Alright, kindly enter the following DEACTIVATE<SPACE>UserId Example: DEACTIVATE 123456");
        hashtable.put(commands[7], "Alright, kindly enter the following LIST_BRANCHES<SPACE>merchantName Example: LIST_BRANCHES Angus");
        hashtable.put(commands[8], "Alright, kindly enter the following ADDRESS_BRANCH<SPACE>branchName EXAMPLE: ADDRESS_BRANCH Angus");
        hashtable.put(commands[9], "Alright, kindly enter the following OPENING_HOURS<SPACE>branchName Example: OPENING_HOURS Angus");

        if (hashtable.get(command) == null) {
            return "Sorry something went wrong. Please try another command.";
        } else {
            return hashtable.get(command);
        }

    }
}
