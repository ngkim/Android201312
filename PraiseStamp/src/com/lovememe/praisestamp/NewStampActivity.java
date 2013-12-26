package com.lovememe.praisestamp;

import java.util.List;

import com.loveme.praisestamp.R;
import com.loveme.praisestamp.R.layout;
import com.loveme.praisestamp.R.menu;
import com.lovememe.praisestamp.ListActivity.TextOnClickListener;
import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.db.PraiseStampDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NewStampActivity extends Activity {
	private PraiseStampDao stampDao;

	private PraiseStamp makeStamp() {
		PraiseStamp stamp = null;
		try {
			stamp = new PraiseStamp();

			EditText textTitle = (EditText) findViewById(R.id.stamp_title);
			String title = textTitle.getText().toString();

			EditText textPresent = (EditText) findViewById(R.id.stamp_present);
			String present = textPresent.getText().toString();

			EditText textGoal = (EditText) findViewById(R.id.editGoal);
			int goal = Integer.parseInt(textGoal.getText().toString());

			stamp.setTitle(title);
			stamp.setGoalCnt(goal);
			stamp.setPresent(present);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stamp;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_stamp);

		try {
			stampDao = new PraiseStampDao(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Button btnRegister = (Button) findViewById(R.id.btn_register);

		btnRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("onClick", "Make Stamp and insert into DB");
				PraiseStamp stamp = makeStamp();
				stampDao.insertStamp(stamp);
				Log.i("onClick", "Go to List");
				Intent intentSubActivity = new Intent(NewStampActivity.this, ListActivity.class);
				startActivity(intentSubActivity);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_stamp, menu);
		return true;
	}

}
