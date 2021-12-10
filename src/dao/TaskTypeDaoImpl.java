package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.TaskType;

public class TaskTypeDaoImpl implements TaskTypeDao {

	private DataSource ds;

	public TaskTypeDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<TaskType> findAll() throws Exception {
		List<TaskType> taskTypeList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM task_types";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				taskTypeList.add(mapToTaskType(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return taskTypeList;
	}

	@Override
	public TaskType findById(Integer id) throws Exception {
		TaskType taskType = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM task_types WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				taskType = mapToTaskType(rs);
			}

		} catch (Exception e) {
			throw e;
		}

		return taskType;

	}

	@Override
	public void insert(TaskType task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(TaskType task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(TaskType task) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	protected TaskType mapToTaskType(ResultSet rs) throws Exception {
		TaskType taskType = new TaskType();
		taskType.setId(rs.getInt("id"));
		taskType.setName(rs.getString("name"));
		return taskType;

	}

}
