package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.TaskDao;
import domain.Task;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String strId = request.getParameter("id");
			Task task = new Task();
			task.setId(Integer.parseInt(strId));
			TaskDao taskDao = DaoFactory.createTaskDao();
			taskDao.delete(task);
			request.getRequestDispatcher("/main")
					.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
