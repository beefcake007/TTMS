package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fanye.dao.MovieDao;
import com.fanye.dao.ScheduleDao;
import com.fanye.model.Movie;
import com.fanye.model.PageBean;
import com.fanye.model.Schedule;
import com.fanye.util.DateUtil;
import com.fanye.util.DbUtil;
import com.fanye.util.JsonUtil;
import com.fanye.util.ResponseUtil;
import com.fanye.util.StringUtil;

public class ScheduleServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil=new DbUtil();
	MovieDao movieDao=new MovieDao();
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
		
		if("listFG".equals(action)){
			this.listFGAction(request,response);
		}else if("list".equals(action)){
			this.listAction(request, response);
		}else if("save".equals(action)){
			this.saveAction(request, response);
		}else if("delete".equals(action)){
			this.deleteAction(request, response);
		}
	}
	
	private void listFGAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String movieId=request.getParameter("movieId");
		HttpSession session=request.getSession();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			Movie movieList=movieDao.movieListFGSingle(con, Integer.parseInt(movieId));
			List<Schedule> scheduleList=scheduleDao.scheduleFGList(con, Integer.parseInt(movieId));
			session.setAttribute("movieList", movieList);
			session.setAttribute("scheduleList", scheduleList);
			request.getRequestDispatcher("/foreground/movie/movieSale.jsp").forward(request, response);
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
	
	private void listAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Schedule schedule=new Schedule();
		String s_movieName=request.getParameter("s_movieName");
		String s_hallId=request.getParameter("s_hallId");
		if(StringUtil.isNotEmpty(s_movieName)){
			schedule.setMovieName(s_movieName);
		}
		if(StringUtil.isNotEmpty(s_hallId)){
			schedule.setHallId(Integer.parseInt(s_hallId));
		}
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(scheduleDao.scheduleList(con, pageBean, schedule));
			int total=scheduleDao.scheduleCount(con, schedule);
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
		String scheduleId=request.getParameter("scheduleId");
		String hallId=request.getParameter("hallId");
		String movieId=request.getParameter("movieId");
		String schedulePrice=request.getParameter("schedulePrice");
		String scheduleBeginDate=request.getParameter("scheduleBeginDate");
		
		Schedule schedule=new Schedule(Integer.parseInt(movieId),Integer.parseInt(hallId),Float.parseFloat(schedulePrice),DateUtil.convertToSqlDate(scheduleBeginDate));
		
		if(StringUtil.isNotEmpty(scheduleId)){
			schedule.setScheduleId(Integer.parseInt(scheduleId));
		}
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int saveNum=0;
			if(StringUtil.isNotEmpty(scheduleId)){
				saveNum=scheduleDao.scheduleUpdate(con, schedule);
			}else{
				saveNum=scheduleDao.scheduleAdd(con, schedule);
			}
			if(saveNum>0){
				result.put("success", "true");
			}else{
				result.put("errorMsg", "±£´æÊ§°Ü£¡");
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
			int delNums=scheduleDao.scheduleDelete(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "É¾³ýÊ§°Ü£¡");
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
}
