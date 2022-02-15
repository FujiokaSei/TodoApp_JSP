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
			String sql = "SELECT * FROM todo_app.tasks;";
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
			String sql = "SELECT *  FROM todo_app.tasks WHERE tasks.id=?";
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
			String sql = "INSERT INTO todo_app.tasks"
					+ "(title, detail, adding_time, time_limit, user_id, task_type_id, priority_id)VALUES"
					+ " (?, ?, NOW(), ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getDetail());
			stmt.setObject(3, task.getTimeLimit(), Types.DATE);
			stmt.setObject(4, task.getUserId(), Types.INTEGER);
			stmt.setObject(5, task.getTaskTypeId(), Types.INTEGER);
			stmt.setObject(6, task.getPriorityId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(Task task) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE tasks SET title=?, detail=?, time_limit=?, priority_id=? WHERE id=?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getDetail());
			stmt.setObject(3, task.getTimeLimit(), Types.DATE);
			stmt.setObject(4, task.getPriorityId(), Types.INTEGER);
			stmt.setObject(5, task.getId(), Types.INTEGER);

			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
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
	public List<Task> findDoingDeadlineOrder() throws Exception {
		List<Task> taskList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM todo_app.tasks "
					+ "WHERE(task_type_id <> 3) "
					+ "ORDER BY time_limit ASC, priority_id DESC;";
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
	public List<Task> findDoingPriorityOrder() throws Exception {
		List<Task> taskList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM todo_app.tasks "
					+ "WHERE(task_type_id <> 3) "
					+ "ORDER BY priority_id DESC, time_limit ASC;";
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
			String sql = "UPDATE `todo_app`.`tasks` SET `task_type_id` = '3' WHERE (`id` = ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Integer findDoneCount() throws Exception {
		Integer doneCount = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT COUNT(*) AS count FROM todo_app.tasks WHERE task_type_id = 3;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				doneCount = mapToInt(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return doneCount;
	}

	@Override
	public Integer findNotDoneCount() throws Exception {
		return null;
	}

	@Override
	public Integer findAllCount() throws Exception {
		Integer allCount = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT COUNT(*) AS count FROM todo_app.tasks;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				allCount = mapToInt(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return allCount;
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
		task.setPriorityId(rs.getInt("priority_id"));
		return task;
	}

	protected Integer mapToInt(ResultSet rs) throws Exception {
		Integer num;
		num = rs.getInt("count");
		return num;
	}

}
