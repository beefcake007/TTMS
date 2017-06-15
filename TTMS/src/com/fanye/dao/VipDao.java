package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.json.JSONArray;

import com.fanye.model.Customer;
import com.fanye.model.PageBean;
import com.fanye.util.StringUtil;

public class VipDao {
	
	public Customer login(Connection con,Customer customer)throws Exception{
		Customer currentUser=null;
		String sql="select * from t_customer where customerName=? and customerPassWord=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getCustomerPassWord());
		ResultSet re=pstmt.executeQuery();
		while(re.next()){
			currentUser = new Customer();
			currentUser.setCustomerId(re.getInt("customerId"));
			currentUser.setCustomerName(re.getString("customerName"));
			currentUser.setCustomerPassWord(re.getString("customerPassWord"));
		}
		return currentUser;
	}
	
	public boolean checkName(Connection con,String userName)throws Exception{
		String sql="select * from t_customer where customerName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, userName);
		ResultSet re=pstmt.executeQuery();
		return re.next();
	}
	
	public int vipAdd(Connection con,Customer customer)throws Exception{
		String sql="insert into t_customer values(null,?,?,?,?,1)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getCustomerPassWord());
		pstmt.setString(3, customer.getCustomerEmail());
		pstmt.setString(4, customer.getCustomerMobile());
		return pstmt.executeUpdate();
	}
	
	public ResultSet vipList(Connection con,PageBean pageBean,Customer customer)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_customer c,t_class u where c.classId=u.classId ");
		if(StringUtil.isNotEmpty(customer.getCustomerName())){
			sb.append(" and c.customerName like '%"+customer.getCustomerName()+"%'");
		}
		if(customer.getClassId()!=-1){
			sb.append(" and c.classId="+customer.getClassId());
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	public int vipCount(Connection con,Customer customer)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_customer c,t_class u where c.classId=u.classId ");
		if(StringUtil.isNotEmpty(customer.getCustomerName())){
			sb.append(" and c.customerName like '%"+customer.getCustomerName()+"%'");
		}
		if(customer.getClassId()!=-1){
			sb.append(" and c.classId="+customer.getClassId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("total");
		}else{
			return 0;
		}
	}
	
	public int vipUpdate(Connection con,Customer customer)throws Exception{
		String sql="update t_customer set customerName=?,customerPassWord=?,customerEmail=?,customerMobile=?,classId=? where customerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getCustomerPassWord());
		pstmt.setString(3, customer.getCustomerEmail());
		pstmt.setString(4, customer.getCustomerMobile());
		pstmt.setInt(5, customer.getClassId());
		pstmt.setInt(6, customer.getCustomerId());
		return pstmt.executeUpdate();
	}
	
	public int vipSuperAdd(Connection con,Customer customer)throws Exception{
		String sql="insert into t_customer values(null,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getCustomerPassWord());
		pstmt.setString(3, customer.getCustomerEmail());
		pstmt.setString(4, customer.getCustomerMobile());
		pstmt.setInt(5, customer.getClassId());
		return pstmt.executeUpdate();
	}
	
	public int vipDelete(Connection con,String delIds)throws Exception{
		String sql="delete from t_customer where customerId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
