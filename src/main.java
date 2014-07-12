import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class main extends JFrame implements ActionListener {

	private static JButton Server, Client;
	private static JPanel panel;
	private static Server getPanel;

	public main() {

		super("Choose Interface");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();

		// New borderlayout

		setSize(540, 240);

		setResizable(false);

		// modifies buttons

		Server = new JButton("Server");

		Server.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		Client = new JButton("Client");

		Client.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

		// Adds everything to panel

		panel.add(Server);

		panel.add(Client);

		// adds action listeners

		Server.addActionListener(this);

		Client.addActionListener(this);

		// panel can close on "x" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setContentPane(panel);
		this.setVisible(true);

	}

	// Identifies action performed method
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Server) {
			getPanel = new Server();
			this.removeAll();
			this.validate();
			this.repaint();
			this.setContentPane(getPanel.returnPanel());

		}

		if (e.getSource() == Client) {
			new Client("Client");
		}

	}

	public static void main(String[] args) {
		new main();

	}

}
