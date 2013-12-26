package com.lovememe.praisestamp.event;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.lovememe.praisestamp.db.PraiseStamp;

public class StampOnClickListener implements OnClickListener {
	PraiseStamp stamp;

	public StampOnClickListener(PraiseStamp stamp, StampView view) {
		this.stamp = stamp;
	}

	@Override
    public void onClick(View v) {
		
    	if (isEmpty()) {
    		setEmpty(false);
    		setImage();
    		Toast.makeText(getContext(),
                    "Good Job!!!",
                    Toast.LENGTH_SHORT).show();
    		setText("12/26");
    		setGravity(Gravity.CENTER);
    	}
    }

}