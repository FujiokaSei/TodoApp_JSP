package domain;

import java.util.Date;

public class Task {

	private Integer id;
	private String title;
	private String detail;
	private Date addingTime;
	private Date timeLimit;
	private Integer userId;
	private Integer taskTypeId;
	private Integer priorityId;

	//コンストラクタの定義
	public Task() {
	}

	public Task(String title, String detail, Date addingTime, Date timeLimit, Integer userId, Integer taskTypeId) {
		this.title = title;
		this.detail = detail;
		this.addingTime = addingTime;
		this.timeLimit = timeLimit;
		this.userId = userId;
		this.taskTypeId = taskTypeId;
	}

	//getter,setterの定義
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}

}
