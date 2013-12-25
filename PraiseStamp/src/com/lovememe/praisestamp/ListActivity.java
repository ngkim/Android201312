package com.lovememe.praisestamp;

import com.loveme.praisestamp.R;
import com.loveme.praisestamp.R.layout;
import com.loveme.praisestamp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
