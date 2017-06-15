package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.fanye.model.Hall;
import com.fanye.model.PageBean;
import com.fanye.model.Schedule;
import com.fanye.util.StringUtil;

public class ScheduleDao {

	public boolean existUserWithHallId(Connection con,String hallId)throws Exception{
		String sql="select * from t_schedule where hallId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, hallId);
		return pstmt.executeQuery().next();
	}
	
	public ResultSet scheduleList(Connection con,PageBean pageBean,Schedule schedule)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_schedule s,t_movie m,t_hall h where s.movieId=m.movieId and s.hallId=h.hallId ");
		if(StringUtil.isNotEmpty(schedule.getMovieName())){
			sb.append(" and m.movieName like '%"+schedule.getMovieName()+"%'");
		}
		if(schedule.getHallId()!=-1){
			sb.append(" and s.hallId="+schedule.getHallId());
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public List<Schedule> scheduleFGList(Connection con,int movieId)throws Exception{
		List<Schedule> scheduleList=new ArrayList<Schedule>();
		String sql="select * from t_schedule s,t_movie m,t_hall t where s.movieId=m.movieId and s.hallId=t.hallId and s.movieId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, movieId);
		ResultSet re=pstmt.executeQuery();
		while(re.next()){
			Schedule schedule=new Schedule();
			schedule.setScheduleId(re.getInt("scheduleId"));
			schedule.setMovieName(re.getString("movieName"));
			schedule.setHallName(re.getString("hallName"));
			schedule.setHallId(re.getInt("hallId"));
			schedule.setSchedulePrice(re.getFloat("schedulePrice"));
			schedule.setScheduleBeginDate(re.getTimestamp("scheduleBeginDate"));
			scheduleList.add(schedule);
		}
		return scheduleList;
	}
	
	public Schedule saleFGList(Connection con,int movieId,int hallId,Timestamp scheduleBeginDate)throws Exception{
		Schedule schedule=new Schedule();
		String sql="select * from t_schedule s,t_movie m,t_hall t where s.movieId=m.movieId and s.hallId=t.hallId and s.movieId=? and s.hallId=? and s.scheduleBeginDate=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, movieId);
		pstmt.setInt(2, hallId);
		pstmt.setTimestamp(3,scheduleBeginDate);
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			schedule.setScheduleId(re.getInt("scheduleId"));
			schedule.setMovieId(re.getInt("movieId"));
			schedule.setMovieName(re.getString("movieName"));
			schedule.setHallName(re.getString("hallName"));
			schedule.setHallId(re.getInt("hallId"));
			schedule.setSchedulePrice(re.getFloat("schedulePrice"));
			schedule.setScheduleBeginDate(re.getTimestamp("scheduleBeginDate"));
			schedule.setHallRow(re.getInt("hallRow"));
			schedule.setHallColumn(re.getInt("hallColumn"));
		}
		return schedule;
	}
	
	public JSONObject ticketSale(Connection con,int scheduleId)throws Exception{
		JSONObject jsonObject =new JSONObject();
		String sql="select * from t_schedule s,t_movie m,t_hall h where s.movieId=m.movieId and s.hallId=h.hallId and scheduleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, scheduleId);
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			jsonObject.put("schedulePrice", re.getFloat("schedulePrice"));
			jsonObject.put("scheduleBeginDate", re.getTimestamp("scheduleBeginDate"));
			jsonObject.put("movieName", re.getString("movieName"));
			jsonObject.put("hallRow", re.getInt("hallRow"));
			jsonObject.put("hallColumn", re.getInt("hallColumn"));
		}
		return jsonObject;
	}
	
	public int scheduleCount(Connection con,Schedule schedule)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_schedule s,t_movie m,t_hall h where s.movieId=m.movieId and s.hallId=h.hallId ");
		if(StringUtil.isNotEmpty(schedule.getMovieName())){
			sb.append(" and m.movieName like '%"+schedule.getMovieName()+"%'");
		}
		if(schedule.getHallId()!=-1){
			sb.append(" and s.hallId="+schedule.getHallId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int scheduleUpdate(Connection con,Schedule schedule)throws Exception{
		String sql="update t_schedule set movieId=?,hallId=?,schedulePrice=?,scheduleBeginDateTime=? where scheduleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, schedule.getMovieId());
		pstmt.setInt(2, schedule.getHallId());
		pstmt.setFloat(3, schedule.getSchedulePrice());
		pstmt.setTimestamp(4, schedule.getScheduleBeginDate());
		pstmt.setInt(5, schedule.getScheduleId());
		return pstmt.executeUpdate();
	}
	
	public int scheduleAdd(Connection con,Schedule schedule)throws Exception{
		String sql="insert into t_schedule values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, schedule.getMovieId());
		pstmt.setInt(2, schedule.getHallId());
		pstmt.setFloat(3, schedule.getSchedulePrice());
		pstmt.setTimestamp(4, schedule.getScheduleBeginDate());
		return pstmt.executeUpdate();
	}
	
	public int scheduleDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_schedule where scheduleId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
