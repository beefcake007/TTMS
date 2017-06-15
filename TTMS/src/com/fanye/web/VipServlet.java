package com.fanye.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fanye.dao.VipDao;
import com.fanye.model.Customer;
import com.fanye.model.PageBean;
import com.fanye.util.DbUtil;
import com.fanye.util.JsonUtil;
import com.fanye.util.ResponseUtil;
import com.fanye.util.StringUtil;

public class VipServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	VipDao vipDao=new VipDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		
		if("login".equals(action)){
			this.loginAction(request,response);
		}else if("check".equals(action)){
			this.checkAction(request,response);
		}else if("register".equals(action)){
			this.registerAction(request,response);
		}else if("list".equals(action)){
			this.listAction(request,response);
		}else if("save".equals(action)){
			this.saveAction(request,response);
		}else if("delete".equals(action)){
			this.deleteAction(request,response);
		}
		
	}

	private void loginAction(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		HttpSession session=request.getSession();
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		String remember=request.getParameter("remember");
		
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(passWord)){
			request.setAttribute("error", "用户名或密码错误！");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		Customer customer=new Customer(userName,passWord);
		Connection con=null;
		
		try{
			con=dbUtil.getCon();
			Customer currentUser=vipDao.login(con, customer);
			if(currentUser==null){
				request.setAttribute("error", "用户名或密码错误！");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				session.setAttribute("currentUser", currentUser);
				request.getRequestDispatcher("movie?action=listFG").forward(request, response);
			}
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
	
	
	private void checkAction(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		String userName=request.getParameter("userName");
		Connection con=null;
		try{
			JSONObject result=new JSONObject();
			boolean isCheck=false;
			con=dbUtil.getCon();
			isCheck=vipDao.checkName(con, userName);
			if(isCheck){
				result.put("success", "true");
				result.put("exist", "用户名已存在！");
			}else{
				result.put("success", "false");
				result.put("exist", "用户名可用！");
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
	
	private void registerAction(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		String userName=request.getParameter("userName");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String passWord=request.getParameter("passWord");
		Customer customer=new Customer(userName,passWord,email,mobile);
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(passWord)||StringUtil.isEmpty(email)||StringUtil.isEmpty(mobile)){
			request.setAttribute("error", "信息不能为空！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=0;
			addNum=vipDao.vipAdd(con, customer);
			if(addNum!=0){
				System.out.println("注册成功");
				response.sendRedirect("index.jsp");
			}else{
				System.out.println("注册失败");
			}
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
	
	
	private void listAction(HttpServletRequest request,HttpServletResponse response)throws 
		ServletException, IOException{
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		
		Customer customer =new Customer();
		
		String s_customerName=request.getParameter("s_customerName");
		String s_classId=request.getParameter("s_classId");
		if(StringUtil.isNotEmpty(s_customerName)){
			customer.setCustomerName(s_customerName);
		}
		if(StringUtil.isNotEmpty(s_classId)){
			customer.setClassId(Integer.parseInt(s_classId));
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(vipDao.vipList(con, pageBean, customer));
			int total=vipDao.vipCount(con, customer);
			result.put("total", total);
			result.put("rows", jsonArray);
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
	
	private void saveAction(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		String customerId=request.getParameter("customerId");
		String customerName=request.getParameter("customerName");
		String customerPassWord=request.getParameter("customerPassWord");
		String classId=request.getParameter("classId");
		String customerEmail=request.getParameter("customerEmail");
		String customerMobile=request.getParameter("customerMobile");
		
		Customer customer = new Customer(customerName,customerPassWord,customerEmail,customerMobile,Integer.parseInt(classId));
		if(StringUtil.isNotEmpty(customerId)){
			customer.setCustomerId(Integer.parseInt(customerId));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			if(StringUtil.isNotEmpty(customerId)){
				saveNums=vipDao.vipUpdate(con, customer);
			}else{
				if(vipDao.checkName(con, customerName)){
					saveNums=-1;
				}else{
					saveNums=vipDao.vipSuperAdd(con, customer);
				}
			}
			if(saveNums==-1){
				result.put("errorMsg", "此用户名已存在！");
			}else if(saveNums==0){
				result.put("errorMsg", "保存失败！");
			}else{
				result.put("success", "true");
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
	
	
	private void deleteAction(HttpServletRequest request,HttpServletResponse response)throws
		ServletException, IOException{
		String delIds=request.getParameter("delIds");
		
		Connection con=null;
		try{
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int delNums=vipDao.vipDelete(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败！");
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
