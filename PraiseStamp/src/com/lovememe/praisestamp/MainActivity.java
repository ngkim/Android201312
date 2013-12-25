package com.lovememe.praisestamp;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.loveme.praisestamp.R;
import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.db.PraiseStampDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.lovememe.praisestamp.STAMP"; 
	
	private int stampsPerRow = 3;
	private PraiseStampDao stampDao;
	private PraiseStamp stamp;
		
	private void createTables() {
		TableLayout table = new TableLayout(this);
		table.setStretchAllColumns(true);
		table.setShrinkAllColumns(true);

		TableRow rowTitle = new TableRow(this);
		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		// title column/row
		TextView title = new TextView(this);
		title.setText(stamp.getTitle());
		title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		title.setGravity(Gravity.CENTER);
		title.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TableRow.LayoutParams params = new TableRow.LayoutParams();
		params.span = 3;

		rowTitle.addView(title, params);

		int totalRows = (stamp.getGoalCnt() / stampsPerRow) + 1;

		ArrayList<TableRow> rows = new ArrayList<TableRow>();
		for (int i = 0; i < totalRows; i++) {
			rows.add(new TableRow(this));
		}

		for (int i = 0; i < rows.size(); i++) {
			TableRow row = rows.get(i);

			for (int j = 0; j < stampsPerRow; j++) {
				int curPos = i * stampsPerRow + j;
				if (curPos < stamp.getNowCnt())
					row.addView(new StampView(this, "12/25"));
				else if (curPos >= stamp.getGoalCnt())
					break;
				else
					row.addView(new StampView(this));
			}
		}

		table.addView(rowTitle);
		for (int i = 0; i < rows.size(); i++) {
			table.addView(rows.get(i));
		}

		setContentView(table);
	}
	
	private void setTestStamp() {
		Intent i = getIntent();
		stamp = (PraiseStamp)i.getSerializableExtra(EXTRA_MESSAGE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			stampDao = new PraiseStampDao(this);
			stampDao.createStampTable();
			setTestStamp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		createTables();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
