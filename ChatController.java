import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class ChatController implements ActionListener {
    private static Scanner scanner = new Scanner(System.in);
    private static String currentCommand = "";
    private static int currentState = 0;
    private static State[] states = new State[4];
    public static String[] data;
    JPanel messagePanel;
    JTextField userInput;
    JTextArea display;
    JButton sendButton;

    public ChatController() {
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        messagePanel = new JPanel();
        messagePanel.setPreferredSize(new Dimension(100, 50));

        display = new JTextArea();
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.add(new JScrollPane(display));
        display.setLineWrap(true);

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
    }

    private boolean isFirstTime = true;

    public void run(String input) {

        if (input != "" && isFirstTime) {
            display.append("User: " + input + "\n");
            display.append("Zap: " + "How can I help you?" + "\n");
//            display.setBackground(Color.white);
            display.append("\n");
//            display.setText("Client: " + userInput.getText() + "\nServer: " + "How can I help you?");
            isFirstTime = false;
        } else {
            State state = states[currentState];
            ResultState resultState = state.process(input, currentCommand);

            String output = resultState.getNextMessage();
            currentCommand = resultState.getCommand();
            currentState = resultState.getNextState();
//        System.out.println(currentState + " " +  currentCommand + " " + output);
//            display.setText("Client: " + userInput.getText() + "\nServer: " + output);
            display.append("User: " + input + "\n");
            display.append("Zap: " + output + "\n");
            display.append("\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        run(userInput.getText());
        userInput.setText("");
    }

    public static String[] getData() {
        return data;
    }

    public static void setData(String[] data) {
        ChatController.data = data;
    }

    public static String getCurrentCommand() {
        return currentCommand;
    }
}
