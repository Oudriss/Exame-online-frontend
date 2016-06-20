package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import beans.Candidature;
import beans.Utilisateur;
import bm.CandidatureBM;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/AccueilServlet")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private CandidatureBM candidatureBM;
	private HttpSession session;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        try {
			candidatureBM = new CandidatureBM();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("Utilisateur");
		String action = request.getParameter("action");
		if( action == null){
			dispatcher = request.getRequestDispatcher("/VIEWS/accueil.jsp");
			dispatcher.include(request,response);
		}else if( action.equals("consultation")){
			dispatcher = request.getRequestDispatcher("/VIEWS/examens.jsp");
			request.setAttribute("candidatures", candidatureBM.getNouvellesCandidatures(user.getCin()));
			dispatcher.include(request,response);
		}else  if( action.equals("accueil")){
			dispatcher = request.getRequestDispatcher("/VIEWS/accueil.jsp");
			dispatcher.include(request,response);
		}else if( action.equals("historique")){
			ArrayList<Candidature> cands = candidatureBM.getAncienCandidatures(user.getCin());
			ArrayList<Float> notes = new ArrayList<Float>();
			for( Candidature cand: cands ){
				notes.add(candidatureBM.calculerNote(cand));
			}
			dispatcher = request.getRequestDispatcher("/VIEWS/historique.jsp");
			request.setAttribute("candidatures", cands);
			request.setAttribute("notes", notes);
			dispatcher.include(request,response);
		}else{
			dispatcher = request.getRequestDispatcher("/VIEWS/accueil.jsp");
			dispatcher.include(request,response);
		}
	}

}
