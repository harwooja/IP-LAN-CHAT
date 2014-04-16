//imports all java packages

import java.net.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class Server extends JFrame implements ActionListener {

	//	Identifies all variables

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String userInput, inputLine, USERN;
	private static PrintWriter out;
	private static BufferedReader in;
	private static JLabel YourIP, DisplayIP;
	private static JTextField ChatBox;
	private static JButton Send, Done;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static JTextArea Text;
	private static JScrollPane ChatOutput;

	public Server(String name) {

		super(Messages.getString("Server.0")); //$NON-NLS-1$

		out = null;
		in = null;
		USERN = name;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		setSize(500, 240);
		setResizable(false);

		Send = new JButton(Messages.getString("Server.1")); //$NON-NLS-1$
		Send.setFont(new Font(Messages.getString("Server.2"), Font.BOLD, 16)); //$NON-NLS-1$
		Send.setEnabled(false);

		Done = new JButton(Messages.getString("Server.3")); //$NON-NLS-1$
		Done.setFont(new Font(Messages.getString("Server.4"), Font.BOLD, 16)); //$NON-NLS-1$

		YourIP = new JLabel(Messages.getString("Server.5"), SwingConstants.LEFT); //$NON-NLS-1$
		YourIP.setFont(new Font(Messages.getString("Server.6"), Font.BOLD, 14)); //$NON-NLS-1$
		YourIP.setForeground(Color.darkGray);

		FindIP();

		Text = new JTextArea(6, 40);
		Text.setEditable(false);
		Text.setOpaque(false);

		ChatOutput = new JScrollPane(Text,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		ChatBox = new JTextField(40);
		ChatBox.setOpaque(false);
		ChatBox.setEnabled(false);

		panel.setOpaque(false);
		setContentPane(panel);

		panel.add(YourIP);
		panel.add(DisplayIP);
		panel.add(ChatOutput);
		panel.add(ChatBox);
		panel.add(Send);
		panel.add(Done);

		ChatBox.addActionListener(this);
		Send.addActionListener(this);
		Done.addActionListener(this);

		this.setContentPane(panel);
		this.setVisible(true);

		CHAT();

	}

	public void CONNECT() {

		userInput = ChatBox.getText();
		out.println(userInput);
		Text.append(USERN + Messages.getString("Server.7") + userInput); //$NON-NLS-1$
		Text.append(Messages.getString("Server.8")); //$NON-NLS-1$

	}

	public void CHAT() {

		try {

			serverSocket = null;

			try {

				serverSocket = new ServerSocket(10007);

			} catch (IOException e) {
				System.err.println(Messages.getString("Server.9")); //$NON-NLS-1$
				System.exit(1);

			}

			clientSocket = null;

			try {

				clientSocket = serverSocket.accept();

			} catch (IOException e) {

				System.err.println(Messages.getString("Server.11")); //$NON-NLS-1$
				System.exit(1);
			}

			JOptionPane
			.showMessageDialog(null, Messages.getString("Server.12")); //$NON-NLS-1$
			ChatBox.setEnabled(true);
			Send.setEnabled(true);

			//sets our printwriter and bufferdreader with our sockets! <get string / send string>	

			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket
					.getInputStream()));

			while ((inputLine = in.readLine()) != null)

			{

				Text.append(Messages.getString("Server.15") + inputLine); //$NON-NLS-1$
				Text.append(Messages.getString("Server.16")); //$NON-NLS-1$

			}

			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();

		} catch (IOException e) {

			System.out.println(Messages.getString("Server.17")); //$NON-NLS-1$

		}

	}

	public void FindIP() {

		try {

			InetAddress addr = InetAddress.getLocalHost();

			String hostname = addr.getHostName();

			//			gets ip address as a readable string

			String ips = addr.getHostAddress();

			DisplayIP = new JLabel(ips, SwingConstants.RIGHT);
			DisplayIP.setFont(new Font(
					Messages.getString("Server.18"), Font.BOLD, 18)); //$NON-NLS-1$
			DisplayIP.setForeground(Color.RED);

		} catch (UnknownHostException e) {

			JOptionPane
			.showMessageDialog(null, Messages.getString("Server.19")); //$NON-NLS-1$

		}

	}

	//	Identifies action performed method

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Send) {
			CONNECT();
		}
		if (e.getSource() == Done) {
			System.exit(1);
		}

	}

	public static void main(String[] args) {

		new Server(Messages.getString("Server.20"));

	}

}



