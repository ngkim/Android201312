package com.lovememe.praisestamp.event;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;

import com.lovememe.praisestamp.MainActivity;
import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.db.PraiseStampDao;
import com.lovememe.praisestamp.view.StampView;

public class StampOnLongClickListener implements OnLongClickListener {
	StampView view;
	PraiseStampDao stampDao;

	public StampOnLongClickListener(StampView view) {		
		this.view = view;
		
		try {
			stampDao = new PraiseStampDao(view.getContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private DialogInterface.OnClickListener addDialogClickListener() {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        switch (which){
    	        case DialogInterface.BUTTON_POSITIVE:
    	        	view.setEmptyStamp();
    	        	
    	        	PraiseStamp stamp = view.getStamp();
    	    		stamp.decreaseNowCnt();
    	    		
    	    		stampDao.updateStamp(stamp); 
    	    		MainActivity activity_main = (MainActivity)view.getContext();
    	    		activity_main.layout();
    	    		
    	    		dialog.dismiss();
    	            break;

    	        case DialogInterface.BUTTON_NEGATIVE:
    	            dialog.dismiss();
    	            break;
    	        }
    	    }
    	};
    	
    	return dialogClickListener;
	}
	
	public void addDialog() {
		DialogInterface.OnClickListener dialogClickListener = addDialogClickListener();
    	AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
    	builder.setMessage("Will you delete?").setPositiveButton("Yes", dialogClickListener)
    	    .setNegativeButton("No", dialogClickListener).show();
	}

	@Override
	public boolean onLongClick(View arg0) {
		if(!view.isEmpty()) addDialog();
		return false;
	}

	public PraiseStampDao getStampDao() {
		return stampDao;
	}

	public void setStampDao(PraiseStampDao stampDao) {
		this.stampDao = stampDao;
	}

}