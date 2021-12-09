package dao;

import java.util.List;

import domain.TaskType;

public interface TaskTypeDao {

	List<TaskType> findAll() throws Exception;

	TaskType findById(Integer id) throws Exception;

	void insert(TaskType task) throws Exception;

	void update(TaskType task) throws Exception;

	void delete(TaskType task) throws Exception;

}
