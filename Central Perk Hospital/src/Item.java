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

public class Item extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Item frame = new Item();
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
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public Item() {
		con = (Connection) DB.dbconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Item Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 354, 451);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 50, 90, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 105, 90, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(26, 154, 114, 38);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Selling Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(26, 213, 90, 38);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel();
		AutoID(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setBounds(146, 50, 182, 44);
		panel.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(146, 112, 182, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setBounds(146, 161, 182, 28);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(146, 220, 182, 28);
		panel.add(textField_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("Buying Price");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(26, 268, 90, 38);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Quantity");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3_2.setBounds(26, 328, 90, 38);
		panel.add(lblNewLabel_3_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(146, 279, 182, 28);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(146, 339, 182, 28);
		panel.add(textField_4);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String iid = lblNewLabel_4.getText();
				String name = textField.getText();
				String desc = textField_1.getText();
				String sprice = textField_2.getText();
				String bprice = textField_3.getText();
				String qty = textField_4.getText();
				
				try {
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into item(itemid,name,description,sellprice,buyprice,qty) values(?,?,?,?,?,?)");
					pst.setString(1, iid);
					pst.setString(2, name);
					pst.setString(3, desc);
					pst.setString(4, sprice);
					pst.setString(5, bprice);
					pst.setString(6, qty);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Item Added!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField.requestFocus();
					item_table();
					
					
					
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
					String iid = lblNewLabel_4.getText();
					String name = textField.getText();
					String desc = textField_1.getText();
					String sprice = textField_2.getText();
					String bprice = textField_3.getText();
					String qty = textField_4.getText();
					
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("update item set name =?, description=?, sellprice=?, buyprice=?, qty=? where itemid=?");
					pst.setString(1, name);
					pst.setString(2, desc);
					pst.setString(3, sprice);
					pst.setString(4, bprice);
					pst.setString(5, qty);
					pst.setString(6, iid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Item Updated!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField.requestFocus();
					item_table();
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
					String iid = lblNewLabel_4.getText();
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("delete from item where itemid = ?");
					pst.setString(1, iid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Item Deleted!");
					AutoID(lblNewLabel_4);
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
					textField_4.setText("");
					textField.requestFocus();
					item_table();
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
				textField_2.setText(df.getValueAt(s,3).toString());
				textField_3.setText(df.getValueAt(s,4).toString());
				textField_4.setText(df.getValueAt(s,5).toString());
				btnNewButton.setEnabled(false);
			}
		});
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item ID", "Item Name", "Description", "Sell Price", "Buy Price", "Quantity"
			}
		));
		item_table();
	}
	public void AutoID(JLabel l) {
		try {
			Statement s = (Statement) con.createStatement();
			rs = s.executeQuery("select MAX(itemid) from item");
			rs.next();
			rs.getString("MAX(itemid)");
			
			if(rs.getString("MAX(itemid)") == null)
				l.setText("IT001");
			else {
				long id = Long.parseLong(rs.getString("MAX(itemid)").substring(2, rs.getString("MAX(itemid)").length()));
				id++;
				l.setText("IT" + String.format("%03d", id));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void item_table() {

		try {
			int a;
			PreparedStatement pst1=(PreparedStatement) con.prepareStatement("select * from item");
			ResultSet rs=pst1.executeQuery();
			ResultSetMetaData rd=(ResultSetMetaData) rs.getMetaData();
			a=rd.getColumnCount();
			DefaultTableModel df=(DefaultTableModel)table.getModel();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			df.setRowCount(0);
			while(rs.next()) {
				Vector<String> v2 = new Vector();
				for (int i=1;i<=a; i++){
					v2.add(rs.getString("Item ID"));
					v2.add(rs.getString("Item Name"));
					v2.add(rs.getString("Description"));
					v2.add(rs.getString("Selling Price"));
					v2.add(rs.getString("Buying Price"));
					v2.add(rs.getString("Quantity"));
				}
				df.addRow(v2);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
