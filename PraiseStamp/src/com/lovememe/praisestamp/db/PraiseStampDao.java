package com.lovememe.praisestamp.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;
import com.j256.ormlite.support.ConnectionSource;

import android.content.Context;

public class PraiseStampDao {
	private Dao<PraiseStamp, String> stampDao;
	private Context context;
	private ConnectionSource connectionSource;

	public PraiseStampDao(Context context) {
		this.context = context;
		connectionSource = DBManager.getInstance(this.context)
				.getConnectionSource();
		try {
			stampDao = DaoFactory.createDao(connectionSource, PraiseStamp.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createStampTable() {
		DBManager.getInstance(context).createTable(PraiseStamp.class);
	}

	public List<PraiseStamp> getStampList() {
		try {
			return stampDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PraiseStamp getStampById(int id) {
		try {
			return stampDao.queryForId(id + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertStamp(PraiseStamp stamp) {
		try {
			stampDao.create(stamp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStamp(PraiseStamp stamp) {
		try {
			stampDao.update(stamp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStamp(PraiseStamp stamp) {
		try {
			stampDao.delete(stamp);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}