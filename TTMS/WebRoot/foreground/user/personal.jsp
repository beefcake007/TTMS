<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainTemp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css ">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<title>个人中心</title>
</head>
<body style="margin:1px;">
<jsp:include page="/foreground/movie/header.jsp"></jsp:include>
<div class="data_list">
	<div align="center" style="margin-top:100px; font-size:36px;color:red;">欢迎来到个人中心</div>
	<div class="data_info" style="font-size:18px; color:blue;">
		<p>最近的订单记录：</p>
	</div>
	<div class="data_content"  style="margin-top:15px;">
		<table class="table table-hover table-bordered">
			<tr>
				<th>编号</th>
				<th>会员姓名</th>
				<th>会员等级</th>
				<th>剧目名称</th>
				<th>剧目价格</th>
				<th>剧目时间</th>
				<th>演出厅</th>
				<th>座位</th>
			</tr>
			<c:forEach items="${buyList }" var="buy" varStatus="status">
				<tr>
					<td>${status.count }</td>			
					<td>${buy.customerName }</td>
					<td>${buy.className }</td>
					<td>${buy.movieName }</td>
					<td>${buy.orderAdjustedPrice }</td>
					<td><fmt:formatDate value="${buy.orderBuyDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${buy.hallName }</td>
					<td>${buy.seatRow }排${buy.seatColumn }列</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<nav aria-label="Page navigation">
			<ul class="pagination" >
				${pageCode }
			</ul>
		</nav>
	</div>
</div>
</body>
</html>