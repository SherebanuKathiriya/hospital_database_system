import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class Patient extends JFrame {

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
					Patient frame = new Patient();
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
	Connection con;
	ResultSet rs;
	
	
	
	
	
	public Patient() {
		con = (Connection) DB.dbconnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Patient Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 354, 451);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Patient ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 50, 90, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 89, 90, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(26, 136, 114, 28);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(26, 178, 90, 28);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel();
		AutoID(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setBounds(146, 50, 182, 28);
		panel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(146, 95, 182, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(146, 142, 182, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setBounds(146, 182, 182, 184);
		panel.add(textArea);
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pid = lblNewLabel_4.getText();
				String pname = textField.getText();
				String phone = textField_1.getText();
				String add = textArea.getText();
				
				try {
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into patient(patientid,name,phone,address) values(?,?,?,?)");
					pst.setString(1, pid);
					pst.setString(2, pname);
					pst.setString(3, phone);
					pst.setString(4, add);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Patient Added!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textArea.setText("");
					textField.requestFocus();
					patient_table();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 406, 70, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				try {
					String pid = lblNewLabel_4.getText();
					String pname = textField.getText();
					String phone = textField_1.getText();
					String add = textArea.getText();
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("update patient set name =?, phone=?, address=? where patientid=?");
					pst.setString(1, pname);
					pst.setString(2, phone);
					pst.setString(3, add);
					pst.setString(4, pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Patient Updated!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textArea.setText("");
					textField.requestFocus();
					patient_table();
					btnNewButton.setEnabled(true);
					
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(90, 406, 82, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				try {
					String pid = lblNewLabel_4.getText();
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from patient where patientid = ?");
					pst.setString(1, pid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Patient Deleted!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textArea.setText("");
					textField.requestFocus();
					patient_table();
					btnNewButton.setEnabled(true);
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(182, 406, 70, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Cancel");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2_1.setBounds(262, 406, 82, 23);
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
				textArea.setText(df.getValueAt(s,3).toString());
				btnNewButton.setEnabled(false);
			}
		});
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Patient ID", "Patient Name", "Phone Number", "Address"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(86);
		patient_table();
	}
	
	public void AutoID(JLabel l) {
		try {
			Statement s = (Statement) con.createStatement();
			rs = s.executeQuery("select MAX(patientid) from patient");
			rs.next();
			rs.getString("MAX(patientid)");
			
			if(rs.getString("MAX(patientid)") == null)
				l.setText("PS001");
			else {
				long id = Long.parseLong(rs.getString("MAX(patientid)").substring(2, rs.getString("MAX(patientid)").length()));
				id++;
				l.setText("PS" + String.format("%03d", id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void patient_table() {
		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select * from patient");
			ResultSet rs=pst1.executeQuery();
			ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			df.setRowCount(0);
			while(rs.next()) {
				Vector<String> v2 = new Vector();
				for (int i=1;i<=a; i++){
					v2.add(rs.getString("patientid"));
					v2.add(rs.getString("name"));
					v2.add(rs.getString("phone"));
					v2.add(rs.getString("address"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

