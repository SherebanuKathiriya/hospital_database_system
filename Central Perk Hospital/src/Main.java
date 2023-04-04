import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static int id;
	static String uname;
	int idd;
	
	static String utype;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main(id, uname, utype);
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
	
	
	public Main(int idform, String unameform, String utypeform) {
		id = idform;
		uname = unameform;
		utype = utypeform;
		idd=id;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(338, 181, 419, 135);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 22, 94, 39);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("UserType");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 72, 94, 39);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_3.setText(unameform);
		lblNewLabel_3.setBounds(192, 23, 171, 38);
		panel_1.add(lblNewLabel_3);
		
		
		
		JLabel lblNewLabel_4 = new JLabel();
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_4.setText(utypeform);
		lblNewLabel_4.setBounds(192, 73, 171, 38);
		panel_1.add(lblNewLabel_4);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 202, 453);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Patient");
		btnNewButton.setBounds(51, 40, 89, 32);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient p = new Patient();
				p.setVisible(true);
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Doctor");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor d = new Doctor(idd);
				d.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(51, 83, 89, 32);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Create Channel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Channel c = new Channel();
				c.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(35, 126, 125, 32);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View Channel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewChannel vc = new viewChannel(idd,utype);
				vc.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(35, 169, 125, 32);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("View Prescription");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewPrescription ps = new viewPrescription();
				ps.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(35, 212, 125, 32);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Create Item");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item i = new Item();
				i.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(35, 255, 125, 32);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Create User");
		btnNewButton_6.setBounds(35, 298, 125, 32);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUser nu = new NewUser();
				nu.setVisible(true);
			}
		});
		panel.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("View Doctor");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewDoctor vd = new viewDoctor();
				vd.setVisible(true);
			}
		});
		btnNewButton_7.setBounds(40, 341, 125, 32);
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Logout");
		btnNewButton_8.setBounds(51, 387, 89, 32);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginform lf = new loginform();
				JOptionPane.showMessageDialog(null, "Logged out successfully");
				dispose();
				lf.setVisible(true);
				
			}
		});
		panel.add(btnNewButton_8);
		
		JLabel lblNewLabel = new JLabel("Central Perk Hospital");
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 36));
		lblNewLabel.setBounds(364, 11, 354, 64);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_9 = new JButton("Report");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesReport sr = new SalesReport();
				sr.setVisible(true);
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_9.setBounds(599, 388, 119, 37);
		contentPane.add(btnNewButton_9);
		
		
		if(utype.equals("Doctor")) {
			btnNewButton.setVisible(false);
			btnNewButton_2.setVisible(false);
			btnNewButton_4.setVisible(false);
			btnNewButton_5.setVisible(false);
			btnNewButton_6.setVisible(false);
			btnNewButton_9.setVisible(false);
		}
		else if(utype.equals("Pharmacist")) {
			btnNewButton.setVisible(false);
			btnNewButton_1.setVisible(false);
			btnNewButton_2.setVisible(false);
			btnNewButton_3.setVisible(false);
			btnNewButton_6.setVisible(false);
			btnNewButton_9.setVisible(true);
		}
		else if(utype.equals("Receptionist")) {
			btnNewButton_1.setVisible(false);
			btnNewButton_4.setVisible(false);
			btnNewButton_9.setVisible(false);
		}
	}
}
