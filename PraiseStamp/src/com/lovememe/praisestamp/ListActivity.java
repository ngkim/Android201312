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
		intent.putExtra(EXTRA_MESSAGE, stamp.getId());
		startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		// Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView1);
		
		try {
			stampDao = new PraiseStampDao(this);
			stampDao.createStampTable();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View", 
                                         "Adapter implementation",
                                         "Simple List View In Android",
                                         "Create List View Android", 
                                         "Android Example", 
                                         "List View Source Code", 
                                         "List View Array Adapter", 
                                         "Android Example List View" 
                                        };
		
     // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
