package com.loveme.praisestamp;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.loveme.praisestamp.db.DtoFactory;
import com.loveme.praisestamp.db.PraiseStamp;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int stampsPerRow = 4;
	private DtoFactory dtoFactory;

	private ImageView goodJobStamp() {
		// day 1 column
		ImageView imgStamp = new ImageView(this);
		imgStamp.setImageDrawable(getResources()
				.getDrawable(R.drawable.goodjob));
		imgStamp.setPadding(0, 0, 0, 0); // padding in each image if needed

		return imgStamp;
	}

	private ImageView emptyStamp() {
		ImageView imgStamp = new ImageView(this);
		imgStamp.setImageDrawable(getResources().getDrawable(R.drawable.empty));
		imgStamp.setPadding(0, 0, 0, 0); // padding in each image if needed

		return imgStamp;
	}

	private void createTables() {
		PraiseStamp stamp = getStampFromDB();
		
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
					row.addView(goodJobStamp());
				else if (curPos >= stamp.getGoalCnt())
					break;
				else
					row.addView(emptyStamp());
			}
		}

		table.addView(rowTitle);
		for (int i = 0; i < rows.size(); i++) {
			table.addView(rows.get(i));
		}

		setContentView(table);
	}
	
	private PraiseStamp getStampFromDB() {
		PraiseStamp stamp = null;
		try {
			Dao<PraiseStamp, Integer> stampDao = dtoFactory.getStampDao();
			List<PraiseStamp> stampList = stampDao.queryForAll();
			stamp = stampList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stamp;
	}

	private PraiseStamp getTestStamp() {
		PraiseStamp stamp = new PraiseStamp("크리스마스 선물", 10);
		stamp.setNowCnt(5);

		return stamp;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		dtoFactory = (DtoFactory)getApplication();
		
		PraiseStamp stamp = getTestStamp();
		try {
			Dao<PraiseStamp, Integer> stampDao = dtoFactory.getStampDao();
			stampDao.create(stamp);
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
