import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class loginform extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	java.sql.Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginform frame = new loginform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginform() {
		con = DB.dbconnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setBounds(175, 96, 84, 39);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setBounds(91, 161, 84, 39);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(212, 161, 131, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(91, 211, 84, 39);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(212, 211, 131, 39);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_2_1 = new JLabel("Usertype");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(91, 261, 84, 39);
		contentPane.add(lblNewLabel_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Doctor", "Receptionist", "Pharmacist", "Admin"}));
		comboBox.setBounds(212, 261, 131, 39);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(107, 356, 84, 39);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = textField.getText();
					String pwd = String.valueOf(passwordField.getPassword());
					String utype = comboBox.getSelectedItem().toString();
					
					PreparedStatement pst = con.prepareStatement("select * from user where username=? and password=? and usertype=?");
					pst.setString(1, user);
					pst.setString(2, pwd);
					pst.setString(3, utype);
					ResultSet r = pst.executeQuery();
					if(r.next()) {
						int userid = r.getInt("Id");
						String name = r.getString("name");
						Main s = new Main(userid,user,utype);
						s.setVisible(true);
						dispose();
					}
					else JOptionPane.showMessageDialog(null, "Enter correct Username, Password and Usertype!");
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Central Perk Hospital");
		lblNewLabel_3.setFont(new Font("Rockwell", Font.BOLD, 25));
		lblNewLabel_3.setBounds(88, 52, 278, 33);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(229, 356, 84, 39);
		contentPane.add(btnNewButton_1); 
		
		
	}
}
