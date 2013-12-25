package com.loveme.praisestamp;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.loveme.praisestamp.db.DtoFactory;
import com.loveme.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.dialog.DeleteStampDialog;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int stampsPerRow = 3;
	private DtoFactory dtoFactory;
	private PraiseStamp pStamp;
		
	private void createTables() {
//		PraiseStamp stamp = getStampFromDB();
		
		TableLayout table = new TableLayout(this);
		table.setStretchAllColumns(true);
		table.setShrinkAllColumns(true);

		TableRow rowTitle = new TableRow(this);
		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		// title column/row
		TextView title = new TextView(this);
		title.setText(pStamp.getTitle());
		title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		title.setGravity(Gravity.CENTER);
		title.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TableRow.LayoutParams params = new TableRow.LayoutParams();
		params.span = 3;

		rowTitle.addView(title, params);

		int totalRows = (pStamp.getGoalCnt() / stampsPerRow) + 1;

		ArrayList<TableRow> rows = new ArrayList<TableRow>();
		for (int i = 0; i < totalRows; i++) {
			rows.add(new TableRow(this));
		}

		for (int i = 0; i < rows.size(); i++) {
			TableRow row = rows.get(i);

			for (int j = 0; j < stampsPerRow; j++) {
				int curPos = i * stampsPerRow + j;
				if (curPos < pStamp.getNowCnt())
					row.addView(new StampView(this, "12/25"));
				else if (curPos >= pStamp.getGoalCnt())
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

	private void setTestStamp() {
		pStamp = new PraiseStamp("Christmas Gift!!!", 15);
		pStamp.setNowCnt(8);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		dtoFactory = (DtoFactory)getApplication();
		
		setTestStamp();
		/*try {
			Dao<PraiseStamp, Integer> stampDao = dtoFactory.getStampDao();
			stampDao.create(stamp);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		createTables();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
