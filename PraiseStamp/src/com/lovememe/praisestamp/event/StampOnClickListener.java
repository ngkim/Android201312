package com.lovememe.praisestamp.event;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.db.PraiseStampDao;
import com.lovememe.praisestamp.view.StampView;

public class StampOnClickListener implements OnClickListener {
	StampView view;
	PraiseStampDao stampDao;

	public StampOnClickListener(StampView view) {		
		this.view = view;
		
		try {
			stampDao = new PraiseStampDao(view.getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
    public void onClick(View v) {		
		PraiseStamp stamp = view.getStamp();
		Log.i("StampOnClickListener", "NowCnt= " + stamp.getNowCnt());
		Log.i("StampOnClickListener", "ID= " + view.getId());
		
		if (view.isEmpty() && (view.getId() == stamp.getNowCnt())) {
    		view.setEmpty(false);
    		view.setImage();    		
    		
    		stamp.increaseNowCnt();
    		
    		stampDao.updateStamp(stamp);
    		
    		Toast.makeText(view.getContext(),
                    "Good Job!!!",
                    Toast.LENGTH_SHORT).show();
    		view.setText("12/26");
    		view.setGravity(Gravity.CENTER);
    		
    				
    	}
    }

}