import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prescription extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prescription frame = new Prescription(cno,dname);
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
	static String cno;
	static String dname;
	String cid;
	Connection con;
	ResultSet rs;
	public Prescription(String cno, String dname) {
		con = (Connection) DB.dbconnect();
		Prescription.cno = cno;
		Prescription.dname = dname;
		cid=cno;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Prescription", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prescription No");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(38, 42, 139, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblChannelNo = new JLabel("Channel No");
		lblChannelNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChannelNo.setBounds(38, 102, 139, 33);
		contentPane.add(lblChannelNo);
		
		JLabel lblNewLabel_1_1 = new JLabel("Desease Type");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(38, 156, 139, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Description");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(38, 208, 139, 33);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(228, 44, 230, 49);
		contentPane.add(lblNewLabel_1);
		AutoID(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(228, 104, 257, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(cid);
		textField.setEnabled(false);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(228, 156, 257, 33);
		contentPane.add(textField_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(228, 214, 257, 165);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = lblNewLabel_1.getText();
				String chid = textField.getText();
				String dtype = textField_1.getText();
				String desc = textArea.getText();
				try {
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into prescription(presid,chanid,dname,dtype,description) values (?,?,?,?,?)");
					pst.setString(1, pid);
					pst.setString(2, chid);
					pst.setString(3, dname);
					pst.setString(4, dtype);
					pst.setString(5, desc);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Prescription Submitted!");
					
					AutoID(lblNewLabel_1);
					textField_1.setText("");
					textArea.setText("");
					textField.requestFocus();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(188, 400, 101, 33);
		contentPane.add(btnNewButton);
	}
	public void AutoID(JLabel l) {
		try {
			Statement s = (Statement) con.createStatement();
			rs = s.executeQuery("select MAX(presid) from prescription");
			rs.next();
			rs.getString("MAX(presid)");
			
			if(rs.getString("MAX(presid)") == null)
				l.setText("PC001");
			else {
				long id = Long.parseLong(rs.getString("MAX(presid)").substring(2, rs.getString("MAX(presid)").length()));
				id++;
				l.setText("PC" + String.format("%03d", id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
