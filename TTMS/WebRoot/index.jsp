<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>达塔用户登录</title>
<link rel="stylesheet" href="style/index.css">
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css ">
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.css">
<script src="bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
	<div class="demo form-bg" style="padding: 20px 0;">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" action="vip?action=login" method="post">
                        <span class="heading">用户登录</span>
                        <div class="form-group">
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="用户名或电子邮箱">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" class="form-control" id="passWord" name="passWord" placeholder="密　码">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group">
                            <div class="main-checkbox">
                                <input type="checkbox" value="remember-me" id="remember" name="remember"/>
                            </div>
                            <span class="text">记住我</span>&nbsp;&nbsp;&nbsp;&nbsp;<font id="error" color="red">${error}</font>
                            <button type="submit" class="btn btn-default">登录</button>
                            <button type="button" class="btn btn-default" onclick="window.location.href='register.jsp'">注册</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
	</div>
</div>
</body>
</html>