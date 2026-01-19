package in.sp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.dbcon.DbConnection;
import in.sp.modal.User;

@WebServlet("/loginForm")
public class Login extends HttpServlet{

			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				PrintWriter out = resp.getWriter(); //PrintWriter is used to write response data back to the client.
				resp.setContentType("text/html");//setContentType(“text/html”) informs the browser that the response content is HTML
				//so it can render it correctly.
				String myemail = req.getParameter("email"); 
				String mypass = req.getParameter("pass1");
				try
				{
					Connection con= DbConnection.getConnection();
					String select_sql_query = "SELECT * FROM register WHERE email=? AND password=?";
					PreparedStatement ps= con.prepareStatement(select_sql_query); 
					ps.setString(1, myemail);
					ps.setString(2, mypass);
					ResultSet rs= ps.executeQuery();
					if(rs.next()) {
						User user = new User(); 
						user.setName(rs.getString("name"));
						user.setEmail(rs.getString("email")); 
						user.setCity(rs.getString("city"));
						
						HttpSession session= req.getSession(); 
						session.setAttribute("session_user",user);
						RequestDispatcher rd= req.getRequestDispatcher("/profile.jsp");
						rd.forward(req, resp);
					}
					else
					{
						
						RequestDispatcher rd= req.getRequestDispatcher("/login.html");
						rd.include(req, resp);
						out.println(" <h3 style='color:red' Email id and password did not match </h3>");
					}
				
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

