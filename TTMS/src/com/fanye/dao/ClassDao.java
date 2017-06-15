package com.fanye.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fanye.model.Classs;
import com.fanye.model.PageBean;
import com.fanye.util.StringUtil;

public class ClassDao {

	public ResultSet classList(Connection con,PageBean pageBean,Classs cla)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_class ");
		if(StringUtil.isNotEmpty(cla.getClassName())){
			sb.append(" and className like '%"+cla.getClassName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int classCount(Connection con,Classs cla)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_class ");
		if(StringUtil.isNotEmpty(cla.getClassName())){
			sb.append(" and className like '%"+cla.getClassName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet re=pstmt.executeQuery();
		if(re.next()){
			return re.getInt("total");
		}else{
			return 0;
		}
	}
}
