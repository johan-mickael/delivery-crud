package ramorasata;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.Helper;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("action")!=null) {
			HttpSession session = request.getSession();
			session.removeAttribute("idSession");
			response.sendRedirect("/gestionlivraison/LivraisonServlet.html");
		} else {
			RequestDispatcher dispat = request.getRequestDispatcher("/views/Login.jsp");
			dispat.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Helper.login(request.getParameter("identifiant"), request.getParameter("mdp"));
			HttpSession session = request.getSession();
			session.setAttribute("idSession", id);
			response.sendRedirect("/gestionlivraison/LivraisonServlet.html");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
			RequestDispatcher dispat = request.getRequestDispatcher("/views/Login.jsp");
			dispat.forward(request,response);
		}
	}

}
