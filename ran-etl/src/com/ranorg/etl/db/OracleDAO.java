package com.ranorg.etl.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ranorg.etl.misc.FileStatus;

public class OracleDAO extends DAO {

	@Override
	public void updateStatus(FileStatus fs) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			log.debug("Trying to update status with sequence number "+fs.getFileSeqNumber());
			con = getConnection();
			st = con.prepareStatement("UPDATE FILE_STATUS set status=?, endtime=?, parsecount=?, errormsg=?, source=? where fileseq=?");
			st.setString(1, fs.getStatus());
			st.setTimestamp(2, new java.sql.Timestamp(fs.getEndDate().getTime()));
			st.setInt(3, fs.getParseCount());
			st.setString(4, fs.getErrorMessage());
			st.setString(5, fs.getSource());
			st.setLong(6, fs.getFileSeqNumber());
			st.addBatch();
			st.executeBatch();
			log.debug("Successfully updated status with sequence number "+fs.getFileSeqNumber());
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
			String generatedColumns[] = { "fileseq" };
			st = con.prepareStatement(
					"INSERT INTO FILE_STATUS (fileseq,filename,status,starttime,source,parsecount) values (ran_seq.nextval,?,?,?,?,?)",
					generatedColumns);
			st.setString(1, fs.getFileName());
			st.setString(2, fs.getStatus());
			st.setTimestamp(3, new java.sql.Timestamp(fs.getStartDate().getTime()));
			st.setString(4, fs.getSource());
			st.setInt(5, fs.getParseCount());
			st.addBatch();
			st.executeBatch();
			log.debug("Status inserted. "+fs.getFileName());
			rs = st.getGeneratedKeys();
			if (rs.next())
				fs.setFileSeqNumber(((BigDecimal) rs.getObject(1)).longValue());
			log.debug("Status inserted with sequence number "+fs.getFileSeqNumber());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, st, rs);
		}
	}

}
