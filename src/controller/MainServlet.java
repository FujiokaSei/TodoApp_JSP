package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.PriorityDao;
import dao.TaskDao;
import dao.TaskTypeDao;
import domain.Priority;
import domain.Task;
import domain.TaskType;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			PriorityDao priorityDao = DaoFactory.createPriorityDao();
			List<Task> taskList = taskDao.findDoing();
			List<TaskType> taskTypeList = taskTypeDao.findAll();
			List<Priority> priorityList = priorityDao.findAll();

			/*統計のデータの処理を入れる
			 *
			 *
			 * */
			//findAllCount()のテスト
			Integer allCount = taskDao.findAllCount();
			Integer doneCount = taskDao.findDoneCount();
			Integer notDoneCount = allCount - doneCount;
			Double completingRate = Math.floor((double) doneCount / allCount * 100);


			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
			Date now = new Date();
			String nowSdf = sdf.format(now);

			request.setAttribute("now", nowSdf);
			request.setAttribute("taskList", taskList);
			request.setAttribute("taskTypeList", taskTypeList);
			request.setAttribute("priorityList", priorityList);
			request.setAttribute("allCount", allCount);
			request.setAttribute("doneCount", doneCount);
			request.setAttribute("notDoneCount", notDoneCount);
			request.setAttribute("completingRate", completingRate);

			request.getRequestDispatcher("WEB-INF/view/main.jsp")
					.forward(request, response);

			System.out.println("doGet");
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
		/*
		 * TODO:①データを受け取る
		 *
		 * TODO:②データをセットする
		 *
		 * TODO:③main.jspにフォワードする
		 * */

		//		try {
		//			TaskDao taskDao = DaoFactory.createTaskDao();
		//			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
		//			List<Task> taskList = taskDao.findAll();
		//			List<TaskType> taskTypeList = taskTypeDao.findAll();
		//
		//			/*統計のデータの処理を入れる
		//			 *
		//			 *
		//			 * */
		//			//findAllCount()のテスト
		//			Integer num = taskDao.findAllCount();
		//
		//			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
		//			Date now = new Date();
		//			String nowSdf = sdf.format(now);
		//
		//			request.setAttribute("now", nowSdf);
		//			request.setAttribute("taskList", taskList);
		//			request.setAttribute("taskTypeList", taskTypeList);
		//
		//			request.getRequestDispatcher("WEB-INF/view/main.jsp")
		//					.forward(request, response);
		//
		//
		//			System.out.println("doPOST");
		//
		//		} catch (Exception e) {
		//			throw new ServletException(e);
		//		}

		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			PriorityDao priorityDao = DaoFactory.createPriorityDao();
			List<Task> taskList = taskDao.findDoing();
			List<TaskType> taskTypeList = taskTypeDao.findAll();
			List<Priority> priorityList = priorityDao.findAll();

			/*統計のデータの処理を入れる
			 *
			 *
			 * */
			//findAllCount()のテスト
			Integer allCount = taskDao.findAllCount();
			Integer doneCount = taskDao.findDoneCount();
			Integer notDoneCount = allCount - doneCount;
			Double completingRate = Math.floor((double) doneCount / allCount * 100);

			SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd'T'HH:mm");
			Date now = new Date();
			String nowSdf = sdf.format(now);

			request.setAttribute("now", nowSdf);
			request.setAttribute("taskList", taskList);
			request.setAttribute("taskTypeList", taskTypeList);
			request.setAttribute("priorityList", priorityList);
			request.setAttribute("allCount", allCount);
			request.setAttribute("doneCount", doneCount);
			request.setAttribute("notDoneCount", notDoneCount);
			request.setAttribute("completingRate", completingRate);

			request.getRequestDispatcher("WEB-INF/view/main.jsp")
					.forward(request, response);

			System.out.println("doGet");
		}

		catch (Exception e) {
			throw new ServletException(e);
		}

	}

}
