<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "ramorasata.Liste" %>
<% 	Liste[] liste = (Liste[]) request.getAttribute("liste");
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
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<ul class="nav nav-tabs mb-4">
	  	<li class="nav-item">
	    	<a class="nav-link" href="/gestionlivraison/LivraisonServlet.html">
	    		Livraison
	    	</a>
	  	</li>
	 	 <li class="nav-item">
	    	<a class="nav-link active" href="/gestionlivraison/ListeServlet.html">Livreur</a>
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
	</ul><center>
	<table>
	<tr>
		<% for(int indice = 1; indice <= nombre; indice++) { %>
			<td width="80"><a href="ListeServlet.html?numero=<%= indice %>"><%= indice %></a></td>
		<% } %>
	</tr>
	</table></center>
<table border="0" class="table table-hover">
	<thead class="thead-dark">
	<tr>
		<th>#</th>
		<th>NOM</th>
		<th>PRENOM</th>
		<th>AXE</th>
		<th>Date D'ENTREE</th>
		<th colspan="2">
			<% if (idSession != null){ %>
			<a class="float-right" href="/gestionlivraison//InsertionLivreurServlet">
				<button class="btn btn-success"><i class="fas fa-plus"></i> Ajouter</button>
			</a>
			<% } %>
		</th>
	</tr>
	</thead>
	<tbody>
<%for(int i = 0; i < liste.length; i++){ %>
<tr>
	<td class="text-right"><%= i+1 %></td>
	<td class="text-left"><%= liste[i].getNom() %></td>
	<td class="text-left"><%= liste[i].getPrenom() %></td>
	<td class="text-left"><%= liste[i].getAxe() %></td>
	<td class="text-center"><%=liste[i].getDateEntree() %></td>
	<td class="text-center"><% if (idSession != null){ %><a href="/gestionlivraison/ModifierLivreur.html?id=<%= liste[i].getId() %>"><i class="fas fa-edit"></i></a> <% } %></td>
	<td class="text-center"><% if (idSession != null){ %>
	<form id="form" action="/gestionlivraison/ModifierLivreur.html" method="post">
			    <input type="hidden" name="idLivreur" value="<%= liste[i].getId() %>"/>
			    <input type="hidden" name="action" value="delete"/>
			    <a href="javascript:;" onclick="document.getElementById('form').submit();">
			    	<i class="fas fa-trash"></i>
			    </a>
			</form>		
	<% } %></td>
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