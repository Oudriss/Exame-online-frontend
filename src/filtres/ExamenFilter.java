package filtres;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ExamenTimer;

/**
 * Servlet Filter implementation class ExamenFilter
 */
@WebFilter("/ExamenFilter")
public class ExamenFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ExamenFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		ExamenTimer.END_TIME = System.currentTimeMillis();
		System.out.println((ExamenTimer.END_TIME - ExamenTimer.START_TIME) + "Timing");

		if( (session.getAttribute("Utilisateur") != null) && (req.getParameter("action").equals("Valider les r�ponses")) 
				&& ((ExamenTimer.END_TIME - ExamenTimer.START_TIME) >= 7200000)  ){
			System.out.println((ExamenTimer.END_TIME - ExamenTimer.START_TIME) + "Timing ==");
			req.setAttribute("Erreur", "Vous avez depass� la dur�e d'examen, vos r�ponses !!");
            rep.sendRedirect( req.getContextPath() + "/VIEWS/accueil.jsp"  );
		}else chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
