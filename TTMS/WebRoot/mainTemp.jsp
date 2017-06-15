<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
 %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="style/mainTemp.css">
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css ">
<link rel="stylesheet" href="bootstrap3/css/buttons.css">
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.css">
<script src="bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
 <style type="text/css">

         .ol:hover .caption{
            opacity: 1;

        }


         img{
            z-index: 4;
        }


         .caption{
            cursor: pointer;
            position: absolute;
            opacity: 0;
            -webkit-transition:all 0.45s ease-in-out;
            -moz-transition:all 0.45s ease-in-out;
            -o-transition:all 0.45s ease-in-out;
            -ms-transition:all 0.45s ease-in-out;
            transition:all 0.45s ease-in-out;

        }
         .blur{
            background-color: rgba(0,0,0,0.5);
            width:223px;
            height:298px;
            z-index: 5;
            position: absolute;
        }

        .caption-text h1{
            text-transform: uppercase;
            font-size: 18px;
        }
         .caption-text{
            z-index: 10;
            color: #fff;
            position: absolute;
            width:223px;
            height:298px;
            text-align: center;
            top:30px;
        }

    </style>
<title>达塔票务</title>
</head>
<body style="background-color:#a7a7a7">
<jsp:include page="/foreground/movie/header.jsp"></jsp:include>
<div class="container">
	<div class="header">
		
	</div>
	<div class="slider">
		<jsp:include page="/foreground/movie/Carousel.jsp"></jsp:include>
	</div>
	<div class="content">
	<!-- 	<div class="row">
		  <div class="col-md-3">
		  	<img src="images/movie/银河护卫队2.jpg" class="card">
		  	<a href="" class="buyticket">购票</a>
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/摔跤吧！爸爸.jpg" class="card">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/拆弹专家.jpg" class="card">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/傲娇偏见.jpg" class="card">
		  </div>
		</div>
		
		<div class="row">
		  <div class="col-md-3">
		  	<img src="images/movie/速度与激情8.jpg" class="card" align="middle">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/记忆大师.jpg" class="card" align="middle">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/春娇救志明.jpg" class="card">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/超凡战队.jpg" class="card">
		  </div>
		</div>
		
		<div class="row">
		  <div class="col-md-3">
		  	<img src="images/movie/金刚：骷髅岛.jpg" class="card" align="middle">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/麻烦家族.jpg" class="card" align="middle">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/喜欢你.jpg" class="card">
		  </div>
		  <div class="col-md-3">
		  	<img src="images/movie/亚瑟王：斗兽争霸.jpg" class="card">
		  </div>
		</div> -->
		<c:forEach var="movie" items="${movieList }" varStatus="status">
			<c:if test="${status.count%4==1}">
					<div class="row">
				  <div class="col-md-3 ol">
				  	<div class="caption">
		                <div class="blur"></div>
		                <div class="caption-text">
		                    <h1>${movie.movieName }</h1>
		                    <p>主演：${movie.movieMainActor }</p>
		                    <p>导演：${movie.movieDirector }</p>
		                    <p>时长：${movie.movieDuration }</p>
		                </div>
		            </div>
				  	<img src="${movie.movieImage }" class="card" align="middle">
				  	<a href="schedule?action=listFG&movieId=${movie.movieId }" class="button button-royal button-rounded button-giant buyticket">购买</a>
				  </div>
			</c:if>
			<c:if test="${status.count%4==2}">
				  <div class="col-md-3 ol">
				  <div class="caption">
		                <div class="blur"></div>
		                <div class="caption-text">
		                    <h1>${movie.movieName }</h1>
		                    <p>主演：${movie.movieMainActor }</p>
		                    <p>导演：${movie.movieDirector }</p>
		                    <p>时长：${movie.movieDuration }</p>
		                </div>
		            </div>
				  	<img src="${movie.movieImage }" class="card" align="middle">
				  	<a href="schedule?action=listFG&movieId=${movie.movieId }" class="button button-royal button-rounded button-giant buyticket">购买</a>
				  </div>
			</c:if>
			<c:if test="${status.count%4==3}">
				  <div class="col-md-3 ol">
				  	<div class="caption">
		                <div class="blur"></div>
		                <div class="caption-text">
		                    <h1>${movie.movieName }</h1>
		                    <p>主演：${movie.movieMainActor }</p>
		                    <p>导演：${movie.movieDirector }</p>
		                    <p>时长：${movie.movieDuration }</p>
		                </div>
		            </div>
				  	<img src="${movie.movieImage }" class="card" align="middle">
				  	<a href="schedule?action=listFG&movieId=${movie.movieId }" class="button button-royal button-rounded button-giant buyticket">购买</a>
				  </div>
			</c:if>
			<c:if test="${status.count%4==0}">
				  <div class="col-md-3 ol">
				  <div class="caption">
		                <div class="blur"></div>
		                <div class="caption-text">
		                    <h1>${movie.movieName }</h1>
		                    <p>主演：${movie.movieMainActor }</p>
		                    <p>导演：${movie.movieDirector }</p>
		                    <p>时长：${movie.movieDuration }</p>
		                </div>
		            </div>
				  	<img src="${movie.movieImage }" class="card" align="middle">
				  	<a href="schedule?action=listFG&movieId=${movie.movieId }" class="button button-royal button-rounded button-giant buyticket">购买</a>
				  </div>
				  </div>
			</c:if>
				
	     </c:forEach>
		
	</div>
	<div class="footer">
		<jsp:include page="/foreground/movie/footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>