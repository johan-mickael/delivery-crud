package ramorasata;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListeServlet
 */
public class ListeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Integer numero = 1;
			Integer limit = 10;
			if(request.getParameter("numero") != null) numero = new Integer(request.getParameter("numero"));
//			Liste[] liste = Liste.findAll();
			Object[][] object = Liste.pagination(numero, limit);
			Liste[] liste = (Liste[]) object[1];
			Integer nombre = (Integer) object[0][0] ;
			request.setAttribute("nombre", nombre);
			request.setAttribute("liste", liste);
			RequestDispatcher dispat = request.getRequestDispatcher("/views/ListeLivreur.jsp");
			dispat.forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
