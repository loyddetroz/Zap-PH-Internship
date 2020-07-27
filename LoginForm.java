import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class LoginForm {
	private Socket socket = null;
	private ServerSocket server = null;

	public LoginForm(int port) throws IOException {
		try {
			server = new ServerSocket(port);

			while (true) {
				socket = server.accept();

				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				PrintStream out = new PrintStream(socket.getOutputStream());

				// Reads Html file
				String htmlNumFileName = "login.html";
				FileReader htmlNumFr = new FileReader(htmlNumFileName);
				File htmlNumDir = new File(htmlNumFileName);

				// Html file content
				String htmlNumFc = "";

				int numFileChar;

				while ((numFileChar = htmlNumFr.read()) != -1) {
					htmlNumFc += (char) numFileChar;
				}

				// Reads Html file
				String htmlPinFileName = "loginPin.html";
				FileReader htmlPinFr = new FileReader(htmlPinFileName);
				File htmlPinDir = new File(htmlPinFileName);

				// Html file content
				String htmlPinFc = "";

				int fileChar;

				while ((fileChar = htmlPinFr.read()) != -1) {
					htmlPinFc += (char) fileChar;
				}

				String inputLine;

				String outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + htmlNumFc;

				String getRequest = "";

				while ((inputLine = input.readLine()) != null) {
					if (inputLine.length() > 5) {
						if (inputLine.substring(0, 3).equals("GET")) {
							getRequest = inputLine;
							System.out.println("Get Request " + getRequest);
							if (getRequest.contains("number=") && getRequest.contains("pin=")) {
								String pNumber = getRequest.substring(19, 31);
								String pass = getRequest.substring(36, 42);
								if (Login.getCredentials(pNumber, pass).equalsIgnoreCase("success")) {
									ChatClient chatClient = new ChatClient("localhost", 3000, pNumber);
								} else if (Login.getCredentials(pNumber, pass).equalsIgnoreCase("invalid pin")) {
									outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n" + htmlPinFc;

								} else {
									outputLine = "HTTP/1.1 200 OK\n" + "Content-Type: text/html" + "\n\n"
											+ htmlNumFc;
								}
							}
						}
					}

					// System.out.println(inputLine);

					if (inputLine.isEmpty()) {
						break;
					}
				}

				out.println(outputLine);

				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		LoginForm form = new LoginForm(4000);
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

	public static String getCredentials(String number, String pin) {
		ArrayList users = readFile("data/users");

		if (users.contains(number)) {
			if (users.get(users.indexOf(number) + 1).toString().equals(pin)) {
				return "success";
			} else {
				return "invalid pin";
			}
		} else {
			return "invalid number";
		}
	}
}
