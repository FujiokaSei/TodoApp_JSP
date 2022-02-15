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
	}

	@Override
	public List<Priority> findAll() throws Exception {
		List<Priority> priorityList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM todo_app.prioritys;";
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

	/*	@Override
		public Priority findById(Integer id) throws Exception {
			return null;
		}

		@Override
		public void insert(Priority priority) throws Exception {

		}

		@Override
		public void update(Priority priority) throws Exception {
		}

		@Override
		public void delete(Priority priority) throws Exception {
		}*/

	protected Priority mapToPriority(ResultSet rs) throws Exception {
		Priority priority = new Priority();
		priority.setId(rs.getInt("id"));
		priority.setName(rs.getString("name"));
		return priority;
	}
}
