package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fanye.dao.MovieDao;
import com.fanye.model.Movie;
import com.fanye.model.PageBean;
import com.fanye.util.DbUtil;
import com.fanye.util.JsonUtil;
import com.fanye.util.ResponseUtil;
import com.fanye.util.StringUtil;

public class MovieServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	DbUtil dbUtil=new DbUtil();
	MovieDao movieDao=new MovieDao();

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
		}else if("listFG".equals(action)){
			this.listFGAction(request,response);
		}
	}
	
	
	private void listAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		Movie movie=new Movie();
		String s_movieName=request.getParameter("s_movieName");
		if(StringUtil.isNotEmpty(s_movieName)){
			movie.setMovieName(s_movieName);
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Connection con=null;
		
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(movieDao.movieList(con, pageBean, movie));
			int total=movieDao.movieCount(con, movie);
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
	
	private void listFGAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Movie movie=new Movie();
		HttpSession session=request.getSession();
		Connection con=null;
		
		try{
			con=dbUtil.getCon();
			List<Movie> movieList=movieDao.movieListFG(con, movie);
			session.setAttribute("movieList", movieList);
			request.getRequestDispatcher("mainTemp.jsp").forward(request, response);
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
		String movieId=request.getParameter("movieId");
		String movieName=request.getParameter("movieName");
		String movieMainActor=request.getParameter("movieMainActor");
		String movieDirector=request.getParameter("movieDirector");
		String movieDuration=request.getParameter("movieDuration");
		String movieDescription=request.getParameter("movieDescription");
		
		Movie movie=new Movie(movieName,movieMainActor,movieDirector,Integer.parseInt(movieDuration),movieDescription);
		
		if(StringUtil.isNotEmpty(movieId)){
			movie.setMovieId(Integer.parseInt(movieId));
		}
		
		Connection con=null;
		
		try{
			con=dbUtil.getCon();
			int saveNum=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(movieId)){
				saveNum=movieDao.movieUpdate(con, movie);
			}else{
				saveNum=movieDao.movieAdd(con, movie);
			}
			if(saveNum==1){
				result.put("success", "true");
			}else{
				result.put("errorMsg", "±£´æÊ§°Ü£¡");
			}
			result.put("data", movie);
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
			throws ServletException, IOException{
		String delId=request.getParameter("id");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNum=movieDao.movieDelete(con, delId);
			if(delNum==1){
				result.put("success", "true");
				result.put("delNum", delNum);
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
