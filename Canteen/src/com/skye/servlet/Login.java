package com.skye.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skye.jdbc.UserTools;

public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				req.getInputStream()));
		StringBuilder sb = new StringBuilder();

		String s = null;
		while ((s = reader.readLine()) != null) {
			sb.append(s);
		}

		boolean backInfo = UserTools.login(sb.toString());
		
		PrintWriter pw = resp.getWriter();
//		pw.write();
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
