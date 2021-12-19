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
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			System.out.println("id:" + id);

			TaskDao taskDao = DaoFactory.createTaskDao();
			Task editTask = taskDao.findById(id);
			String mode="editMode";

			request.setAttribute("mode", mode);
			request.setAttribute("editTask", editTask);
			request.getRequestDispatcher("/main")
					.forward(request, response);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
