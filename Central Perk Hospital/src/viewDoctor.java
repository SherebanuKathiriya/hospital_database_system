import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

import net.proteanit.sql.DbUtils;

public class viewDoctor extends JFrame {
	private JTable table;
	private JPanel contentPane;
	Connection con;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewDoctor frame = new viewDoctor();
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
	public viewDoctor() {
		con = (Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 957, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(89, 73, 723, 386);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 703, 364);
		panel.add(scrollPane);
		
		
		table = new JTable();
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Doctor ID", "Name", "Phone", "Qualification", "Specialization", "Fee", "Room NO"
			}
		));
		sales_table();
		JLabel lblNewLabel = new JLabel("Doctor Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(335, 26, 243, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(424, 462, 89, 37);
		contentPane.add(btnNewButton);
	}
	public void sales_table() {
		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select doctorid,name,phone,qualification,specialization,fee,roomno from doctor");
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
