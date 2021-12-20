package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
		List<Task> taskList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM task_board.tasks;";
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
		Task task = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *  FROM task_board.tasks WHERE tasks.id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				task = mapToTask(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return task;

	}

	@Override
	public void insert(Task task) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO task_board.tasks"
					+ "(title, detail, adding_time, time_limit, user_id, task_type_id)VALUES"
					+ " (?, ?, NOW(), ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getDetail());
			stmt.setObject(3, task.getTimeLimit(), Types.DATE);
			stmt.setObject(4, task.getUserId(), Types.INTEGER);
			stmt.setObject(5, task.getTaskTypeId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Task task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Task task) throws Exception {
		int id = task.getId();
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM tasks WHERE id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Task> findNotDone() throws Exception {
		List<Task> taskList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM task_board.tasks "
					+ "WHERE(task_type_id <> 3) "
					+ "ORDER BY time_limit ASC;";
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
	public void complete(Task task) throws Exception {
		int id = task.getId();
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE `task_board`.`tasks` SET `task_type_id` = '3' WHERE (`id` = ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

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

	@Override
	public Integer findDoneCount() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Integer findNotDoneCount() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Integer findAllCount() throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
