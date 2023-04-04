import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Inventory extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory(pno);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	static String pno;
	String pid;
	Connection con;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public Inventory(String pno) {
		con = (Connection) DB.dbconnect();
		Inventory.pno=pno;
		pid=pno;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 929, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 387, 307);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prescription ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 27, 134, 35);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Drug ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 84, 134, 35);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Drug Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 141, 134, 35);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Quantity");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 200, 134, 35);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(196, 29, 157, 35);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setText(pid);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(196, 150, 157, 26);
		panel.add(textField_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(196, 209, 83, 26);
		panel.add(spinner);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String dcode = textField.getText();
					try {
						PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from item where itemid=?");
						pst.setString(1, dcode);
						ResultSet rs = pst.executeQuery();
						
						if(rs.next()==false) {
							JOptionPane.showMessageDialog(null, "Drug not found");
						}
						else {
							String dname = rs.getString("name");
							textField_1.setText(dname.trim());
							spinner.requestFocus();
						}
					} 
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		textField.setBounds(196, 93, 157, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(475, 10, 387, 307);
		contentPane.add(panel_1);
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalCost.setBounds(10, 27, 134, 50);
		panel_1.add(lblTotalCost);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPay.setBounds(10, 103, 134, 50);
		panel_1.add(lblPay);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBalance.setBounds(10, 177, 134, 50);
		panel_1.add(lblBalance);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(181, 27, 157, 43);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(181, 103, 157, 43);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(181, 177, 157, 43);
		panel_1.add(textField_4);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dcode = textField.getText();
				try {
					PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from item where itemid=?");
					pst.setString(1, dcode);
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						int curqty;
						int sprice;
						int qty;
						curqty = rs.getInt("qty");
						sprice = rs.getInt("sellprice");
						qty=Integer.parseInt(spinner.getValue().toString());
						int total = sprice*qty;
						if(qty>=curqty) {
							JOptionPane.showMessageDialog(null, "Quantity Insufficiant");
						}
						else {
							DefaultTableModel dt = (DefaultTableModel)table.getModel();
							dt.addRow(new Object[] {
									lblNewLabel_2.getText(),
									textField.getText(),
									textField_1.getText(),
									spinner.getValue().toString(),
									sprice,
									total
							});
							int sum=0;
							for(int i=0;i<table.getRowCount();i++) {
								sum+=Integer.parseInt(table.getValueAt(i,5).toString());
							}
							textField_2.setText(Integer.toString(sum));
							textField.setText("");
							textField_1.setText("");
							spinner.setValue(0);
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(75, 260, 112, 41);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBounds(208, 260, 100, 41);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 328, 852, 211);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Prescription ID", "Drug Code", "Drug Name", "Quantity", "Price", "Total"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(83);
		
		JButton btnUpdateSales = new JButton("Update Sales");
		btnUpdateSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int total = Integer.parseInt(textField_2.getText());
				int pay = Integer.parseInt(textField_3.getText());
				int bal = total-pay;
				textField_4.setText(Integer.toString(bal));
				sales(Integer.toString(total), Integer.toString(pay), Integer.toString(bal),table);
			}
		});
		btnUpdateSales.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdateSales.setBounds(119, 260, 149, 41);
		panel_1.add(btnUpdateSales);
	}
	public void sales(String subtot, String pay, String bal, JTable tbl ) {
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dt.format(now);
		
		int lastinsertedid=0;
		try {
			PreparedStatement pst = (PreparedStatement) con.prepareStatement("insert into sales(date,subtotal,pay,balance) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, date);
			pst.setString(2, subtot);
			pst.setString(3, pay);
			pst.setString(4, bal);
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				lastinsertedid = rs.getInt(1);
			}
			
			int rows = tbl.getColumnCount();
			PreparedStatement pst1 = (PreparedStatement) con.prepareStatement("insert into saleproduct(salesid,prodid,sprice,qty,total) values(?,?,?,?,?)");
			String presid;
			String itemid;
			String price;
			String qty;
			int total;
			for(int i=0; i<tbl.getRowCount(); i++) {
				presid = (String)tbl.getValueAt(i, 0);
				itemid = (String)tbl.getValueAt(i, 1);
				qty = tbl.getValueAt(i, 3).toString();
				price = tbl.getValueAt(i, 4).toString();
				total = (int)tbl.getValueAt(i, 5);
				
				int qty1 = Integer.parseInt(qty);
				pst1.setInt(1,lastinsertedid);
				pst1.setString(2, itemid);
				pst1.setString(3,price);
				pst1.setInt(4,qty1);
				pst1.setInt(5,total);
				pst1.executeUpdate();
				JOptionPane.showMessageDialog(this, "Record Added");
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
