package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.PriorityDao;
import dao.TaskDao;
import dao.TaskTypeDao;
import domain.Priority;
import domain.Task;
import domain.TaskType;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			PriorityDao priorityDao = DaoFactory.createPriorityDao();
			List<TaskType> taskTypeList = taskTypeDao.findAll();
			List<Priority> priorityList = priorityDao.findAll();
			List<Task> taskList = new ArrayList<>();

			HttpSession session = request.getSession();
			String orderId = request.getParameter("order");

			if (orderId != null) {//nullではない場合
				if (orderId.equals("2")) {//orderIdがあれば、sessionに格納する
					session.setAttribute("orderId", "2");
					taskList = taskDao.findDoingPriorityOrder();
					request.setAttribute("selectedOrderId", "2");
				} else if (orderId.equals("1")) {
					session.setAttribute("orderId", "1");
					taskList = taskDao.findDoingDeadlineOrder();
					request.setAttribute("selectedOrderId", "1");
				} else {//1,2以外の値の場合、何もしない
				}
			}

			else {//nullの場合、デフォルトのidを取り出す。
				orderId = (String) session.getAttribute("orderId");

				if (orderId.equals("2")) {
					taskList = taskDao.findDoingPriorityOrder();
					request.setAttribute("selectedOrderId", "2");
				} else if (orderId.equals("1")) {
					taskList = taskDao.findDoingDeadlineOrder();
					request.setAttribute("selectedOrderId", "1");

				} else {
					taskDao.findDoingDeadlineOrder();
					request.setAttribute("selectedOrderId", "1");

				}
			}
			orderId = (String) session.getAttribute("orderId");
			request.setAttribute("orderId", orderId);

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
		}

		catch (
		Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			TaskDao taskDao = DaoFactory.createTaskDao();
			TaskTypeDao taskTypeDao = DaoFactory.createTaskTypeDao();
			PriorityDao priorityDao = DaoFactory.createPriorityDao();
			List<TaskType> taskTypeList = taskTypeDao.findAll();
			List<Priority> priorityList = priorityDao.findAll();
			List<Task> taskList = new ArrayList<>();

			HttpSession session = request.getSession();
			String orderId = request.getParameter("order");

			if (orderId != null) {//nullではない場合
				if (orderId.equals("2")) {//orderIdがあれば、sessionに格納する
					session.setAttribute("orderId", "2");
					taskList = taskDao.findDoingPriorityOrder();
					request.setAttribute("selectedOrderId", "2");
				} else if (orderId.equals("1")) {
					session.setAttribute("orderId", "1");
					taskList = taskDao.findDoingDeadlineOrder();
					request.setAttribute("selectedOrderId", "1");
				} else {//1,2以外の値の場合、何もしない
				}
			}

			else {//nullの場合、デフォルトのidを取り出す。
				orderId = (String) session.getAttribute("orderId");

				if (orderId.equals("2")) {
					taskList = taskDao.findDoingPriorityOrder();
					request.setAttribute("selectedOrderId", "2");
				} else if (orderId.equals("1")) {
					taskList = taskDao.findDoingDeadlineOrder();
					request.setAttribute("selectedOrderId", "1");

				} else {
					taskDao.findDoingDeadlineOrder();
					request.setAttribute("selectedOrderId", "1");

				}
			}
			orderId = (String) session.getAttribute("orderId");
			request.setAttribute("orderId", orderId);

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
		}

		catch (
		Exception e) {
			throw new ServletException(e);
		}
	}
}
