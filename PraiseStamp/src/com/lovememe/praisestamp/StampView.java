package com.lovememe.praisestamp;

import com.loveme.praisestamp.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StampView extends TextView {
	private boolean isSelected; // selected stamp
	private boolean isEmpty;  // empty stamp  

	public StampView(Context context) {
		super(context);
		
		setText("");
		setGravity(Gravity.CENTER);
		
		setSelected(false);
		setEmpty(true);
		setImage();
		
		setPadding(0, 0, 0, 0); // padding in each image if needed
		addOnClickListener();
		addOnLongClickListener();
	}
	
	public StampView(Context context, String text) {
		super(context);
		
		setText(text);
		setGravity(Gravity.CENTER);
		
		setSelected(false);
		setEmpty(false);
		setImage();
		
		setPadding(0, 0, 0, 0); // padding in each image if needed
		addOnClickListener();
		addOnLongClickListener();
	}
	
	public StampView(Context context, boolean empty) {
		super(context);
		
		setEmpty(empty);
		setSelected(false);
		setImage();
		
		setPadding(0, 0, 0, 0); // padding in each image if needed
		addOnClickListener();
		addOnLongClickListener();		
	}
	
	public StampView(Context context, String text, boolean empty) {
		super(context);
		
		setText(text);
		setGravity(Gravity.CENTER);
		
		setEmpty(empty);
		setSelected(false);
		setImage();
		
		setPadding(0, 0, 0, 0); // padding in each image if needed
		addOnClickListener();
		addOnLongClickListener();		
	}
	
	private void setImage() {
		int resImage = 0;
		
		if( isEmpty() ) resImage = R.drawable.empty;
		else resImage = R.drawable.goodjob;
		
		setCompoundDrawablesWithIntrinsicBounds(0,resImage,0,0);
	}
	
	private void addOnClickListener() {
		setOnClickListener(new View.OnClickListener() {
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
		});
	}
	
	private void setEmptyStamp() {
		setEmpty(true);
    	setImage();
    	Toast.makeText(getContext(),
                "Clear Stamp!!!",
                Toast.LENGTH_SHORT).show();
    	setText("");
	}
	
	private DialogInterface.OnClickListener addDialogClickListener() {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
    	    @Override
    	    public void onClick(DialogInterface dialog, int which) {
    	        switch (which){
    	        case DialogInterface.BUTTON_POSITIVE:
    	        	setEmptyStamp();
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
	
	private void addDialog() {
		DialogInterface.OnClickListener dialogClickListener = addDialogClickListener();
    	AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    	builder.setMessage("Will you delete?").setPositiveButton("Yes", dialogClickListener)
    	    .setNegativeButton("No", dialogClickListener).show();
	}
	
	private void addOnLongClickListener() {
		setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				if(!isEmpty()) addDialog();
				return false;
			}
		});
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}	

}
