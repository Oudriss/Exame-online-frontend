package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import beans.Utilisateur;
import bm.AuthentificationBM;

/**
 * Servlet implementation class AuthentificationServlet
 */
@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuthentificationBM authentification;
	private RequestDispatcher dispatcher;
	private HttpSession session;
    /**
     * Default constructor. 
     */
    public AuthentificationServlet() {
        // TODO Auto-generated constructor stub
    	try {
			authentification = new AuthentificationBM();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Le cas de demande de connexion
		if( request.getParameter("action").equals("Connecter") ){
			Utilisateur user;
			String password = request.getParameter("pass");
			String login = request.getParameter("user");
			if( (user = authentification.authentifier(login, password)) != null){
				System.out.println("ok ok ok");
				session = request.getSession();
				session.setAttribute("Utilisateur", user);
				dispatcher = request.getRequestDispatcher("/VIEWS/accueil.jsp");
				dispatcher.include(request,response);
			}else{
				dispatcher = request.getRequestDispatcher("authentification.jsp");
				request.setAttribute("erreur", "Le mot de passe ou login n'est pas correct");
				//dispatcher.include(request,response);
				dispatcher.forward(request, response);
				System.out.println("Erreur d'authentification");
			}
		//Le cas de deconnexion
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Le cas de demande de connexion
		if( request.getParameter("action").equals("deconnexion") ){
			session.invalidate();
			dispatcher = request.getRequestDispatcher("/authentification.jsp");
			dispatcher.include(request,response);
		}
	}

}
