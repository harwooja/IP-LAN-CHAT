import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class main extends JFrame implements ActionListener {


	private static JButton Server, Client;


	public main() {

		super("Choose Interface");



		// panel can close on "x" button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creates new panel
		JPanel panel = new JPanel();

		// Sets size
		setSize(500, 240);
		setResizable(false);








		this.setContentPane(panel);
		this.setVisible(true);


	
	}


	// Identifies action performed method
	public void actionPerformed(ActionEvent e) {

	

	}

	public static void main(String[] args) {
		new Client("Client");

	}

}
