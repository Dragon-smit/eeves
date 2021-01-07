<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="elements/head.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
  function check(){
			var title = document.getElementById("title");
			var content = document.getElementById("content");
			if(title.value == ""){
				alert("标题不能为空！");
				return false;
			}else if(content.value == ""){
				alert("内容不能为空！");
				return false;
			}
			return true;
	}
</script>
  
 <body>
 <form action="MsgServlet?action=send" method="post" onsubmit="return check()">
	<div id="main">
		<div class="mainbox">			
			<div class="menu">
				<span>当前用户：<a href="main.jsp">${sessionScope.username}</a></span>
				<span><a href="UserServlet?action=findUsers">发短消息</a></span>
				<span><a href="UserServlet?action=logout">退出</a></span>
			</div>
			<div class="content">
				<div class="message">
					
						<div class="tmenu">
							<ul class="clearfix">
								<li>
									发送给：
									<select name="toUser">

										<c:if test="${param.name != null}">
											<option selected="selected">${param.name}</option>
										</c:if>

										<c:if test="${users != null}">
											<c:forEach items="${users}" var="user">
												<option value="${user.username}">${user.username}</option>
											</c:forEach>
										</c:if>

						  	 		</select>
								</li>								
								<li>标题：<input type="text" name="title" id="title"/></li>
							</ul>
						</div>
						<div class="view">
							<textarea name="content" id="content"></textarea>
							<div class="send"><input type="submit" name="submit" value=" " /></div>
						</div>
					
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>
