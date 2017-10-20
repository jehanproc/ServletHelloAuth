package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;


//@WebServlet("/welcome")
//commenté car cette anotation definit à l'ancienne dans web.xml 

@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//on recupere le nom de l'utilisateur
		String nom = req.getParameter("nom");
		//simulation d'un traitement metier, passer le nom en majuscule
		nom = nom.toUpperCase();
		//ce resultat metier il faut le mettre dans le modele pour qu'une vue puisse l'atteindre
		// le modele se materialise par un scope => requete, session, application . En majorité des requetes
		// zone de partage entre servlet et jsp dans un scope
		req.setAttribute("nom", nom); // son nom et sa valeur (map/collection)
		//il faut declancher la vue => ressource html
		req.getRequestDispatcher("/WEB-INF/jsp/helloAuth.jsp").forward(req,resp);
		
		
	}
	

}
