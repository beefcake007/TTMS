package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fanye.model.Order;

public class SeatDao {

	public boolean checkList(Connection con,int scheduleId)throws Exception{
		String sql="select * from t_seat where scheduleId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, scheduleId);
		ResultSet re=pstmt.executeQuery();
		return re.next();
	}
	
	public String seatsList(Connection con,int scheduleId)throws Exception {
		String sql="select * from t_seat where scheduleId=?";
		String seat="";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, scheduleId);
		ResultSet re=pstmt.executeQuery();
		seat+="[";
		while(re.next()){
			seat+="'";
			seat+=re.getInt("seatRow");
			seat+="_";
			seat+=re.getInt("seatColumn");
			seat+="\',";
		}
		seat=seat.substring(0, seat.length()-1);
		seat+="]";
		return seat;
	}
	
	public int seatAdd(Connection con,int customerId,int scheduleId,int row,int column)throws Exception {
		String sql="insert into t_seat values(null,?,?,?,?,1)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		pstmt.setInt(2, scheduleId);
		pstmt.setInt(3, row);
		pstmt.setInt(4, column);
		return pstmt.executeUpdate();
	}
	
	public int backSeatId(Connection con,int customerId,int scheduleId,int row,int column)throws Exception{
		String sql="select * from t_seat where customerId=? and scheduleId=? and seatRow=? and seatColumn=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		pstmt.setInt(2, scheduleId);
		pstmt.setInt(3, row);
		pstmt.setInt(4, column);
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("seatId");
		}else{
			return 0;
		}
	}
	
	public boolean checkSeat(Connection con,int scheduleId,int row,int column)throws Exception{
		String sql="select * from t_seat where scheduleId=? and seatRow=? and seatColumn=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, scheduleId);
		pstmt.setInt(2, row);
		pstmt.setInt(3, column);
		ResultSet re=pstmt.executeQuery();
		return re.next();
	}
	
	public int orderAdd(Connection con,Order order)throws Exception{
		String sql="insert into t_order values(null,?,?,?,?,now())";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, order.getSeatId());
		pstmt.setInt(2, order.getCustomerId());
		pstmt.setInt(3,order.getScheduleId());
		pstmt.setFloat(4, order.getOrderAdjustedPrice());
		return pstmt.executeUpdate();
	}
}
