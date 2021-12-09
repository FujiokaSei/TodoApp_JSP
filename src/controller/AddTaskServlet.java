package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.TaskDao;
import domain.Task;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			List<Task> taskList = taskDao.findAll();
			request.setAttribute("taskList", taskList);
			request.getRequestDispatcher("WEB-INF/view/addTask.jsp")
					.forward(request, response);
		}

		catch (Exception e) {
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TaskDao taskDao = DaoFactory.createTaskDao();

	}

}
