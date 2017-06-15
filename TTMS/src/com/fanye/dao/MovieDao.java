package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fanye.model.Movie;
import com.fanye.model.PageBean;
import com.fanye.util.StringUtil;

public class MovieDao {

	public ResultSet movieList(Connection con,PageBean pageBean,Movie movie)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_movie");
		if(StringUtil.isNotEmpty(movie.getMovieName())){
			sb.append(" and movieName like '%"+movie.getMovieName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public List<Movie> movieListFG(Connection con,Movie movie)throws Exception{
		List<Movie> list=new ArrayList<Movie>();
		StringBuffer sb=new StringBuffer("select * from t_movie");
		if(StringUtil.isNotEmpty(movie.getMovieName())){
			sb.append(" and movieName like '%"+movie.getMovieName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet re=pstmt.executeQuery();
		while(re.next()){
			Movie m=new Movie();
			m.setMovieId(re.getInt("movieId"));
			m.setMovieName(re.getString("movieName"));
			m.setMovieMainActor(re.getString("movieMainActor"));
			m.setMovieDirector(re.getString("movieDirector"));
			m.setMovieDuration(re.getInt("movieDuration"));
			m.setMovieDescription(re.getString("movieDescription"));
			m.setMovieImage("images/movie/"+re.getString("movieName")+".jpg");
			list.add(m);
		}
		return list;
	}
	
	public Movie movieListFGSingle(Connection con,int movieId)throws Exception{
		Movie m=new Movie();
		String sql="select * from t_movie where movieId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, movieId);
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			
			m.setMovieId(re.getInt("movieId"));
			m.setMovieName(re.getString("movieName"));
			m.setMovieMainActor(re.getString("movieMainActor"));
			m.setMovieDirector(re.getString("movieDirector"));
			m.setMovieDuration(re.getInt("movieDuration"));
			m.setMovieDescription(re.getString("movieDescription"));
			m.setMovieImage("images/movie/"+re.getString("movieName")+".jpg");
		}
		return m;
	}
	
	public int movieCount(Connection con,Movie movie)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_movie ");
		if(StringUtil.isNotEmpty(movie.getMovieName())){
			sb.append(" and movieName like '%"+movie.getMovieName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int movieUpdate(Connection con,Movie movie)throws Exception{
		String sql="update t_movie set movieName=?,movieMainActor=?,movieDirector=?,movieDuration=?,movieDescription=? where movieId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, movie.getMovieName());
		pstmt.setString(2, movie.getMovieMainActor());
		pstmt.setString(3, movie.getMovieDirector());
		pstmt.setInt(4, movie.getMovieDuration());
		pstmt.setString(5, movie.getMovieDescription());
		pstmt.setInt(6, movie.getMovieId());
		return pstmt.executeUpdate();
	}
	
	public int movieDelete(Connection con,String delId)throws Exception{
		String sql="delete from t_movie where movieId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, delId);
		return pstmt.executeUpdate();
	}
	
	public int movieAdd(Connection con,Movie movie)throws Exception{
		String sql="insert into t_movie values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, movie.getMovieName());
		pstmt.setString(2, movie.getMovieMainActor());
		pstmt.setString(3, movie.getMovieDirector());
		pstmt.setInt(4, movie.getMovieDuration());
		pstmt.setString(5, movie.getMovieDescription());
		return pstmt.executeUpdate();
	}
}
