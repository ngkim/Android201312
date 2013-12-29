package com.lovememe.praisestamp.view;

import com.loveme.praisestamp.R;
import com.lovememe.praisestamp.db.PraiseStamp;
import com.lovememe.praisestamp.event.StampOnClickListener;
import com.lovememe.praisestamp.event.StampOnLongClickListener;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class StampView extends TextView {
	private boolean isSelected; // selected stamp
	private boolean isEmpty;  // empty stamp  
	private PraiseStamp stamp;
	private int id;
	
	public StampView(Context context) {
		super(context);
		
		init("", true);
	}
	
	public StampView(Context context, PraiseStamp stamp) {
		super(context);
		
		this.stamp = stamp;
		
		init("", true);
	}
	
	public StampView(Context context, PraiseStamp stamp, String text) {
		super(context);
		
		this.stamp = stamp;
		
		init(text, false);
	}
	
	public StampView(Context context, String text) {
		super(context);
		
		init(text, true);
	}
	
	public StampView(Context context, boolean empty) {
		super(context);
		
		init("", empty);
	}
	
	public StampView(Context context, String text, boolean empty) {
		super(context);
		
		init(text, empty);
	}
	
	public void init(String text, boolean empty){
		setText(text);
		setGravity(Gravity.CENTER);
		
		setSelected(false);
		setEmpty(empty);
		setImage();
		
		setPadding(0, 0, 0, 0); // padding in each image if needed
		addOnClickListener();
		addOnLongClickListener();		
	}

	public void setImage() {
		int resImage = 0;
		
		if( isEmpty() ) resImage = R.drawable.empty;
		else resImage = R.drawable.goodjob;
		
		setCompoundDrawablesWithIntrinsicBounds(0,resImage,0,0);
	}
	
	public void setEmptyStamp() {
		setEmpty(true);
    	setImage();
    	Toast.makeText(getContext(),
                "Clear Stamp!!!",
                Toast.LENGTH_SHORT).show();
    	setText("");
	}
	
	private void addOnClickListener() {
		setOnClickListener(new StampOnClickListener(this));
	}
	
	private void addOnLongClickListener() {
		setOnLongClickListener(new StampOnLongClickListener(this));
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

	public PraiseStamp getStamp() {
		return stamp;
	}

	public void setStamp(PraiseStamp stamp) {
		this.stamp = stamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

}
