package wgz.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	public SessionFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	private final static HashSet<String> set = new HashSet<String>();

	// 0. 首先是该方法被调用
	public void init(FilterConfig fConfig) throws ServletException {
		logger.debug("init() 方法启动");
		String urls = fConfig.getInitParameter("allow-urls");// 获得init-params中的运行没有session就可以访问的网页
		StringTokenizer token = new StringTokenizer(urls, ",");// 按照,分隔开所有的网页
		while (token.hasMoreTokens()) {
			set.add(token.nextToken());// 把分隔开的所有网页加入到allowList里面
		}
	}

	// 1. 接下来是doFilter方法被调用
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		logger.debug("要访问的网页: " + url);
		if (set.contains(url)) {
			logger.debug("访问的网页" + url + "是不需要登陆的");
			chain.doFilter(request, response);
		} else {
			HttpSession session = request.getSession(false);
			logger.debug("session存在么?" + session);
			String user = (String) ((session != null) ? session.getAttribute("user") : null);
			if (user != null) {
				chain.doFilter(request, response);
			} else {
				logger.debug("用户已经登出，需要重新登陆");
				response.sendRedirect("index.html");
			}

		}

	}

}
