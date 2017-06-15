package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fanye.model.Order;
import com.fanye.model.PageBean;

public class OrderDao {

	public String movieList(Connection con)throws Exception{
		String movie="";
		String sql="select movieName from t_movie";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet re=pstmt.executeQuery();
		movie+="[";
		while(re.next()){
			movie+="'";
			movie+=re.getString("movieName");
			movie+="',";
		}
		movie=movie.substring(0, movie.length()-1);
		movie+="]";
//		System.out.println(movie);
		return movie;
	}
	
//	public int movieCount(Connection con,String schduleId)throws Exception{
//		String sql="select count(*) as total from t_seat where scheduleId=?";
//		PreparedStatement pstmt=con.prepareStatement(sql);
//		pstmt.setString(1, schduleId);
//		ResultSet re=pstmt.executeQuery();
//		if(re.next()){
//			return re.getInt("total");
//		}else{
//			return 0;
//		}
//	}
	
	public String movieIdList(Connection con)throws Exception{
		String movieId="";
		String sql="select movieId from t_movie";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet re=pstmt.executeQuery();
		while(re.next()){
			movieId+=re.getInt("movieId");
			movieId+=",";
		}
		movieId=movieId.substring(0, movieId.length()-1);
		return movieId;
	}
	
	public String movieCount(Connection con,String movieIds)throws Exception{
		String[] ids=movieIds.split(",");
		String movieCount="";
		//movieCount+="[";
		for(int i=0;i<ids.length;i++){
			String sql="select count(*) as total from t_schedule t1,t_seat t2 where t1.scheduleId=t2.scheduleId and movieId=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, ids[i]);
			ResultSet re=pstmt.executeQuery();
			if(re.next()){
				movieCount+=re.getInt("total");
				movieCount+=",";
			}else{
				movieCount+=0;
				movieCount+=",";
			}
		}
		movieCount=movieCount.substring(0, movieCount.length()-1);
		//movieCount+="]";
//		System.out.println(movieCount);
		return movieCount;
	}
	
	public List<Order> buyListFG(Connection con,int customerId,PageBean pageBean)throws Exception{
		List<Order> customerBuyList=new ArrayList<Order>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from t_order o,t_customer c,t_hall h,t_class cl,t_schedule s,t_movie m,t_seat seat where h.hallId=s.hallId and o.customerId=c.customerId and s.movieId=m.movieId and c.classId=cl.classId and o.scheduleId=s.scheduleId and s.scheduleId=seat.scheduleId and o.seatId=seat.seatId and seat.customerId=c.customerId and o.customerId=?");
		if(pageBean!=null){
			sql.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sql.toString());
		pstmt.setInt(1, customerId);
		ResultSet re=pstmt.executeQuery();
		while(re.next()){
			Order order=new Order();
			order.setCustomerName(re.getString("customerName"));
			order.setClassName(re.getString("className"));
			order.setMovieName(re.getString("movieName"));
			order.setOrderAdjustedPrice(re.getFloat("orderAdjustedPrice"));
			order.setOrderBuyDate(re.getDate("orderBuyDate"));
			order.setHallName(re.getString("hallName"));
			order.setSeatRow(re.getInt("seatRow"));
			order.setSeatColumn(re.getInt("seatColumn"));
			customerBuyList.add(order);
		}
		return customerBuyList;
	}
	
	public int buyListFGCount(Connection con,int customerId)throws Exception{
		String sql="select count(*) as total from t_order o,t_customer c,t_hall h,t_class cl,t_schedule s,t_movie m,t_seat seat where h.hallId=s.hallId and o.customerId=c.customerId and s.movieId=m.movieId and c.classId=cl.classId and o.scheduleId=s.scheduleId and s.scheduleId=seat.scheduleId and o.customerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("total");
		}else{
			return 0;
		}
	}

	public float saleMoney(Connection con)throws Exception{
		float totalMoney=0;
		String sql="select * from t_order o,t_customer c,t_hall h,t_class cl,t_schedule s,t_movie m,t_seat seat where h.hallId=s.hallId and o.customerId=c.customerId and s.movieId=m.movieId and c.classId=cl.classId and o.scheduleId=s.scheduleId and s.scheduleId=seat.scheduleId";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet re=pstmt.executeQuery();
		while(re.next()){
			totalMoney+=re.getFloat("orderAdjustedPrice");
		}
		return totalMoney;
	}
}
