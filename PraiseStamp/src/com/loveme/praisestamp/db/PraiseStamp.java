package com.loveme.praisestamp.db;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "praise_stamp")
public class PraiseStamp {
	
	@DatabaseField(id = true, generatedId = true) private int id;
	@DatabaseField private String title;
	@DatabaseField private int goalCnt;
	@DatabaseField private int nowCnt;

	public PraiseStamp(String title, int goal) {
		this.title = title;
		this.goalCnt = goal;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGoalCnt() {
		return goalCnt;
	}

	public void setGoalCnt(int goalCnt) {
		this.goalCnt = goalCnt;
	}

	public int getNowCnt() {
		return nowCnt;
	}

	public void setNowCnt(int nowCnt) {
		this.nowCnt = nowCnt;
	}

	@Override
	public String toString() {
		return "PraiseStamp [title=" + title + ", goalCnt=" + goalCnt
				+ ", nowCnt=" + nowCnt + "]";
	}
}
