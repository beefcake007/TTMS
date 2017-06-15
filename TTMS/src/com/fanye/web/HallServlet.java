package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fanye.dao.HallDao;
import com.fanye.dao.ScheduleDao;
import com.fanye.model.Hall;
import com.fanye.model.PageBean;
import com.fanye.model.Role;
import com.fanye.util.DbUtil;
import com.fanye.util.JsonUtil;
import com.fanye.util.ResponseUtil;
import com.fanye.util.StringUtil;

public class HallServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil=new DbUtil();
	HallDao hallDao=new HallDao();
	ScheduleDao scheduleDao=new ScheduleDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		
		if("list".equals(action)){
			this.listAction(request,response);
		}else if("save".equals(action)){
			this.saveAction(request,response);
		}else if("delete".equals(action)){
			this.deleteAction(request,response);
		}else if("comBoList".equals(action)){
			this.comBoListAction(request,response);
		}
	}
	
	
	private void listAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Hall hall=new Hall();
		String s_hallName=request.getParameter("s_hallName");
		if(StringUtil.isNotEmpty(s_hallName)){
			hall.setHallName(s_hallName);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(hallDao.hallList(con, pageBean, hall));
			int total=hallDao.hallCount(con, hall);
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
	
	
	private void saveAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String hallId=request.getParameter("hallId");
		String hallName=request.getParameter("hallName");
		String hallRow=request.getParameter("hallRow");
		String hallColumn=request.getParameter("hallColumn");
		String hallDescription=request.getParameter("hallDescription");
		
		Hall hall=new Hall(hallName,Integer.parseInt(hallRow),Integer.parseInt(hallColumn),hallDescription);
		if(StringUtil.isNotEmpty(hallId)){
			hall.setHallId(Integer.parseInt(hallId));
		}
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			if(StringUtil.isNotEmpty(hallId)){
				saveNums=hallDao.hallUpdate(con, hall);
			}else{
				saveNums=hallDao.hallAdd(con, hall);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("errorMsg", "保存失败！");
			}
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
	
	private void deleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delIds=request.getParameter("delIds");
		
		Connection con=null;
		
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			String[] str=delIds.split(",");
			for(int i=0;i<str.length;i++){
				boolean f=scheduleDao.existUserWithHallId(con, str[i]);
				if(f){
					result.put("errorIndex", i);
					result.put("errorMsg","演出厅下有演出计划，不能删除");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums=hallDao.hallDelete(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败！");
			}
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
	
	private void comBoListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("hallId", "");
			jsonObject.put("hallName", "请选择......");
			jsonArray.add(jsonObject);
			jsonArray.addAll(JsonUtil.formatRsToJsonArray(hallDao.hallList(con,null,new Hall())));
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
}
