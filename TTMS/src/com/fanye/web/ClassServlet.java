package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fanye.dao.ClassDao;
import com.fanye.model.Classs;
import com.fanye.model.PageBean;
import com.fanye.util.DbUtil;
import com.fanye.util.JsonUtil;
import com.fanye.util.ResponseUtil;
import com.fanye.util.StringUtil;

public class ClassServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	ClassDao classDao=new ClassDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		if("comBoList".equals(action)){
			this.comBoList(request,response);
		}else if("list".equals(action)){
			this.list(request, response);
		}
	}
	
	private void comBoList(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("classId", "");
			jsonObject.put("className", "«Î—°‘Ò......");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(classDao.classList(con, null, new Classs())));
			ResponseUtil.write(response, jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void list(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Classs cla=new Classs();
		String s_className=request.getParameter("s_className");
		if(StringUtil.isNotEmpty(s_className)){
			cla.setClassName(s_className);
		}
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(classDao.classList(con, pageBean, cla));
			int total=classDao.classCount(con, cla);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
