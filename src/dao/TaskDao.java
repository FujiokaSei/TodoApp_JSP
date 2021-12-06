package dao;

import java.util.List;

import domain.Task;

public interface TaskDao {

	List<Task> findAll() throws Exception;

	Task findById(Integer id) throws Exception;

	void insert(Task task) throws Exception;

	void update(Task task) throws Exception;

	void delete(Task task) throws Exception;

}
