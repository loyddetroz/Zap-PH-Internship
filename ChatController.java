import javax.swing.*;
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
    JTextField messageArea;
    JButton sendButton;
    String input;

    public ChatController() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));

        messagePanel = new JPanel();
        messagePanel.setPreferredSize(new Dimension(100, 50));

        JTextArea chatDisplayArea = new JTextArea();
        chatDisplayArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        panel.add(chatDisplayArea);

        messageArea = new JTextField(50);
        messageArea.setSize(new Dimension(200, 50));
        messagePanel.add(messageArea);

        sendButton = new JButton("Send");
        messagePanel.add(sendButton);
        sendButton.addActionListener(this);

        JFrame frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.add(messagePanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Zap Chat Bot");
        frame.pack();
        frame.setSize(700, 500);
        frame.setVisible(true);
    }

    public void run() {
        states[0] = new State1();
        states[1] = new State2();
        states[2] = new State3();
        states[3] = new State4();

        if (scanner.nextLine() != "") {
            System.out.println("How can I help you?");
        }

        while (true) {
            State state = states[currentState];
            System.out.println(input);

            if (input.toLowerCase().contains("bye")) {
                break;
            }

            ResultState resultState = state.process(input, currentCommand);

            String output = resultState.getNextMessage();
            currentCommand = resultState.getCommand();
            currentState = resultState.getNextState();
//            System.out.println(currentState + " " +  currentCommand + " " + output);
            System.out.println(output);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        input = messageArea.getText();
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
