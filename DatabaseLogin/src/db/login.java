package db;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.sql.*;


public class login implements ActionListener {

	// Projekt 4 i punuar nga Francesko Kola, Jozefina Blinishta, Valmir Lumaj, Ali Skuqaj dhe Jurgen Meti.

	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	int count = 5;
	
	public static void main(String[] args) {
		
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new login());
		panel.add(button);
		
		
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		String user = userText.getText();
		String password = passwordText.getText();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root","");
			Statement stmt = con.createStatement();
			String sql = "Select * from userDB where Username='" + user+"' and Password='" + password+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(count != 0) {
				if(rs.next()) {
					userText.setText(null);
					passwordText.setText(null);
					JOptionPane.showMessageDialog( null, "Login Successful!");
				}
				else {
					userText.setText(null);
					passwordText.setText(null);
					count--;
				}
				
			}
			else {
				System.exit(0);
			}
			
		}catch (Exception e){
			System.out.println(e);
		}
		
		
		
	}
}
