package com.lovememe.praisestamp;

import java.util.List;

import com.loveme.praisestamp.R;
import com.loveme.praisestamp.R.layout;
import com.loveme.praisestamp.R.menu;
import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.db.PraiseStampDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListActivity extends Activity {
	
	private PraiseStampDao stampDao;
	public final static String EXTRA_MESSAGE = "com.lovememe.praisestamp.STAMP"; 
	
	class TextOnClickListener implements OnClickListener {
		PraiseStamp stamp;
		
		public TextOnClickListener(PraiseStamp stamp) {
			this.stamp = stamp;
		}
		
		@Override
		public void onClick(View v) {
			sendMessage(stamp);
		}
	}
	
	private void sendMessage(PraiseStamp stamp){
		Log.i("onClick", "CallSubActivity");
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(EXTRA_MESSAGE, stamp);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		try {
			stampDao = new PraiseStampDao(this);
			stampDao.createStampTable();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TableLayout layout = (TableLayout) findViewById(R.id.TableLayout1);
		
		List<PraiseStamp> stampList = stampDao.getStampList();
		for ( int i = 0; i < stampList.size(); i++ ){
			TableRow row = new TableRow(this);
			row.setGravity(Gravity.CENTER_HORIZONTAL);
			
			PraiseStamp stamp = stampList.get(i);
			TextView text = new TextView(this);
			text.setText(stamp.getTitle());
			text.setOnClickListener(new TextOnClickListener(stamp));
			
			row.addView(text);
			layout.addView(row);
		}

		Button btnCallMain = (Button) findViewById(R.id.btn_register);
		
		btnCallMain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("onClick", "CallSubActivity");
				Intent intentSubActivity = new Intent(ListActivity.this,
						NewStampActivity.class);
				startActivity(intentSubActivity);				
			}
		});
	}
	
	private void addStampList() {
		try {			
			PraiseStamp stamp = new PraiseStamp("Christmas Gift!!!", 15);
			stamp.setNowCnt(8);
			stampDao.insertStamp(stamp);
			
			stamp = new PraiseStamp("New year present!!!", 20);
			stamp.setNowCnt(3);
			stampDao.insertStamp(stamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
