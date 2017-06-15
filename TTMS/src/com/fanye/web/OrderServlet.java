package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fanye.dao.OrderDao;
import com.fanye.model.Order;
import com.fanye.model.PageBean;
import com.fanye.util.DbUtil;
import com.fanye.util.PageUtil;
import com.fanye.util.PropertiesUtil;
import com.fanye.util.ResponseUtil;
import com.fanye.util.StringUtil;

public class OrderServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	OrderDao orderDao=new OrderDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if("sales".equals(action)){
			this.salesAction(request,response);
		}else if("userList".equals(action)){
			this.userListAction(request,response);
		}
	}

	
	private void salesAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			float totalMoney=orderDao.saleMoney(con);
			result.put("totalMoney", totalMoney);
			String movie=orderDao.movieList(con);
			result.put("moive", movie);
			String movieIds=orderDao.movieIdList(con);
			String movieCount=orderDao.movieCount(con, movieIds);
			result.put("movieCount", movieCount);
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
	
	
	public void userListAction(HttpServletRequest request,HttpServletResponse response)
			throws ServletException , IOException{
		String customerId=request.getParameter("customerId");
		String page=request.getParameter("page");
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int total=orderDao.buyListFGCount(con, Integer.parseInt(customerId));
			List<Order> buyList=orderDao.buyListFG(con, Integer.parseInt(customerId),pageBean);
			request.setAttribute("pageCode", PageUtil.genPagation(request.getContextPath()+"/order?action=userList", Integer.parseInt(customerId),total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize"))));
			request.setAttribute("buyList", buyList);
			request.getRequestDispatcher("/foreground/user/personal.jsp").forward(request, response);
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
