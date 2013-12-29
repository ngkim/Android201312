package com.lovememe.praisestamp.adapter;

import java.util.ArrayList;

import com.lovememe.praisestamp.ListActivity;
import com.lovememe.praisestamp.MainActivity;
import com.lovememe.praisestamp.db.PraiseStamp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StampListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<PraiseStamp> stampList;
	
	public StampListAdapter(Context context, ArrayList<PraiseStamp> stampList) {
		this.context = context;
		this.stampList = stampList;		
	}

	@Override
	public int getCount() {
		return stampList.size();
	}

	@Override
	public PraiseStamp getItem(int pos) {
		// TODO Auto-generated method stub
		return stampList.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
//		PraiseStamp stamp = stampList.get(i);
//		TextView text = new TextView(this);
//		text.setText(stamp.getTitle());
//		text.setOnItemClickListener(new StampOnItemClickListener(stamp));
		return view;
	}
	
	class StampOnItemClickListener implements AdapterView.OnItemClickListener {
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
//			Intent intent = new Intent(ListActivity.class, MainActivity.class);
//			intent.putExtra(EXTRA_MESSAGE, stamp.getId());
//			startActivity(intent);
		}
	}
	
	

}
