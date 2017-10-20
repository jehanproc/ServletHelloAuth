package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

//@WebServlet({"/authentification/login","/authentification/validation",})
@SuppressWarnings("serial")
public class AuthentificationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		//reverse login
		String revlogin = new StringBuffer(login).reverse().toString();
		// check pass
		if(revlogin.equals(password)){
			req.setAttribute("nom", login);
			//req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
			// mieux on appel une URL qui reprend dynamiquement le contexte applicatif
			resp.sendRedirect(getServletContext().getContextPath() + "/welcome?nom="+login);
			
		}
		else {
			req.setAttribute("erreur", "Echec dans l'authentification (combinaison log/pass incorrecte ");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}
		
		// ou en une ligne
		//	if(new StringBuffer(login).reverse().toString().equals(password))
		
				
	}

}
