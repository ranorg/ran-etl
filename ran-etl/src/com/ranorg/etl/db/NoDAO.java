package com.ranorg.etl.db;

import java.sql.Connection;

import com.ranorg.etl.misc.FileStatus;

public class NoDAO extends DAO {

	@Override
	protected Connection getConnection() {
		return null;
	}

	@Override
	public void updateStatus(FileStatus fs) {

	}

	@Override
	public void insertStatus(FileStatus fs) {

	}

}
