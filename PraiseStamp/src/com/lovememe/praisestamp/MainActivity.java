package com.lovememe.praisestamp;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.loveme.praisestamp.R;
import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.db.PraiseStampDao;
import com.lovememe.praisestamp.view.StampView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.lovememe.praisestamp.STAMP"; 
	public final static String EXTRA_MESSAGE_EDIT = "com.lovememe.praisestamp.EDITSTAMP"; 
	
	private int stampsPerRow = 3;
	private PraiseStampDao stampDao;
	private PraiseStamp stamp;
		
	public void layout() {
		ScrollView scrollView = new ScrollView(this);
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
		
		TableRow rowEdit = new TableRow(this);
		rowEdit.setGravity(Gravity.RIGHT);
		
		TextView editText = new TextView(this);
		editText.setText("EDIT");
		
		editText.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("StampDetail", "Call Edit Stamp Activity");
				Intent intent = new Intent(MainActivity.this,
						NewStampActivity.class);
				intent.putExtra(EXTRA_MESSAGE_EDIT, stamp.getId());
				startActivity(intent);
			}
			
		});
		rowEdit.addView(editText);
		
		int totalRows = (stamp.getGoalCnt() / stampsPerRow) + 1;

		ArrayList<TableRow> rows = new ArrayList<TableRow>();
		for (int i = 0; i < totalRows; i++) {
			rows.add(new TableRow(this));
		}

		for (int i = 0; i < rows.size(); i++) {
			TableRow row = rows.get(i);

			for (int j = 0; j < stampsPerRow; j++) {
				int curPos = i * stampsPerRow + j;
				if (curPos < stamp.getNowCnt()) {
					StampView sView = new StampView(this, stamp, "12/25");
					sView.setId(curPos);
					row.addView(sView);					
				} else if (curPos >= stamp.getGoalCnt())
					break;
				else {
					StampView sView = new StampView(this, stamp);
					sView.setId(curPos);
					row.addView(sView);
				}
			}
		}

		table.addView(rowTitle);
		table.addView(rowEdit);
		for (int i = 0; i < rows.size(); i++) {
			table.addView(rows.get(i));
		}

		scrollView.addView(table);
		setContentView(scrollView);
	}
	
	private void setStamp() {
		Intent i = getIntent();
		int stampId = i.getIntExtra(EXTRA_MESSAGE, 0);
		
		stamp = stampDao.getStampById(stampId);
		
		Log.i("Stamp Detail", "NowCnt= " + stamp.getNowCnt());
		Log.i("Stamp Detail", "Present= " + stamp.getPresent());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			stampDao = new PraiseStampDao(this);
			setStamp();
		} catch (Exception e) {
			e.printStackTrace();
		}

		layout();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
