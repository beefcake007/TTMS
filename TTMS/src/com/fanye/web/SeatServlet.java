package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.fanye.dao.ScheduleDao;
import com.fanye.dao.SeatDao;
import com.fanye.model.Order;
import com.fanye.model.Schedule;
import com.fanye.util.DateUtil;
import com.fanye.util.DbUtil;
import com.fanye.util.ResponseUtil;

public class SeatServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	ScheduleDao scheduleDao=new ScheduleDao();
	SeatDao seatDao=new SeatDao();

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
		if("sale".equals(action)){
			this.saleFGAction(request,response);
		}else if("buy".equals(action)){
			this.buyAction(request,response);
		}else if("ticketSale".equals(action)){
			this.ticketSaleAction(request,response);
		}
	}	
	
	private void saleFGAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String movieId=request.getParameter("movieId");
		String hallId=request.getParameter("hallId");
		String scheduleBeginDate=request.getParameter("scheduleBeginDate");
		String scheduleId=request.getParameter("scheduleId");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			Schedule saleList=scheduleDao.saleFGList(con, Integer.parseInt(movieId), Integer.parseInt(hallId), DateUtil.convertToSqlDate(scheduleBeginDate));
			if(seatDao.checkList(con, Integer.parseInt(scheduleId))){
				String seat=seatDao.seatsList(con, Integer.parseInt(scheduleId));
				request.setAttribute("seat", seat);
			}else{
				String seat="[]";
				request.setAttribute("seat", seat);
			}
			request.setAttribute("saleList", saleList);
			request.getRequestDispatcher("/foreground/movie/sale.jsp").forward(request, response);
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
	
	
	private void buyAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String scheduleId=request.getParameter("scheduleId");
		String seatBuy=request.getParameter("seatBuy");
		String price=request.getParameter("price");
		String customerId=request.getParameter("customerId");
//		System.out.println(scheduleId);
//		System.out.println(seatBuy);
		Connection con=null;
		Order order=new Order();
		order.setScheduleId(Integer.parseInt(scheduleId));
		order.setOrderAdjustedPrice(Float.parseFloat(price));
		order.setCustomerId(Integer.parseInt(customerId));
		
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int saveNum=0;
			String[] seatArr=seatBuy.split(" ");
			for(int i=0;i<seatArr.length;i++){
				String[] ob=seatArr[i].split("_");
				int row=Integer.parseInt(ob[0]);
				int column=Integer.parseInt(ob[1]);
				if(seatDao.checkSeat(con, Integer.parseInt(scheduleId), row, column)){
					result.put("errorMsg", "此票已被购买，请重新选座！");
				}else{
					saveNum+=seatDao.seatAdd(con,Integer.parseInt(customerId) ,Integer.parseInt(scheduleId), row, column);
					int seatId=seatDao.backSeatId(con, Integer.parseInt(customerId) ,Integer.parseInt(scheduleId), row, column);
					order.setSeatId(seatId);
					seatDao.orderAdd(con, order);
					result.put("success", true);
				}
			}
//			String seat=seatDao.seatsList(con, Integer.parseInt(scheduleId));
//			request.setAttribute("seat", seat);
//			request.getRequestDispatcher("/foreground/movie/movieSale.jsp").forward(request, response);
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
	
	private void ticketSaleAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String scheduleId=request.getParameter("scheduleId");
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject jsonObject=scheduleDao.ticketSale(con, Integer.parseInt(scheduleId));
			JSONObject result=new JSONObject();
			String seat=null;
			if(seatDao.checkList(con, Integer.parseInt(scheduleId))){
				seat=seatDao.seatsList(con, Integer.parseInt(scheduleId));
			}else{
				seat="[]";
			}
			result.put("ticket", jsonObject);
			result.put("seat", seat);
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
