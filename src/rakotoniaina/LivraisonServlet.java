package rakotoniaina;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ramorasata.Axe;
import ramorasata.Liste;
import ramorasata.Livreur;

/**
 * Servlet implementation class LivraisonServlet
 */
public class LivraisonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String actionListeLivraison = "LISTELIVRAISON";
	private String actionModifierLivraison = "MODIFIERLIVRAISON";
	private String actionSupprimerLivraison = "SUPPRIMERLIVRAISON";
	private String actionGenererFormulaire = "GENERERFORMULAIRE";
	private String formActionInsert = "INSERT";
	private String formActionUpdate = "UPDATE";

    public LivraisonServlet() {
    	super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null)
			action = actionListeLivraison;
		try {
			if(action.compareTo(actionGenererFormulaire) == 0)
				generateForm(request, response, formActionInsert, null);
			if(action.compareTo(actionListeLivraison) == 0)
				listeLivraison(request, response);
			if(action.compareTo(actionModifierLivraison) == 0)
				generateForm(request, response, formActionUpdate, null);
		} catch (Exception e) {
			declencherErreur(request, response, e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if(action.compareTo(actionGenererFormulaire) == 0)
				HandleLivraison(request, response, formActionInsert);
			if(action.compareTo(actionModifierLivraison) == 0)
				HandleLivraison(request, response, formActionUpdate);
			if(action.compareTo(actionSupprimerLivraison) == 0)
				removeLivraison(request, response);
			listeLivraison(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void init() throws ServletException {
    	try {
    		initialisationContext();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	protected void initialisationContext() throws Exception {
		Object[][] objects = Function.findBasicData();
		Frais[] frais = Frais.caster(objects[0]);
		Axe[] axe = Axe.caster(objects[1]);
		ServletContext context = this.getServletContext();
		context.setAttribute("frais", frais);
		context.setAttribute("axe", axe);
	}
	
	protected void generateForm(HttpServletRequest request, HttpServletResponse response, String action, Exception exception) throws Exception {
		String idLvs = request.getParameter("idLivraison");
		Livraison livraison = null;
		if(idLvs != null && action.compareTo(formActionUpdate) == 0) {
			int idLivraison = (int) new Integer(idLvs);
			livraison = Livraison.findById(idLivraison);
		}
		Object[][] dataObject = Livraison.findAll();
		Livraison[] livraisons = Livraison.caster(dataObject[0]);
		Liste[] livreurs = Liste.caster(dataObject[1]);
		request.setAttribute("livraisons", livraisons);
		request.setAttribute("livreurs", livreurs);
		request.setAttribute("livraison", livraison);
		request.setAttribute("action", action);
		request.setAttribute("exception", exception);
		RequestDispatcher dispat = request.getRequestDispatcher("/views/InsertionLivraison.jsp");
		dispat.forward(request,response);
	}
	
	protected void listeLivraison(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer numero = 1;
		Integer limit = 8; 
		if(request.getParameter("numero") != null) numero = new Integer(request.getParameter("numero"));
		Object[][] object = VLivraison.pagination(numero, limit);
		VLivraison[] livraisons = (VLivraison[]) object[1];
		Integer nombre = (Integer) object[0][0] ;
		request.setAttribute("livraisons", livraisons);
		request.setAttribute("nombre", nombre);
		RequestDispatcher dispat = request.getRequestDispatcher("/views/ListeLivraison.jsp");
		dispat.forward(request,response);
	}
	
	protected void HandleLivraison(HttpServletRequest request, HttpServletResponse response, String action) throws Exception {
		response.setContentType("text/plain");
		String id = request.getParameter("idLivraison");
		String idAxe = request.getParameter("idAxe");
		String idFrais = request.getParameter("idFrais");
		String idLivreur = request.getParameter("idLivreur");
		String etat = request.getParameter("etat");
		String dateLivraison = request.getParameter("dateLivraison");
		String produit = request.getParameter("produit");
		try {
			Livreur livreur = Livreur.findModif(idLivreur);
			if(!livreur.verifierAxe(idAxe))
				throw new Exception("axes incompatibles");
			Livraison livraison = new Livraison(id, idAxe, idFrais, etat, idLivreur, dateLivraison, produit);
			if(action.compareTo(formActionInsert) == 0)
				livraison.save();
			if(action.compareTo(formActionUpdate) == 0)
				livraison.update();
		} catch (Exception e) {
			if(action.compareTo(formActionInsert) == 0)
				generateForm(request, response, formActionInsert, e);
			if(action.compareTo(formActionUpdate) == 0)
				generateForm(request, response, formActionUpdate, e);
		}
	}
	protected void removeLivraison(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idLivraison = request.getParameter("idLivraison");
		Livraison livraison = new Livraison(idLivraison);
		livraison.delete();
	}
	
	protected void declencherErreur(HttpServletRequest request, HttpServletResponse response, Exception ex) throws ServletException, IOException {
		RequestDispatcher dispat = request.getRequestDispatcher("/views/Erreur.jsp");
		request.setAttribute("erreur", ex);
		dispat.forward(request,response);
	}
	
}
