package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Task;

public class TaskDaoImpl implements TaskDao {

	private DataSource ds;

	public TaskDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Task> findAll() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		List<Task> taskList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
//			String sql = "SELECT * FROM task_board.tasks;";
			String sql = "SELECT * FROM tasks;";
//			String sql = "SELECT *,locations.name AS location_name "
//					+ "FROM tasks JOIN locations "
//					+ "ON tasks.location_id = locations.id "
//					+ "ORDER BY tasks.id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				taskList.add(mapToTask(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return taskList;
	}

	@Override
	public Task findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insert(Task task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(Task task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Task task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	protected Task mapToTask(ResultSet rs) throws Exception {
		Task task = new Task();
		task.setId((Integer) rs.getObject("id"));
		task.setTitle(rs.getString("title"));
		task.setDetail(rs.getString("detail"));
		task.setAddingTime(rs.getTimestamp("adding_time"));//TODO:本当にGet出来るか確認すること
		task.setTimeLimit(rs.getTimestamp("time_limit"));
		task.setUserId(rs.getInt("user_id"));
		task.setTaskTypeId(rs.getInt("task_type_id"));
		return task;
	}

}
