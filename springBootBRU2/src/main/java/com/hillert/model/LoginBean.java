package com.hillert.model;

public class LoginBean {

	private int LogId;
	private String LogUsername;
	private String LogPassword;
	private String LogRole;
	private String LogStatus;

	

	public String getLogUsername() {
		return LogUsername;
	}

	public void setLogUsername(String logUsername) {
		LogUsername = logUsername;
	}

	public String getLogPassword() {
		return LogPassword;
	}

	public void setLogPassword(String logPassword) {
		LogPassword = logPassword;
	}

	public String getLogRole() {
		return LogRole;
	}

	public void setLogRole(String logRole) {
		LogRole = logRole;
	}

	public String getLogStatus() {
		return LogStatus;
	}

	public void setLogStatus(String logStatus) {
		LogStatus = logStatus;
	}

	public int getLogId() {
		return LogId;
	}

	public void setLogId(int logId) {
		LogId = logId;
	}

}
