<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="elements/head.jsp"  %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<body>
<div id="main">
	<div class="mainbox">
		<div class="title myMessage png"></div>		
		<div class="menu">
			<span>当前用户：<a href="main.jsp">${sessionScope.username}</a></span>
			<span><a href="UserServlet?action=findUsers">发短消息</a></span>
			<span><a href="UserServlet?action=logout">退出</a></span>
		</div>
		<div id="error">${error}</div>
		<div class="content messageList">
			<ul>
				<c:forEach items="${msgList}" var="msg">

					<li<c:if test="${msg.state == 1}"> class="unReaded" </c:if>>

						<em>${msg.msgCreateDate}</em>
						<em><a href="newMsg.jsp?name=${msg.username}">回信</a></em>
						<em><a href="MsgServlet?action=delete&msgid=${msg.msgid}">删除</a></em>
						<p>
							<strong><a href="MsgServlet?action=msg&msgid=${msg.msgid}">${msg.title}</a></strong>
								<%--如果字数超过8个 展示前面7个 后面省略号--%>
							<c:if test="${fn:length(msg.msgcontent) > 8}">
								${fn:substring(msg.msgcontent,0,7)}...
							</c:if>
							<c:if test="${fn:length(msg.msgcontent) <=  8}">
								${msg.msgcontent}
							</c:if>
						</p>
					<li>
				</c:forEach>

		  	 </ul>
		</div>
	</div>
</div>
</body>
</html>
