package com.ranorg.etl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ranorg.etl.misc.FileStatus;

public class MysqlDAO extends DAO {

	@Override
	public void updateStatus(FileStatus fs) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = getConnection();
			st = con.prepareStatement("UPDATE FILE_STATUS set status=?, endtime=?, parsecount=?, errormsg=?, source=? where fileseq=?");
			st.setString(1, fs.getStatus());
			st.setObject(2, fs.getEndDate());
			st.setObject(3, fs.getParseCount());
			st.setObject(4, fs.getErrorMessage());
			st.setObject(5, fs.getSource());
			st.setObject(6, fs.getFileSeqNumber());
			st.addBatch();
			st.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, st, null);
		}

	}

	@Override
	public void insertStatus(FileStatus fs) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			st = con.prepareStatement(
					"INSERT INTO FILE_STATUS (filename,status,starttime,source,parsecount) values (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, fs.getFileName());
			st.setString(2, fs.getStatus());
			st.setObject(3, fs.getStartDate());
			st.setObject(4, fs.getSource());
			st.setObject(5, fs.getParseCount());
			st.addBatch();
			st.executeBatch();
			rs = st.getGeneratedKeys();
			if (rs.next())
				fs.setFileSeqNumber((Long) rs.getObject(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
	}

}
