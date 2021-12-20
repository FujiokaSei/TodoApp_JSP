package dao;

import java.util.List;

import domain.Priority;

public interface PriorityDao {
	List<Priority> findAll() throws Exception;

	Priority findById(Integer id) throws Exception;

	void insert(Priority priority) throws Exception;

	void update(Priority priority) throws Exception;

	void delete(Priority priority) throws Exception;
}
