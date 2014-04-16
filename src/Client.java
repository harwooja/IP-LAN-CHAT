import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class Client extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static String SERVERIP, userInput, inputLine, USERN,
			serverHostname;
	private static JLabel ConnectTo;
	private static JTextField ChatBox, IPConnect;
	private static JButton Send, Done, Connect;
	private static Socket cSocket;
	private static PrintWriter out;
	private static BufferedReader in;
	private static JTextArea Text;
	private static JScrollPane ChatOutput;

	public Client(String name) {

		super("Chatty - Client");

		cSocket = null;
		out = null;
		in = null;
		USERN = name;
		

		// panel can close on "x" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creates new panel
		JPanel panel = new JPanel();

		// Sets size
		setSize(500, 240);
		setResizable(false);

		Send = new JButton("Send");
		Send.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		Done = new JButton("Done");
		Done.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		Connect = new JButton("Connect");
		Connect.setFont(new Font("Comic Sans MS", Font.BOLD, 12));

		ConnectTo = new JLabel("Connect to IP Address:", JLabel.RIGHT);
		ConnectTo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		ConnectTo.setForeground(Color.darkGray);

		Text = new JTextArea(6, 40);
		Text.setEditable(false);
		ChatOutput = new JScrollPane(Text,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		IPConnect = new JTextField(13);
		IPConnect.setOpaque(false);
		ChatBox = new JTextField(40);
		ChatBox.setOpaque(false);
		ChatBox.setEnabled(false);

		panel.setOpaque(false);
		setContentPane(panel);

		panel.add(ConnectTo);
		panel.add(IPConnect);
		panel.add(Connect);
		panel.add(ChatOutput);
		panel.add(ChatBox);
		panel.add(Send);
		panel.add(Done);

		ChatBox.addActionListener(this);
		IPConnect.addActionListener(this);
		Send.addActionListener(this);
		Done.addActionListener(this);
		Connect.addActionListener(this);

		this.setContentPane(panel);
		this.setVisible(true);

		
	while (true) {
		try {
			
			Thread.sleep(1000L);
		if (cSocket != null) {
			System.out.println("cSocket not null anymore");
			getLine();
			break;
		} else {
			System.out.println("cSocket still null");
			
		}
		
		

		} catch (Exception e) {
			System.err.println("Error");
		}
		
	}
	
	}

	public void CONNECT() {

		userInput = ChatBox.getText();
		out.println(userInput);
		Text.append(USERN + ": " + userInput);
		Text.append("\n");

	}

	// identifies public method CHAT
	public void CHAT(String SERVIP) {

		// tries

		serverHostname = new String(SERVIP);

		System.out.println("Attemping to connect to host " + serverHostname
				+ " on port 10007.");

		try {

			cSocket = new Socket(serverHostname, 10007);
			out = new PrintWriter(cSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(cSocket
					.getInputStream()));
			System.out.println("Successfully connected to the server");

		} catch (UnknownHostException e) {
			System.err.println("Don't know about our host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: "
					+ serverHostname);
			System.exit(1);
		}

		ChatBox.setEnabled(true);

	}

	public void getLine() {

		System.out.println(cSocket);
		if (cSocket != null) {

			try {

				// gets all input from buffered reader...
				System.out.println("we're running before the inputline");

				while ((inputLine = in.readLine()) != null) {
				Text.append("Server: " + inputLine);
				Text.append("\n");

				}

				// closes everything
			

				// catches if cannot do..
			} catch (IOException e) {
				// output
				System.out.println("Cannot retrieve input!");

			}

		} 
		
		
		System.out.println("HEY!!!");

	}

	// Identifies action performed method
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == Connect) {
			SERVERIP = IPConnect.getText();
			CHAT(SERVERIP);

		}

		if (e.getSource() == Send) {
			CONNECT();

		}

		if (e.getSource() == Done) {
			System.exit(1);

		}

	}

	public static void main(String[] args) {
		new Client("Client");

	}

}

