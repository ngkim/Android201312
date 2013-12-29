package com.lovememe.praisestamp;

import java.util.ArrayList;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListActivity extends Activity {
	private ListView listView ;
	private PraiseStampDao stampDao;
	public final static String EXTRA_MESSAGE = "com.lovememe.praisestamp.STAMP"; 
	ArrayList<PraiseStamp> stampArrayList;
	
	class StampOnItemClickListener implements AdapterView.OnItemClickListener {
		
		public StampOnItemClickListener() {		
		}
		
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			TextView viewText = (TextView)view;
			
			Log.i("LIST ON CLICK", "VIEW= " + viewText.getText().toString());
			Log.i("LIST ON CLICK", "POSITION= " + position);
			Log.i("LIST ON CLICK", "ID= " + id);
			Log.i("LIST ON CLICK", "STAMP ID= " + stampArrayList.get(position).getId());
			
			sendMessage(stampArrayList.get(position).getId());
		}
	}
	
	private void sendMessage(int id){
		Log.i("onClick", "CallSubActivity");
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(EXTRA_MESSAGE, id);
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		// Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView1);
        stampArrayList = new ArrayList<PraiseStamp>();
		
		try {
			stampDao = new PraiseStampDao(this);
			stampDao.createStampTable();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TableLayout layout = (TableLayout) findViewById(R.id.TableLayout1);
		
		List<PraiseStamp> stampList = stampDao.getStampList();
		ArrayList<String> stampTitleList = new ArrayList<String>();
		for ( int i = 0; i < stampList.size(); i++ ){
//			TableRow row = new TableRow(this);
//			row.setGravity(Gravity.CENTER_HORIZONTAL);
			
			PraiseStamp stamp = stampList.get(i);
//			TextView text = new TextView(this);
//			text.setText(stamp.getTitle());
//			text.setOnItemClickListener(new StampOnItemClickListener(stamp));
			
//			row.addView(text);
//			layout.addView(row);
			stampArrayList.add(stamp);
			stampTitleList.add(stamp.getTitle());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, stampTitleList);
		
		 // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new StampOnItemClickListener());

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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
