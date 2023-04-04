
import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.ResultSetMetaData;

import javax.swing.*;
public class DB {
		Connection conn = null;
		java.sql.PreparedStatement pst;
		public static Connection dbconnect() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/centralperkhospital", "root", "");
				return conn;
			}
			catch(Exception e2) {
				System.out.println(e2);
				return null;
			}
		}
}
