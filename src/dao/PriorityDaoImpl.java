package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Priority;

public class PriorityDaoImpl implements PriorityDao {

	private DataSource ds;

	public PriorityDaoImpl(DataSource ds) {
		this.ds = ds;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public List<Priority> findAll() throws Exception {
		List<Priority> priorityList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM task_board.prioritys;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				priorityList.add(mapToPriority(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return priorityList;
	}

	@Override
	public Priority findById(Integer id) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void insert(Priority priority) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void update(Priority priority) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void delete(Priority priority) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	protected Priority mapToPriority(ResultSet rs) throws Exception {
		Priority priority = new Priority();
		priority.setId(rs.getInt("id"));
		priority.setName(rs.getString("name"));
		return priority;

	}

}
