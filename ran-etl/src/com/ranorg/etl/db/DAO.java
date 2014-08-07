package com.ranorg.etl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.ranorg.etl.misc.ETLConstants;
import com.ranorg.etl.misc.FileStatus;

public abstract class DAO {

	protected static final Logger log = Logger.getLogger(DAO.class);
	private ResourceBundle rb = ResourceBundle.getBundle("etl");
	public abstract void updateStatus(FileStatus fs);
	public abstract void insertStatus(FileStatus fs);

	protected Connection getConnection() {
		try {
			Class.forName(rb.getString(ETLConstants.DRIVER_CLASS));
			Connection connection = DriverManager.getConnection(
					rb.getString(ETLConstants.JDBC_URL),
					rb.getString(ETLConstants.DB_USER_NAME),
					rb.getString(ETLConstants.DB_PASSWORD));
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your JDBC Driver?");
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check configuration");
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
	public void close(Connection con, Statement st, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
