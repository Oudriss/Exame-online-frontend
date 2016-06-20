package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import bm.CandidatureBM;
import utils.Properties;

/**
 * Servlet implementation class UtilsServlet
 */
@WebServlet("/UtilsServlet")
public class UtilsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidatureBM candidatureBM;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UtilsServlet() {
        super();
        try {
			candidatureBM = new CandidatureBM();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("Utilisateur");
		
		String code = request.getParameter("code");
		String[] date = request.getParameter("date").split("/");
		try {
			candidatureBM.exportPdf(user.getCin(),Integer.parseInt(code),date[0],date[1]);
			File fichier = new File( Properties.PDF_DESTINATION+"/"+code+".pdf");
			String type = getServletContext().getMimeType( fichier.getName() );
			response.reset();
			response.setBufferSize( 10240 );
			response.setContentType( type );
			response.setHeader( "Content-Length", String.valueOf( fichier.length() ) );
			response.setHeader( "Content-Disposition", "attachment; filename=\"" +fichier.getName() + "\"" );
			/* Prépare les flux */
			BufferedInputStream entree = null;
			BufferedOutputStream sortie = null;
			    /* Ouvre les flux */
			entree = new BufferedInputStream( new FileInputStream( fichier ), 1024 );
			sortie = new BufferedOutputStream( response.getOutputStream(), 1024 );
		
			byte[] tampon = new byte[1024];
			int longueur;
			while ( ( longueur= entree.read( tampon ) ) > 0 ) {
			    sortie.write( tampon, 0, longueur );
			}
			
		    sortie.close();
		    entree.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
