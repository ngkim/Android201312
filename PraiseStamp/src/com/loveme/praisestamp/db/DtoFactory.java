package com.loveme.praisestamp.db;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DtoFactory extends Application {
	private SharedPreferences preferences;
	private DatabaseHelper databaseHelper = null;

	private Dao<PraiseStamp, Integer> stampDAO = null;

	@Override
	public void onCreate() {
		super.onCreate();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		databaseHelper = new DatabaseHelper(this);
	}

	public SharedPreferences getPreferences() {
		return preferences;
	}

	public Dao<PraiseStamp, Integer> getStampDao() throws SQLException {
		if (stampDAO == null) {
			stampDAO = databaseHelper.getDao(PraiseStamp.class);
		}
		return stampDAO;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

}
