package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fanye.model.Hall;
import com.fanye.model.PageBean;
import com.fanye.util.StringUtil;

public class HallDao {

	public ResultSet hallList(Connection con,PageBean pageBean,Hall hall)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_hall ");
		if(StringUtil.isNotEmpty(hall.getHallName())){
			sb.append(" and hallName like '%"+hall.getHallName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int hallCount(Connection con,Hall hall)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_hall ");
		if(StringUtil.isNotEmpty(hall.getHallName())){
			sb.append(" and hallName like '%"+hall.getHallName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int hallAdd(Connection con,Hall hall)throws Exception{
		String sql="insert into t_hall values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, hall.getHallName());
		pstmt.setInt(2, hall.getHallRow());
		pstmt.setInt(3, hall.getHallColumn());
		pstmt.setString(4, hall.getHallDescription());
		return pstmt.executeUpdate();
	}
	
	public int hallUpdate(Connection con,Hall hall)throws Exception{
		String sql="update t_hall set hallName=? ,hallRow=? ,hallColumn=? ,hallDescription=? where hallId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, hall.getHallName());
		pstmt.setInt(2, hall.getHallRow());
		pstmt.setInt(3, hall.getHallColumn());
		pstmt.setString(4, hall.getHallDescription());
		pstmt.setInt(5, hall.getHallId());
		return pstmt.executeUpdate();
	}
	
	public int hallDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_hall where hallId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
