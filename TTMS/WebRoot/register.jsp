<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="bootstrap3/css/bootstrap.min.css ">
<link rel="stylesheet" href="bootstrap3/css/bootstrap-theme.css">
<script src="bootstrap3/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap3/js/bootstrap.min.js"></script>
<title>达塔</title>
<style type="text/css">
body{
	margin:0px;
	padding:0px;
	background-image:url(images/register.jpg);
	background-size:100%,100%;
}
.form{
	background:rgba(255,255,255,0.5);
	width:50%;
	margin:100px auto;
	border-radius: 30px;
}
.form-horizontal .form-title{
	display: block;
    font-size: 35px;
    font-weight: 700;
    padding: 35px 0;
	margin-left:85px;
    border-bottom: 1px solid #f0f0f0;
    margin-bottom: 30px;
}
.form-horizontal .form-group{
    padding: 0 40px;
    margin: 0 0 25px 0;
    position: relative;
}
.form-horizontal .form-group{
    padding: 0 40px;
    margin: 0 0 25px 0;
    position: relative;
}
.form-horizontal .form-control{
    background: #f0f0f0;
    border: none;
    border-radius: 20px;
    box-shadow: none;
    padding: 0 20px 0 45px;
    height: 40px;
    transition: all 0.3s ease 0s;
}
.form-horizontal .form-control:focus{
    background: #e0e0e0;
    box-shadow: none;
    outline: 0 none;
}
.form-horizontal .btn{
    float: right;
    font-size: 14px;
    color: #fff;
    background: #00b4ef;
    border-radius: 30px;
    padding: 10px 25px;
    border: none;
    text-transform: capitalize;
    transition: all 0.5s ease 0s;
}
.form-horizontal .btn:hover{
    color: #000;
    background: #C0C0C0;
    opacity:0.7;
    text-transform: capitalize;
    transition: all 0.5s ease 0s;
}
</style>
<script type="text/javascript">
	var a=b=c=d=0;
	function checkName(){
		var userName=document.getElementById("userName").value;
		if(userName==null||userName==""){
			document.getElementById("error").innerHTML="用户名不能为空";
			return false;
		}else{
			$.post("vip?action=check",{userName:userName},function(result){
				var result=eval('('+result+')');
				if(result.success){
					document.getElementById("error").innerHTML=result.exist;
					a=1;
					return true;
				}else{
					document.getElementById("error").innerHTML=result.exist;
					return false;
				}
			});
		}
	}
	
	function checkEmail(){
		var email=document.getElementById("email").value;
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
		if(reg.test(email)){
			document.getElementById("error").innerHTML="邮箱可用";
			b=1;
			return true;
		}else{
			document.getElementById("error").innerHTML="邮箱不可用";
			return false;
		}
	}
	
	function checkMobile(){
		var reg = /^1[0-9]{10}$/;
		var mobile=document.getElementById("mobile").value;
		if(reg.test(mobile)){
			document.getElementById("error").innerHTML="手机号可用";
			c=1;
			return true;
		}else{
			document.getElementById("error").innerHTML="手机号不可用";
			return false;
		}
	}
	
	function checkPassWord(){
		var passWord=document.getElementById("passWord").value;
		var rePassWord=document.getElementById("rePassWord").value;
		if(passWord==null||passWord==""){
			document.getElementById("error").innerHTML="密码不能为空";
			return false;
		}else{
			if(passWord!=rePassWord){
				document.getElementById("error").innerHTML="密码不相同";
				return false;
			}else{
				document.getElementById("error").innerHTML="密码相同";
				d=1;
				return true;
			}
		}
	}
	
	function checkForm(){
		if(a==1&&b==1&&c==1&&d==1){
			return true;
		}else{
			return false;
		}
	}
</script>
</head>
<body>
<div class="container">  
   <div class="form row">  
       <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form" method="post" action="vip?action=register" onsubmit="return checkForm()">  
           	<h3 class="form-title">用户注册</h3> 
           <div class="col-sm-9 col-md-9">  
                <div class="form-group">  
                   <i class="fa fa-user fa-lg"></i>
                   <input class="form-control required" type="text" placeholder="用户名" name="userName" id="userName" onblur="checkName()"/><font id="name"></font>
               </div>
               <div class="form-group">  
                       <i class="fa fa-envelope fa-lg"></i>  
                       <input class="form-control eamil" type="text" placeholder="电子邮箱" name="email" id="email" onblur="checkEmail()"/>
               </div>
               <div class="form-group">  
                       <i class="fa fa-envelope fa-lg"></i>  
                       <input class="form-control mobile" type="text" placeholder="手机号" name="mobile" id="mobile" onblur="checkMobile()"/>  
               </div> 
               <div class="form-group">  
                       <i class="fa fa-lock fa-lg"></i>  
                       <input class="form-control required" type="password" placeholder="密码" id="passWord" name="passWord"/>  
               </div>  
               <div class="form-group">  
                       <i class="fa fa-check fa-lg"></i>  
                       <input class="form-control required" type="password" placeholder="再次输入密码" name="rePassWord" id="rePassWord" onblur="checkPassWord()"/>  
               </div>  
               <div class="form-group">  
                   <input type="submit" class="btn btn-success pull-right" value="注册"/>  
                   <input type="button" class="btn btn-info pull-left" id="back_btn" value="返回" onclick="window.location.href='index.jsp'"/>  
               </div>  
           </div>
           <font id="error" color="red">${error }</font>
       </form>  
   </div>  
</div>
</body>
</html>