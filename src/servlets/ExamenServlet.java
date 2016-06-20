package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import beans.Candidature;
import beans.Examen;
import beans.Utilisateur;
import bm.CandidatureBM;
import utils.ExamenTimer;

/**
 * Servlet implementation class ExamenServlet
 */
@WebServlet("/ExamenServlet")
public class ExamenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidatureBM candidatureBM;
	private HttpSession session;
	private RequestDispatcher dispatcher;
	private Candidature candidature;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamenServlet() {
        super();
        try {
			candidatureBM = new CandidatureBM();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
		
		if( action.equals("Passer l'examen")){
			String code = request.getParameter("code");
			String[] date = request.getParameter("date").split("/");
			candidature = candidatureBM.getCandidatureByCode(user.getCin(),Integer.parseInt(code),date[0],date[1]);
			Examen examen = candidature.getExamen();
			if( examen != null){
				dispatcher = request.getRequestDispatcher("/VIEWS/examen.jsp");
				request.setAttribute("examen", examen);
				ExamenTimer.START_TIME = System.currentTimeMillis();
				System.out.println("============= START TIME :");
				dispatcher.include(request,response);
			}
		}else if( action.equals("Valider les réponses")){
			candidature.setUtilisateur(user);
			Examen examen = candidature.getExamen();
			HashMap<Integer,String> reponses = new HashMap<Integer,String>();
			for( int i = 0 ; i < examen.getQuestions().size() ; i++){
				reponses.put(Integer.valueOf(examen.getQuestions().get(i).getCode()), request.getParameter(""+examen.getQuestions().get(i).getCode()));
				System.out.println(reponses.get(Integer.valueOf(examen.getQuestions().get(i).getCode()))+"***");
			}
			candidatureBM.saisirReponses(candidature,reponses);
			request.setAttribute("msg", "Vous avez passé l'examen avec succée, verifier l'historique pour consulter la note");
			dispatcher = request.getRequestDispatcher("/VIEWS/accueil.jsp");
			dispatcher.forward(request,response);
		}
		
	}

}
