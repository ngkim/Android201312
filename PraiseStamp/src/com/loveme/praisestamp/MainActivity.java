package com.loveme.praisestamp;

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

	private ImageView addImage() {
		// day 1 column
		ImageView imageGoodJob = new ImageView(this);
		imageGoodJob.setImageDrawable(getResources().getDrawable(
				R.drawable.goodjob));
		imageGoodJob.setPadding(0, 0, 0, 0); // padding in each image if needed
		
		return imageGoodJob;
	}

	private void createTables() {
		TableLayout table = new TableLayout(this);
		table.setStretchAllColumns(true);
		table.setShrinkAllColumns(true);

		TableRow rowTitle = new TableRow(this);
		rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

		// title column/row
		TextView title = new TextView(this);
		title.setText("ÄªÂùµµÀå");
		title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
		title.setGravity(Gravity.CENTER);
		title.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TableRow.LayoutParams params = new TableRow.LayoutParams();
		params.span = 3;

		rowTitle.addView(title, params);

		TextView day5Label = new TextView(this);
		day5Label.setText("Feb 11");
		day5Label.setTypeface(Typeface.SERIF, Typeface.BOLD);

		TableRow rowDayLabels1 = new TableRow(this);
		TableRow rowDayLabels2 = new TableRow(this);

		
		rowDayLabels1.addView(addImage());
		rowDayLabels1.addView(addImage());
		rowDayLabels1.addView(addImage());
		rowDayLabels1.addView(addImage());
		rowDayLabels2.addView(addImage());
		

		rowDayLabels2.addView(day5Label);

		table.addView(rowTitle);
		table.addView(rowDayLabels1);
		table.addView(rowDayLabels2);

		setContentView(table);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		createTables();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
