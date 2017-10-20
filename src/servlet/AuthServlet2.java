package servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet({"/authentification/login", "/authentification/validation" })
//commenté cette anotation car on veux utiliser l'init du  web.xml 
@SuppressWarnings("serial")
public class AuthServlet2 extends HttpServlet {
	
	Properties users = new Properties();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String newUrl = null;
		
		switch (req.getServletPath()) {
		case "/authentification/login":
			newUrl = "/WEB-INF/jsp/login.jsp";break;

		case "/authentification/validation":
			String login = req.getParameter("login");
			String password = req.getParameter("password");
			/* if(new StringBuffer(login).reverse().toString().equals(password)) {
			 * ancien controle d'acces dans le fichier properties
			 */
			String value = users.getProperty(login);
			if(value != null && value.equals(password)){
				req.getSession().setAttribute("nom", login);
				Date dateConnexion = new Date();
				req.getSession().setAttribute("dateConnexion", dateConnexion);
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
				//req.getSession().setAttribute("dateConnexion", sdf.format(dateConnexion));
				System.out.println(sdf.format(dateConnexion));
				//req.setAttribute("nom", login); //scopé dans la requete
				//req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
				resp.sendRedirect(getServletContext().getContextPath() + "/welcome?nom="+login);
				return;
			}
			else {
				req.setAttribute("erreur", "Echec dans l'authentification (combinaison login/password incorrecte ...)");
				newUrl = "/WEB-INF/jsp/login.jsp";
			}
			break;
		/*	default : 
				newUrl = "/WEB-INF/jsp/login.jsp";break;*/
			
		}
		req.getRequestDispatcher(newUrl).forward(req, resp);
		
		
	}
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletConfig config = getServletConfig();
		String relativePathFile = config.getInitParameter("userFile");
		String realPathFile = getServletContext().getRealPath(relativePathFile);
		System.out.println("relativePAthFile=" + relativePathFile);
		System.out.println("realPathFile=" + realPathFile);
		//Properties users = new Properties(); //quand on sort de la porté de l'init() cette object users disparait
		try {
			InputStream input = new FileInputStream(realPathFile);
			users.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// cette exception es tincluse dans le suivante, on pourrait la virer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
