package wgz.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginServlet Service()方法开始运行");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		logger.debug("user=" + user);
		logger.debug("pass=" + pass);
		if (user.equals("1") && pass.equals("1")) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			// session.setMaxInactiveInterval(10);

			logger.debug("转向:user.jsp");
			response.sendRedirect(response.encodeRedirectURL("user.jsp"));
		} else {
			response.sendRedirect("index.html");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
