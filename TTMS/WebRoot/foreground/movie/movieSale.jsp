<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>达塔影片</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/mainTemp.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css ">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.css">
<script src="${pageContext.request.contextPath}/bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<style type="text/css">
.header .card{
	clear: both;
	display: block;
	margin:auto;
	width:70%;
}
.movieName{
	font-family:Georgia;
	font-weight:bold;
	font-size:25px
}
hr.style-one {
    border: 0;
    height: 1px;
    background: #333;
    background-image: linear-gradient(to right, #ccc, #333, #ccc);
}
p{
	font-size:16px;
	margin:0px;
}
.movieDescription{
	font-family:Georgia;
	font-weight:bold;
	font-size:18px
}
.saleTable{
	margin:20px;
}
</style>
</head>
<body>
<jsp:include page="/foreground/movie/header.jsp"></jsp:include>
<div class="container">
	<div class="header">
		<div class="row">
			<div class="col-xs-6 col-md-4">
				<img src="${pageContext.request.contextPath }/${movieList.movieImage}" class="card">
			</div>
			<div class="col-xs-12 col-md-8">
				<span class="movieName">${movieList.movieName }</span>
				<hr class="style-one">
				<p>导演：${movieList.movieDirector }</p><br>
				<p>主演：${movieList.movieMainActor }</p><br>
				<p>片长：${movieList.movieDuration }&nbsp;分钟</p><br>
				<span class="movieDescription">影片剧情</span>
				<hr class="style-one">
				<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${movieList.movieDescription }</p>
			</div>
		</div>
		<div class="saleTable">
			<table class="table table-striped table-hover">
				<tr>
					<th>编号</th>
					<th>电影名</th>
					<th>电影厅</th>
					<th>票价</th>
					<th>开始时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="schedule" items="${scheduleList }" varStatus="status">
				<tr>
					<td>${schedule.scheduleId }</td>
					<td>${schedule.movieName}</td>
					<td>${schedule.hallName }</td>
					<td>${schedule.schedulePrice}</td>
					<td><fmt:formatDate value="${schedule.scheduleBeginDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
					<td><button type="button" class="btn btn-info btn-primary btn-xs" onclick="javascript:window.location.href='seat?action=sale&movieId=${movieList.movieId}&hallId=${schedule.hallId }&scheduleBeginDate=${schedule.scheduleBeginDate }&scheduleId=${schedule.scheduleId }'">购票</button></td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>