<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%
	//response.setIntHeader("Refresh", 5);
	session.setAttribute("CNT", session.getAttribute("CNT") == null ? 1 : ((Integer) session.getAttribute("CNT") + 1));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function Decrement() {
		timer = document.getElementById("CT");
		timer.innerHTML = timer.innerHTML - 1;
		if (timer.innerHTML != 0) {
			setTimeout('Decrement()', 1000);
			
		}
	}
</script>
</head>
<body onload="setTimeout('Decrement()', 500)">
	<h1>
		欢迎：<%=session.getAttribute("user")%></h1>
	<fieldset>
		<legend>你的Session相关信息</legend>
		<ul>
			<li>Session ID: <%=session.getId()%></li>
			<li>Session创建时间: <%=new Date(session.getCreationTime())%></li>
			<li>Session上次访问时间: <%=new Date(session.getLastAccessedTime())%></li>
			<li>Session访问次数：<%=session.getAttribute("CNT")%>
			<li>Session有效时间倒计时(秒):
				<em><strong id="CT"><%=session.getMaxInactiveInterval()%></strong></em>
			</li>
			<li>Session是新的么?<%=session.isNew()%></li>

		</ul>
		<a href="index.html">回到登陆页面</a>
	</fieldset>

</body>
</html>