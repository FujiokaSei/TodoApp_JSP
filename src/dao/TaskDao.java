package dao;

import java.util.List;

import domain.Task;

public interface TaskDao {

	List<Task> findAll() throws Exception;

	List<Task> findNotDone() throws Exception;

	Task findById(Integer id) throws Exception;

	void insert(Task task) throws Exception;

	void update(Task task) throws Exception;

	void delete(Task task) throws Exception;

	void complete(Task task) throws Exception;

	//↓統計機能(statistics)↓
	Integer findDoneCount() throws Exception;

	Integer findNotDoneCount() throws Exception;

	Integer findAllCount() throws Exception;

}
