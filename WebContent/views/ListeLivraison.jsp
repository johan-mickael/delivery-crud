<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "rakotoniaina.VLivraison" %>
<% 	VLivraison[] livraisons = (VLivraison[]) request.getAttribute("livraisons");
	Integer nombre = (Integer) request.getAttribute("nombre");
	HttpSession session2 = request.getSession();
   	Integer idSession = null;
   	if(session2.getAttribute("idSession") != null) idSession = (Integer) session2.getAttribute("idSession");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/all.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/util.css">
<link rel="stylesheet" type="text/css" href="assets/css/main.css">
<title>Liste des livraisons</title>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs mb-4">
	  	<li class="nav-item">
	    	<a class="nav-link active" href="/gestionlivraison/LivraisonServlet.html">
	    		Livraison
	    	</a>
	  	</li>
	 	 <li class="nav-item">
	    	<a class="nav-link" href="/gestionlivraison/ListeServlet.html">Livreur</a>
	  	</li>
	  	<% if (idSession != null){ %>
	  	<li class="nav-item">
	    	<a class="nav-link" href="/gestionlivraison/LoginServlet.html?action=logout">Se deconnecter</a>
	  	</li>
	  	<% } else { %>
	  	<li class="nav-item">
	    	<a class="nav-link" href="/gestionlivraison/LoginServlet.html">Se connecter</a>
	  	</li>
	  	<% } %>
	</ul>
	<center>
	<table>
	<tr>
		<% for(int indice = 1; indice <= nombre; indice++) { %>
			<td width="80"><a href="LivraisonServlet.html?numero=<%= indice %>"><%= indice %></a></td>
		<% } %>
	</tr>
	</table></center>
<table border="0" class="table table-hover" border="1">
	<thead class="thead-dark">
	<tr>
		<th>#</th>
		<th>AXE</th>
		<th>FRAIS</th>
		<th>ETAT</th>
		<th>LIVREUR</th>
		<th>DATE</th>
		<th>PRODUIT</th>
		<th colspan="2">
			<% if (idSession != null){ %>
			<a class="float-right" href="/gestionlivraison/LivraisonServlet.html?action=GENERERFORMULAIRE">
				<button class="btn btn-success"><i class="fas fa-plus"></i> Ajouter</button>
			</a>
			<% } %>
		</th>
	</tr>
	</thead>
	<tbody>
<% for(int i=0; i<livraisons.length; i++){ %>
<tr>
	<td class="text-right"><%= i+1 %></td>
	<td class="text-left"><%= livraisons[i].getNomAxe() %></td>
	<td class="text-right"><%= livraisons[i].getValeur() %></td>
	<td class="text-left"><%= livraisons[i].getEtat() %></td>
	<td class="text-left"><%= livraisons[i].getNomLivreur()+" "+livraisons[i].getPrenom() %></td>
	<td class="text-center"><%= livraisons[i].getDateLivraison() %></td>
	<td class="text-left"><%= livraisons[i].getProduit() %></td>
	<% if (idSession != null){ %>
		<td class="text-center text-warning"><a class="pagination" href="/gestionlivraison/LivraisonServlet.html?idLivraison=<%= livraisons[i].getId() %>&&action=MODIFIERLIVRAISON">
			<i class="fas fa-edit"></i>
		</a></td>
		<td class="text-center text-danger">
			<form id="form" action="/gestionlivraison/LivraisonServlet.html" method="post">
			    <input type="hidden" name="idLivraison" value="<%= livraisons[i].getId() %>"/>
			    <input type="hidden" name="action" value="SUPPRIMERLIVRAISON"/>
			    <a href="javascript:;" onclick="document.getElementById('form').submit();">
			    	<i class="fas fa-trash"></i>
			    </a>
			</form>		
		</td>
		<% } %>
</tr>
<% } %>
</tbody>
</table>
</div>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/fontawesome.min.js"></script>
<script src="assets/js/all.min.js"></script>
</body>
</html>