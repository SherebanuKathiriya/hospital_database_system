import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.*;

public class Channel extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Channel frame = new Channel();
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
	public Channel() {
		con = (Connection) DB.dbconnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Channel Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 354, 452);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Channel No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 46, 115, 48);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Doctor Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 127, 101, 26);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Patient Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 185, 101, 26);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Room No");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 242, 101, 26);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Channel Date");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(10, 299, 101, 26);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		AutoID(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(168, 48, 144, 48);
		panel.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(168, 129, 144, 26);
		panel.add(comboBox);
		loadDoctor(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(168, 189, 144, 26);
		panel.add(comboBox_1);
		loadPatient(comboBox_1);
		
		JDateChooser cal = new JDateChooser();
		cal.setBounds(168,299,144,26);
		panel.add(cal);
		
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinner.setBounds(168, 247, 70, 26);
		panel.add(spinner);
		
		
		
		JButton btnCreate = new JButton("Create Channel");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = lblNewLabel_2.getText();
				Doctor dname = (Doctor) comboBox.getSelectedItem();
				Patient pname = (Patient) comboBox_1.getSelectedItem();
				int roomno = (int) spinner.getValue();
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = dateFormat.format(cal.getDate());
				
				try {
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into channel(channelno,dname,pname,roomno,date) values(?,?,?,?,?)");
					pst.setString(1, cid);
					pst.setString(2, dname.id);
					pst.setString(3, pname.id);
					pst.setString(4, Integer.toString(roomno));
					pst.setString(5, date);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Channel Created!");
					AutoID(lblNewLabel_2);
					comboBox.setSelectedIndex(-1);
					comboBox_1.setSelectedIndex(-1);
					spinner.setValue(0);
					cal.setDate(new Date());
					channel_table();
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCreate.setBounds(10, 391, 162, 37);
		panel.add(btnCreate);
		
		JButton btnNewButton_2_1 = new JButton("Delete Channel");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				try {
					String cid = lblNewLabel_2.getText();
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from channel where channelno = ?");
					pst.setString(1, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Channel Deleted!");
					AutoID(lblNewLabel_2);
					comboBox.setSelectedIndex(-1);
					comboBox_1.setSelectedIndex(-1);
					spinner.setValue(0);
					cal.setDate(new Date());
					channel_table();
					
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_2_1.setBounds(182, 391, 162, 37);
		panel.add(btnNewButton_2_1);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 11, 519, 452);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel) table.getModel();
				int s = table.getSelectedRow();
				String id = df.getValueAt(s, 0).toString();
				
				lblNewLabel_2.setText(id);
				
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Channel No", "Doctor Name", "Patient Name", "Room No", "Channel Date"
			}
		));
		channel_table();
	}
	public void AutoID(JLabel l) {
		try {
			Statement s = (Statement) con.createStatement();
			rs = s.executeQuery("select MAX(channelno) from channel");
			rs.next();
			rs.getString("MAX(channelno)");
			
			if(rs.getString("MAX(channelno)") == null)
				l.setText("CH001");
			else {
				long id = Long.parseLong(rs.getString("MAX(channelno)").substring(2, rs.getString("MAX(channelno)").length()));
				id++;
				l.setText("CH" + String.format("%03d", id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void channel_table() {
		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select * from channel");
			ResultSet rs=pst1.executeQuery();
			ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			df.setRowCount(0);
			while(rs.next()) {
				Vector<String> v2 = new Vector();
				for (int i=1;i<=a; i++){
					v2.add(rs.getString("channelno"));
					v2.add(rs.getString("dname"));
					v2.add(rs.getString("pname"));
					v2.add(rs.getString("roomno"));
					v2.add(rs.getString("date"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public class Doctor{
		String id;
		String name;
		public Doctor(String id, String name) {
			this.id = id;
			this.name = name;
		}
		public String toString() {
			return name;
		}
	}
	public void loadDoctor(JComboBox comboBox) {
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from doctor");
			ResultSet rs = pst.executeQuery();
			comboBox.removeAll();
			while(rs.next()) {
				comboBox.addItem(new Doctor(rs.getString(1), rs.getString(2)));
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class Patient{
		String id;
		String name;
		public Patient(String id, String name) {
			this.id = id;
			this.name = name;
		}
		public String toString() {
			return name;
		}
	}
	public void loadPatient(JComboBox comboBox) {
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from patient");
			ResultSet rs = pst.executeQuery();
			comboBox.removeAll();
			while(rs.next()) {
				comboBox.addItem(new Patient(rs.getString(1), rs.getString(2)));
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
