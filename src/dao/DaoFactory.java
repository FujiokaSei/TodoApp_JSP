package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/***
 * 各domain（クラス）のDAOオブジェクトを生成するためのDaoFactoryクラス
 */
public class DaoFactory {

	public static TaskDao createTaskDao() {
		return new TaskDaoImpl(getDataSource());
	}

	public static TaskTypeDao createTaskTypeDao() {
		return new TaskTypeDaoImpl(getDataSource());
	}

	public static UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());
	}

	public static PriorityDao createPriorityDao() {
		return new PriorityDaoImpl(getDataSource());
	}

	private static DataSource getDataSource() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/task_board");
		} catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					throw new RuntimeException(e1);
				}
			}
			throw new RuntimeException(e);
		}
		return ds;
	}
}
