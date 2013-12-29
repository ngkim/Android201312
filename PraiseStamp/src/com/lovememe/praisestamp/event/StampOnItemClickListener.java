package com.lovememe.praisestamp.event;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.lovememe.praisestamp.ListActivity;
import com.lovememe.praisestamp.MainActivity;
import com.lovememe.praisestamp.db.PraiseStamp;

public class StampOnItemClickListener implements AdapterView.OnItemClickListener {
	PraiseStamp stamp;
	
	public StampOnItemClickListener(PraiseStamp stamp) {
		this.stamp = stamp;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		sendMessage(stamp);		
	}
	
	public void sendMessage(PraiseStamp stamp){
		Log.i("onClick", "CallSubActivity");
//		Intent intent = new Intent(ListActivity.class, MainActivity.class);
//		intent.putExtra(EXTRA_MESSAGE, stamp.getId());
//		startActivity(intent);
	}
}