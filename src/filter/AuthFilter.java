package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (session.getAttribute("orderId") == null) {
			session.setAttribute("orderId", "2");
		}

		//"taskBoard/hogehoge"など異なるURLにアクセスしようとした場合にlogin画面に遷移させる。
		String uri = req.getRequestURI();
		if (!uri.endsWith("/login") &&
				!uri.contains("/css/") &&
				!uri.contains("/js/") &&
				!uri.contains("/fonts/")) {
			if (session.getAttribute("userName") == null) {
				res.sendRedirect(req.getContextPath() + "/login");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
