package com.tianruan.model;
// Generated 2016-10-8 17:57:10 by Hibernate Tools 3.6.0.Final

/**
 * TWorkLog generated by hbm2java
 */
public class TWorkLog implements java.io.Serializable {

	private String TLogId;
	private String TPersonId;
	private String TLogDate;
	private String TStartTime;
	private String TEndTime;
	private String TWorkLate;
	private String TWorkLess;
	private String TWorkNone;

	public TWorkLog() {
	}

	public TWorkLog(String TLogId) {
		this.TLogId = TLogId;
	}

	public TWorkLog(String TLogId, String TPersonId, String TLogDate, String TStartTime, String TEndTime,
			String TWorkLate, String TWorkLess, String TWorkNone) {
		this.TLogId = TLogId;
		this.TPersonId = TPersonId;
		this.TLogDate = TLogDate;
		this.TStartTime = TStartTime;
		this.TEndTime = TEndTime;
		this.TWorkLate = TWorkLate;
		this.TWorkLess = TWorkLess;
		this.TWorkNone = TWorkNone;
	}

	public String getTLogId() {
		return this.TLogId;
	}

	public void setTLogId(String TLogId) {
		this.TLogId = TLogId;
	}

	public String getTPersonId() {
		return this.TPersonId;
	}

	public void setTPersonId(String TPersonId) {
		this.TPersonId = TPersonId;
	}

	public String getTLogDate() {
		return this.TLogDate;
	}

	public void setTLogDate(String TLogDate) {
		this.TLogDate = TLogDate;
	}

	public String getTStartTime() {
		return this.TStartTime;
	}

	public void setTStartTime(String TStartTime) {
		this.TStartTime = TStartTime;
	}

	public String getTEndTime() {
		return this.TEndTime;
	}

	public void setTEndTime(String TEndTime) {
		this.TEndTime = TEndTime;
	}

	public String getTWorkLate() {
		return this.TWorkLate;
	}

	public void setTWorkLate(String TWorkLate) {
		this.TWorkLate = TWorkLate;
	}

	public String getTWorkLess() {
		return this.TWorkLess;
	}

	public void setTWorkLess(String TWorkLess) {
		this.TWorkLess = TWorkLess;
	}

	public String getTWorkNone() {
		return this.TWorkNone;
	}

	public void setTWorkNone(String TWorkNone) {
		this.TWorkNone = TWorkNone;
	}

}