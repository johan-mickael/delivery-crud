package ramorasata;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifierLivreur
 */
public class ModifierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
			try {
				Livreur livreur = Livreur.findModif(id);
				request.setAttribute("livreur", livreur);
				ServletContext context = this.getServletContext();
				Axe[] axe = (Axe[]) context.getAttribute("axe");
				request.setAttribute("axe", axe);
				RequestDispatcher dispat = request.getRequestDispatcher("/views/ModifierLivreur.jsp");
				dispat.forward(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ServletContext context = this.getServletContext();
				Axe[] axe = (Axe[]) context.getAttribute("axe");
				request.setAttribute("axe", axe);
				request.setAttribute("erreur", e.getMessage());
				RequestDispatcher dispat = request.getRequestDispatcher("/views/ModifierLivreur.jsp");
				dispat.forward(request,response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("idLivreur");
		if(request.getParameter("action")!=null) {
			try {
				Livreur.deleteLivreur(id);
				response.sendRedirect("/gestionlivraison/ListeServlet.html");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Livreur livreur = new Livreur(request.getParameter("idLivreur"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("idAxe"), request.getParameter("dateEntree"));
				Livreur.updateLivreur(livreur);
				response.sendRedirect("/gestionlivraison/ListeServlet");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				try {
					Livreur livreur = Livreur.findModif(id);
					request.setAttribute("livreur", livreur);
					ServletContext context = this.getServletContext();
					Axe[] axe = (Axe[]) context.getAttribute("axe");
					request.setAttribute("axe", axe);
					request.setAttribute("erreur",e.getMessage());
					RequestDispatcher dispat = request.getRequestDispatcher("/views/ModifierLivreur.jsp");
					dispat.forward(request,response);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					request.setAttribute("erreur",e1);
					RequestDispatcher dispat = request.getRequestDispatcher("/views/Erreur.jsp");
					dispat.forward(request,response);
				}
			}
		}
	}

}
