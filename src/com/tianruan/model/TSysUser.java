package com.tianruan.model;
// Generated 2016-9-6 16:24:50 by Hibernate Tools 3.6.0.Final

/**
 * TSysUser generated by hbm2java
 */
public class TSysUser implements java.io.Serializable {

	private String TUserId;
	private String TUserName;
	private String TUserPwd;
	private String TPersonId;
	private String TUserState;

	public TSysUser() {
	}

	public TSysUser(String TUserId, String TPersonId) {
		this.TUserId = TUserId;
		this.TPersonId = TPersonId;
	}

	public TSysUser(String TUserId, String TUserName, String TUserPwd, String TPersonId, String TUserState) {
		this.TUserId = TUserId;
		this.TUserName = TUserName;
		this.TUserPwd = TUserPwd;
		this.TPersonId = TPersonId;
		this.TUserState = TUserState;
	}

	public String getTUserId() {
		return this.TUserId;
	}

	public void setTUserId(String TUserId) {
		this.TUserId = TUserId;
	}

	public String getTUserName() {
		return this.TUserName;
	}

	public void setTUserName(String TUserName) {
		this.TUserName = TUserName;
	}

	public String getTUserPwd() {
		return this.TUserPwd;
	}

	public void setTUserPwd(String TUserPwd) {
		this.TUserPwd = TUserPwd;
	}

	public String getTPersonId() {
		return this.TPersonId;
	}

	public void setTPersonId(String TPersonId) {
		this.TPersonId = TPersonId;
	}

	public String getTUserState() {
		return this.TUserState;
	}

	public void setTUserState(String TUserState) {
		this.TUserState = TUserState;
	}

}
