<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
 %>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="navbar-brand" href="#">达塔</a>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath }/movie?action=listFG"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;主页</a>
                    </li>
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;卖票</a>
                    </li>
                    <li>
                        <a href="#"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;卖票</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/order?action=userList&customerId=${currentUser.customerId}"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;个人中心</a>
                    </li>
                </ul>
                <form name="myForm" class="navbar-form pull-right" method="post" action="">
                    <input class="span2" id="s_title" name="s_title"  type="text" style="margin-top:5px;height:30px;" placeholder="你想知道啥...">
                    <button type="submit" class="btn" onkeydown="if(event.keyCode==13) myForm.submit()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;搜索电影</button>
                </form>
            </div>
        </div>
    </div>
</div>