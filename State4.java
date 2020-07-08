import java.util.Arrays;

public class State4 extends State {

    @Override
    public ResultState process(String input, String command) {
        ResultState resultState = new ResultState();

        if (input.toLowerCase().contains("yes")) {
            resultState.setNextState(0);
            resultState.setCommand(command);
            String[] data = Main.getData();
            resultState.setNextMessage(data[1] + "\n" + "Anything else I can help you with?");
        } 
        else if (Arrays.asList(CommandList.getCommands()).contains(input.toLowerCase())) {
            resultState.setNextState(2);
            resultState.setCommand(input);
            resultState.setNextMessage(CommandList.printCommandResponse(input));
        }
        else {
        	resultState.setNextState(0);
        	resultState.setCommand("");
        	resultState.setNextMessage("How can I help you?");           
        }
        return resultState;

    }

//    private static String printCommandResponse(String command) {
//        if (command.toLowerCase().contains("get balance")) {
//            return "Alright, kindly enter the following BALANCE<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: BALANCE 09176780012 123456 Angus";
//        } else if (command.toLowerCase().contains("get coupons")) {
//            return "Alright, kindly enter the following COUPONS<SPACE>phoneNumber<SPACE>pin. Example: COUPONS 09176780012 123456";
//        } else if (command.toLowerCase().contains("open branches nearby")) {
//            return "Alright, kindly enter the following OPEN_BRANCHES<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: OPEN_BRANCHES 09176780012 123456 Angus";
//        } else if (command.toLowerCase().contains("my rank")) {
//            return "Alright, kindly enter the following RANK<SPACE>phoneNumber<SPACE>pin<SPACE>merchantName. Example: RANK 09176780012 123456 Angus";
//        } else if (command.toLowerCase().contains("ongoing promotions")) {
//            return "Alright, kindly enter the following PROMOTIONS<SPACE>merchantName<SPACE>branch1<SPACE>branch.. (Separated by space for each branch) Example: PROMOTIONS Angus Branch1 Branch2 Branch3..)";
//        } else if (command.toLowerCase().contains("void transaction")) {
//            return "Alright, kindly enter the following VOID_TX<SPACE>refNo<SPACE>reason Example: VOID_TX 1234567 Reason";
//        } else if (command.toLowerCase().contains("deactivate user")) {
//            return "Alright, kindly enter the following DEACTIVATE<SPACE>UserId Example: DEACTIVATE 123456";
//        } else if (command.toLowerCase().contains("list branches")) {
//            return "Alright, kindly enter the following LIST_BRANCHES<SPACE>merchantName Example: LIST_BRANCHES Angus";
//        } else if (command.toLowerCase().contains("branch address")) {
//            return "Alright, kindly enter the following ADDRESS_BRANCH<SPACE>branchName EXAMPLE: ADDRESS_BRANCH Angus";
//        } else if (command.toLowerCase().contains("opening hours of branch")) {
//            return "Alright, kindly enter the following OPENING_HOURS<SPACE>branchName Example: OPENING_HOURS Angus";
//        }
//
//        return "";
//    }
}
