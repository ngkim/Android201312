package com.lovememe.praisestamp.db;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("serial")
@DatabaseTable(tableName = "praise_stamp")
public class PraiseStamp implements Serializable {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String title;
	@DatabaseField
	private String present;
	@DatabaseField
	private int goalCnt;
	@DatabaseField
	private int nowCnt;
	@DatabaseField
	private Date startDate;

	public PraiseStamp() {
		this.title = "";
		this.goalCnt = 0;
	}
	
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

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
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
	
	public void increaseNowCnt() {
		nowCnt++;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "PraiseStamp [title=" + title + ", goalCnt=" + goalCnt
				+ ", nowCnt=" + nowCnt + "]";
	}
}
