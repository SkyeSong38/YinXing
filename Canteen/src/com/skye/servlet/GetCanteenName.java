package com.skye.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skye.jdbc.FoodTools;

public class GetCanteenName extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String school = new String(req.getParameter("school").getBytes("iso-8859-1"),"UTF-8");
			PrintWriter pw = resp.getWriter();
			pw.write(FoodTools.getCanteenName(school));
			pw.close();
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
