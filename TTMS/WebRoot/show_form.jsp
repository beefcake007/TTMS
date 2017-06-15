<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<table class="dv-table" style="width:100%;border:1px solid #ccc;padding:5px;margin-top:5px;">
		<tr>
			<td>电影名</td>
			<td><input name="movieName" class="easyui-validatebox" required="true"></input></td>
			<td>主演</td>
			<td><input name="movieMainActor" class="easyui-validatebox" required="true"></input></td>
		</tr>
		<tr>
			<td>导演</td>
			<td><input name="movieDirector" required="true" validType="email"></input></td>
			<td>时长</td>
			<td><input name="movieDuration" class="easyui-validatebox" required="true"></input></td>
		</tr>
		<tr>
			<td>电影简介</td>
			<td><textarea rows="7" cols="50" required="true" name="movieDescription"></textarea></td>
		</tr>
	</table>
	<div style="padding:5px 0;text-align:right;padding-right:30px">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveItem(${param.index})">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="cancelItem(${param.index})">关闭</a>
	</div>
</form>

</body>
</html>