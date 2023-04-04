import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;
import javax.swing.JSpinner;

public class Doctor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor frame = new Doctor(id);
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
	static int id;
	Connection con;
	ResultSet rs;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public Doctor(int idd) {
		id=idd;
		//JOptionPane.showMessageDialog(this,id);
		con = (Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Doctor Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 354, 451);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctor ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(26, 42, 90, 42);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(26, 118, 90, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(26, 169, 114, 28);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Qualification");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(26, 224, 90, 28);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel();
		AutoID(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(146, 42, 182, 42);
		panel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(146, 119, 182, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(146, 170, 182, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setBounds(146, 225, 182, 28);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Specialization");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(26, 275, 102, 28);
		panel.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setBounds(146, 276, 182, 28);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Channel Fee");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(26, 328, 90, 28);
		panel.add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setBounds(146, 329, 182, 28);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6_1 = new JLabel("Room No");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6_1.setBounds(26, 367, 90, 28);
		panel.add(lblNewLabel_6_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(146, 375, 90, 28);
		panel.add(spinner);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String did = lblNewLabel_4.getText();
				String dname = textField.getText();
				String phone = textField_1.getText();
				String qual = textField_2.getText();
				String spec = textField_3.getText();
				String fee = textField_4.getText();
				int roomno = (int) spinner.getValue();
				
				
				try {
				
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into doctor(doctorid,name,phone,qualification,specialization,fee,logid,roomno) values(?,?,?,?,?,?,?,?)");
					pst.setString(1, did);
					pst.setString(2, dname);
					pst.setString(3, phone);
					pst.setString(4, qual);
					pst.setString(5, spec);
					pst.setString(6, fee);
					pst.setString(7, Integer.toString(idd));
					pst.setString(8, Integer.toString(roomno));
					
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Doctor Added!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					spinner.setValue(0);
					textField.requestFocus();
					doctor_table(id);
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 417, 70, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				try {
					String did = lblNewLabel_4.getText();
					String dname = textField.getText();
					String phone = textField_1.getText();
					String qual = textField_2.getText();
					String spec = textField_3.getText();
					String fee = textField_4.getText();
					int roomno = (int) spinner.getValue();
					
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("update doctor set name =?, phone=?, qualification=?, specialization=?, fee=?, logid=?, roomno=? where doctorid=?");
					pst.setString(1, dname);
					pst.setString(2, phone);
					pst.setString(3, qual);
					pst.setString(4, spec);
					pst.setString(5, fee);
					pst.setString(6, Integer.toString(id));
					pst.setString(7, Integer.toString(roomno));
					pst.setString(8, did);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Doctor Updated!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					spinner.setValue(0);
					textField.requestFocus();
					doctor_table(id);
					btnNewButton.setEnabled(true);
					
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(90, 417, 82, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				try {
					String did = lblNewLabel_4.getText();
					String dname = textField.getText();
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from doctor where doctorid = ?");
					pst.setString(1, did);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Doctor Deleted!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					spinner.setValue(0);
					textField.requestFocus();
					doctor_table(id);
					btnNewButton.setEnabled(true);
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(182, 417, 70, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Cancel");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(262, 417, 82, 23);
		panel.add(btnNewButton_2_1);
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 10, 494, 451);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				String id = df.getValueAt(s, 0).toString();
				lblNewLabel_4.setText(id);
				textField.setText(df.getValueAt(s,1).toString());
				textField_1.setText(df.getValueAt(s,2).toString());
				textField_2.setText(df.getValueAt(s,3).toString());
				textField_3.setText(df.getValueAt(s,4).toString());
				textField_4.setText(df.getValueAt(s,5).toString());
				spinner.setValue(df.getValueAt(s,7));
				btnNewButton.setEnabled(false);
			}
		});
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Doctor ID", "Doctor Name", "Phone Number", "Qualification", "Specialization", "Channel Fee", "Room No"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(86);
		doctor_table(id);
	}
	
	public void AutoID(JLabel l) {
		try {
			Statement s = (Statement) con.createStatement();
			rs = s.executeQuery("select MAX(doctorid) from doctor");
			rs.next();
			rs.getString("MAX(doctorid)");
			
			if(rs.getString("MAX(doctorid)") == null)
				l.setText("DS001");
			else {
				long id = Long.parseLong(rs.getString("MAX(doctorid)").substring(2, rs.getString("MAX(doctorid)").length()));
				id++;
				l.setText("DS" + String.format("%03d", id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doctor_table(int id) {
		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select * from doctor where logid=?");
			pst1.setString(1, Integer.toString(id));
			ResultSet rs=pst1.executeQuery();
			ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			df.setRowCount(0);
			while(rs.next()) {
				Vector<String> v2 = new Vector();
				for (int i=1;i<=a; i++){
					v2.add(rs.getString("doctorid"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("qualification"));
					v2.add(rs.getString("specialization"));
					v2.add(rs.getString("fee"));
					v2.add(rs.getString("roomno"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
