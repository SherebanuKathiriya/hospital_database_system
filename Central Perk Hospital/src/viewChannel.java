import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewChannel extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewChannel frame = new viewChannel(id,utype);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static int id;
	static String utype;
	/**
	 * Create the frame.
	 */
	Connection con;
	public viewChannel(int id, String utype) {
		con = (Connection) DB.dbconnect();
		this.id = id;
		this.utype = utype;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 11, 786, 411);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Channel No", "Doctor ID", "Patient ID", "Room No", "Date"
			}
		));
		
		
		
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(513, 433, 97, 30);
		contentPane.add(btnNewButton);
		
		JButton btnPrescription = new JButton("Prescription");
		btnPrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dt = (DefaultTableModel)table.getModel();
				int s = table.getSelectedRow();
				String cno =  dt.getValueAt(s,0).toString();
				String dname = dt.getValueAt(s,1).toString();
				Prescription ps = new Prescription(cno,dname);
				ps.setVisible(true);
			}
		});
		
		btnPrescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPrescription.setBounds(360, 433, 143, 30);
		contentPane.add(btnPrescription);
		
		if(utype.equals("Doctor")) {
			channel_table_doctor();	
		}
		else channel_table();
	}
	public void channel_table_doctor() {
		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select channel.channelno,doctor.name,patient.name,channel.roomno,channel.date from doctor INNER JOIN channel on channel.dname = doctor.doctorid INNER JOIN patient on channel.pname = patient.patientid where doctor.logid=?");
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
					v2.add(rs.getString("Channel No"));
					v2.add(rs.getString("Doctor name"));
					v2.add(rs.getString("Patient name"));
					v2.add(rs.getString("Room No"));
					v2.add(rs.getString("Date"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void channel_table() {
		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select channel.channelno,doctor.name,patient.name,channel.roomno,channel.date from doctor INNER JOIN channel on channel.dname = doctor.doctorid INNER JOIN patient on channel.pname = patient.patientid");
			ResultSet rs=pst1.executeQuery();
			ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			df.setRowCount(0);
			while(rs.next()) {
				Vector<String> v2 = new Vector();
				for (int i=1;i<=a; i++){
					v2.add(rs.getString("Channel No"));
					v2.add(rs.getString("Doctor name"));
					v2.add(rs.getString("Patient name"));
					v2.add(rs.getString("Room No"));
					v2.add(rs.getString("Date"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
