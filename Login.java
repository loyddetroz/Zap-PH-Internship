import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	private static JTextField phoneNumber;
	private JPasswordField pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone Number");
		lblNewLabel.setBounds(38, 69, 99, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PIN");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(38, 101, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		phoneNumber = new JTextField();
		phoneNumber.setBounds(138, 66, 116, 22);
		frame.getContentPane().add(phoneNumber);
		phoneNumber.setColumns(10);
		
		pin = new JPasswordField();
		pin.setBounds(138, 98, 116, 22);
		frame.getContentPane().add(pin);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pNumber = phoneNumber.getText();
				String pass = pin.getText();
				if (Login.getCredentials(pNumber, pass).equalsIgnoreCase("success")) {
					JOptionPane.showMessageDialog(frame, "Login Successful");
					frame.dispose();
					ChatClient chatClient = new ChatClient("localhost", 3000);
				}
				else if (Login.getCredentials(pNumber, pass).equalsIgnoreCase("invalid pin")) {
					JOptionPane.showMessageDialog(frame, "Invalid PIN");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Invalid Phone Number");
				}
			}
		});
		btnNewButton.setBounds(138, 143, 97, 25);
		frame.getContentPane().add(btnNewButton);
	}
	
	public static ArrayList<String> readFile(String path) {
        ArrayList<String> listOfLines = new ArrayList<String>();
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(path));
            String read = null;
            while ((read = in.readLine()) != null) {
                String[] split = read.split("\\|");
                for (String part : split) {
                    listOfLines.add(part);
                }
            }
        } catch (IOException e) {
            System.out.println("There was a problem: " + e);
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }

        return listOfLines;
    }
	
	public static JTextField getPhoneNumber() {
		return phoneNumber;
	}
	
	public static String getCredentials(String number, String pin) {
        ArrayList users = readFile("data/users");

        if (users.contains(number)) {
            if (users.get(users.indexOf(number)+1).toString().equals(pin) ){
            	return "success";
            }
            else {
            	return "invalid pin";
            }
        }
        else {
        	return "invalid number";
        }
    }
}
