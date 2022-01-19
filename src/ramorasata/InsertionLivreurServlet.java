package ramorasata;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertionLivreur
 */
public class InsertionLivreurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertionLivreurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ServletContext context = this.getServletContext();
			Object[] tab = (Object[]) context.getAttribute("axe");
			request.setAttribute("axe", tab);
			RequestDispatcher dispat = request.getRequestDispatcher("/views/InsertionLivreur.jsp");
			dispat.forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ServletContext context = this.getServletContext();
			Object[] tab = (Object[]) context.getAttribute("axe");
			request.setAttribute("axe", tab);
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher dispat = request.getRequestDispatcher("/views/InsertionLivreur.jsp");
			dispat.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Livreur livreur = new Livreur(request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("idAxe"), request.getParameter("dateEntree"));
			livreur.saveLivreur();
			response.sendRedirect("/gestionlivraison/ListeServlet.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ServletContext context = this.getServletContext();
			Object[] tab = (Object[]) context.getAttribute("axe");
			request.setAttribute("axe", tab);
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher dispat = request.getRequestDispatcher("/views/InsertionLivreur.jsp");
			dispat.forward(request,response);
		}
	}

}
