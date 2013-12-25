package com.lovememe.praisestamp.db;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	private static final String DATABASE_NAME = "db_stamp.db";
	
	private Context context;

	private static DBManager instance;

	public DBManager(Context context) {
		this.context = context;
	}

	public static DBManager getInstance(Context context) {
		if (instance == null) {
			instance = new DBManager(context);
		}
		return instance;
	}

	public ConnectionSource getConnectionSource() {
		SQLiteDatabase sqliteDatabase = null;
		try {
			sqliteDatabase = context.openOrCreateDatabase(DATABASE_NAME,
					SQLiteDatabase.CREATE_IF_NECESSARY, null);
		} catch (Exception e) {
			Log.e("DATABASE", "Error!");
		}

		return new AndroidConnectionSource(sqliteDatabase);
	}

	public void createTable(Class<?> clazz) {
		try {
			TableUtils.createTableIfNotExists(getConnectionSource(), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}