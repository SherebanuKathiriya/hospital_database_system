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

public class viewPrescription extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewPrescription frame = new viewPrescription();
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
	public viewPrescription() {
		con = (Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 32, 767, 361);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"PC001", "CH001", "Meredith Grey", "Fever", "Eat Paracetamol for 3 days \nTake 1 medicine in the morning and 1 at night\nEat before having any meal."},
			},
			new String[] {
				"presid", "chanid", "dname", "dtype", "description"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(53);
		table.getColumnModel().getColumn(1).setPreferredWidth(53);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		pres_table();
		
		JButton btnNewButton = new JButton("Inventory");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dt = (DefaultTableModel)table.getModel();
				int s = table.getSelectedRow();
				String cno =  dt.getValueAt(s,0).toString();
				Inventory i = new Inventory(cno);
				i.setVisible(true);;
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(297, 413, 115, 38);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(422, 413, 115, 38);
		contentPane.add(btnCancel);
	}
	public void pres_table() {

		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select * from prescription");
			ResultSet rs=pst1.executeQuery();
			ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			df.setRowCount(0);
			while(rs.next()) {
				Vector<String> v2 = new Vector();
				for (int i=1;i<=a; i++){
					v2.add(rs.getString("Prescription ID"));
					v2.add(rs.getString("Channel ID"));
					v2.add(rs.getString("Doctor Name"));
					v2.add(rs.getString("Desease Type"));
					v2.add(rs.getString("Description"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
