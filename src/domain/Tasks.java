package domain;

import java.util.Date;

public class Tasks {

	private String title;
	private String detail;
	private Date addingTime;
	private Date timeLimit;
	private Integer userId;
	private Integer taskTypeId;

	//コンストラクタ
	public Tasks() {}
	public Tasks(String title, String detail, Date addingTime, Date timeLimit, Integer userId, Integer taskTypeId) {
		this.title = title;
		this.detail = detail;
		this.addingTime = addingTime;
		this.timeLimit = timeLimit;
		this.userId = userId;
		this.taskTypeId = taskTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getAddingTime() {
		return addingTime;
	}

	public void setAddingTime(Date addingTime) {
		this.addingTime = addingTime;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

}
