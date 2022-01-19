<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "ramorasata.Axe, ramorasata.Livreur" %>
 <% Axe[] axe = (Axe[]) request.getAttribute("axe");
 	Livreur livreur = (Livreur) request.getAttribute("livreur");
 	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="assets/images/icons/favicon.ico"/>
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
	<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
	<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="assets/vendor/daterangepicker/daterangepicker.css">
	<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="assets/css/util.css">
	<link rel="stylesheet" type="text/css" href="assets/css/main.css">
	<!--===============================================================================================-->
	<title>Insert title here</title>
</head>
<body>
<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(assets/images/bg-02.jpg);">
					<span class="login100-form-title-1">
						Livreur
					</span>
				</div>
			<div class="ml-4 mt-4"><a href="/gestionlivraison/ListeServlet.html">Retour</a></div>
				<form class="login100-form validate-form" action="/gestionlivraison/ModifierLivreur.html" method="post">
					<div class="wrap-input100 validate-input m-b-26" data-validate="Le produit est requis">
						<span class="label-input100">Nom</span>
						<input type="text" name="nom" value="<%= livreur.getNom() %>" class="input100">
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-26" data-validate="Le produit est requis">
						<span class="label-input100">Prenom</span>
						<input type="text" name="prenom" value="<%= livreur.getPrenom() %> " class="input100">
						<span class="focus-input100"></span>
					</div>
				
					<div class="wrap-input100 validate-input m-b-26" data-validate="L'axe est requis">
						<span class="label-input100">Axe</span>
						<select name="idAxe" class="input100">
							<%for(int i = 0; i<axe.length; i++){
								if(axe[i].getId() == livreur.getIdAxe()) { %>
									<option value="<%= axe[i].getId() %>" selected><%= axe[i].getNom() %></option>
								<% } else { %>
									<option value="<%= axe[i].getId() %>"><%= axe[i].getNom() %></option>
								<% } } %>
						</select>
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26" data-validate="La date est requis">
						<span class="label-input100">Date</span>
						<input type="date" name="dateEntree" class="input100" value ="<%= livreur.getDateEntree() %>">'
						<input type="hidden" name="idLivreur" value="<%= livreur.getId() %>">
						<span class="focus-input100"></span>
					</div>


					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">
							Ajouter
							<button>
							</div>
					</form>
				<%if(request.getAttribute("erreur") != null) { %>
									<div class="alert alert-danger" role="alert">
									 	<%= request.getAttribute("erreur") %>
									</div>
								<% } %>
				</div>
			</div>
</div>
			<!--===============================================================================================-->
			<script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
			<!--===============================================================================================-->
			<script src="assets/vendor/animsition/js/animsition.min.js"></script>
			<!--===============================================================================================-->
			<script src="assets/vendor/bootstrap/js/popper.js"></script>
			<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
			<!--===============================================================================================-->
			<script src="assets/vendor/select2/select2.min.js"></script>
			<!--===============================================================================================-->
			<script src="assets/vendor/daterangepicker/moment.min.js"></script>
			<script src="assets/"></script>
			<!--===============================================================================================-->
			<script src="assets/vendor/countdowntime/countdowntime.js"></script>
			<!--===============================================================================================-->
			<script src="assets/js/main.js"></script>
</body>
</html>