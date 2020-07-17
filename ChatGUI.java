import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ChatGUI implements ActionListener {
    private static String currentCommand = "";
    private static int currentState = 0;
    private static State[] states = new State[4];
    public static String[] data;
    JPanel messagePanel;
    JTextField userInput;
    JTextArea display;
    JButton sendButton;

    public ChatGUI() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        messagePanel = new JPanel();
        messagePanel.setPreferredSize(new Dimension(100, 50));

        display = new JTextArea();
        panel.add(new JScrollPane(display));
        display.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret) display.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        userInput = new JTextField(50);
        userInput.setSize(new Dimension(200, 50));
        messagePanel.add(userInput);

        sendButton = new JButton("Send");
        messagePanel.add(sendButton);
        sendButton.addActionListener(this);

        JFrame frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.add(messagePanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zap Chat Bot");
        frame.getRootPane().setDefaultButton(sendButton);
        frame.pack();
        frame.setSize(750, 500);
        frame.setVisible(true);

        readFile();

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveFile();
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    private boolean isFirstTime = true;

    public void run(String input) {

        if (input != "" && isFirstTime) {
            display.append("User: " + input + "\n");
            display.append("Zap: " + "How can I help you?" + "\n");

            display.append("\n");
            isFirstTime = false;
        } else {
            State state = states[currentState];
            ResultState resultState = state.process(input, currentCommand);

            String output = resultState.getNextMessage();
            currentCommand = resultState.getCommand();
            currentState = resultState.getNextState();
            display.append("User: " + input + "\n");
            display.append("Zap: " + output + "\n");
            display.append("\n");
        }
    }

//    private String storeAllString="";
//    public void readFile(){
//        try{
//            FileReader read = new FileReader("save/ "+ Login.getPhoneNumber().getText() + ".txt");
//
//            if (read != null) {
//                Scanner scan = new Scanner(read);
//                while(scan.hasNextLine()){
//                    String temp = scan.nextLine()+"\n";
//                    storeAllString = storeAllString + temp;
//                }
//                display.setText(storeAllString);
//            }
//        }
//        catch (Exception exception)
//        {
//            exception.printStackTrace();
//        }
//    }

//    public void saveFile() {
//        File writeFile;
//        Writer writer = null;
//
//        writeFile = new File("save/ "+ Login.getPhoneNumber().getText() + ".txt");
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(writeFile), "utf-8"));
//            display.write(writer);
//        } catch (IOException ex) {
//            // report
//        } finally {
//            try {
//                writer.close();
//            } catch (Exception ex) {/*ignore*/
//            }
//        }
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        run(userInput.getText());
        userInput.setText("");
//        saveFile();
    }

    public static String[] getData() {
        return data;
    }

    public static void setData(String[] data) {
        ChatGUI.data = data;
    }

    public static String getCurrentCommand() {
        return currentCommand;
    }
}
