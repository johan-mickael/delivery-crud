<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import = "	rakotoniaina.Livraison,rakotoniaina.Frais,
ramorasata.Liste, ramorasata.Axe"%>
<% 	ServletContext context = this.getServletContext();
Liste[] livreurs = (Liste[]) request.getAttribute("livreurs");
Frais[] frais = (Frais[]) context.getAttribute("frais"); 
Axe[] axes = (Axe[]) context.getAttribute("axe"); 
String action = (String) request.getAttribute("action"); 
Livraison livraison = (Livraison) request.getAttribute("livraison");
Exception ex = (Exception) request.getAttribute("exception"); %>
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
	<title>Gerer Livraison</title>
</head>
<body>
	<div class="limiter">
		<% if(action.compareTo("INSERT") == 0) { %>
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(assets/images/bg-02.jpg);">
					<span class="login100-form-title-1">
						Livraison
					</span>
				</div>
				<div class="ml-4 mt-4"><a href="/gestionlivraison/LivraisonServlet.html">Retour</a></div>
				<form class="login100-form validate-form" action="LivraisonServlet.html" method="post">
					<input type="hidden" name="action" value="GENERERFORMULAIRE" />
					<div class="wrap-input100 validate-input m-b-26" data-validate="L'axe est requis">
						<span class="label-input100">Axe</span>
						<select name="idAxe" class="input100">
							<% for(int i=0; i<axes.length; i++){ %>
							<option value="<%= axes[i].getId() %>">
								<%= axes[i].getNom() %>
							</option>
							<% } %>
						</select>
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26" data-validate="Le frais est requis">
						<span class="label-input100">Frais</span>
						<select name="idFrais" class="input100">
							<% for(int i=0; i<frais.length; i++){ %>
							<option value="<%= frais[i].getId() %>">
								<%= frais[i].getValeur() %>
							</option>
							<% } %>
						</select>
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26" data-validate="Le livreur est requis">
						<span class="label-input100">Livreur</span>
						<select name="idLivreur" class="input100">
							<% for(int i=0; i<livreurs.length; i++){ %>
							<option value="<%= livreurs[i].getId() %>">
								<%= livreurs[i].getNom()+" "+livreurs[i].getPrenom()+" - "+livreurs[i].getAxe() %>
							</option>
							<% } %>
						</select>
						<span class="focus-input100"></span>
					</div>
					
					<input type="hidden" name="idAxeLivreur" value="<%  %>" />

					<div class="wrap-input100 validate-input m-b-26" data-validate="La date est requis">
						<span class="label-input100">Date</span>
						<input type="date" name="dateLivraison" class="input100">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-26" data-validate="Le produit est requis">
						<span class="label-input100">Produit</span>
						<input type="text" name="produit" class="input100">
						<span class="focus-input100"></span>
					</div>
					
					<input type="hidden" name="etat" value="1"/>

					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn">
							Ajouter
							<button>
							</div>
						</form>
						<% if(ex != null) { %>
						<div class="alert alert-danger" role="alert">
							<%= ex.getMessage() %>
						</div>
						<% } %>
					</div>
				</div>
				<% } %>
				<% if(action.compareTo("UPDATE") == 0) { %>
				<div class="container-login100">
					<div class="wrap-login100">
						<div class="login100-form-title" style="background-image: url(assets/images/bg-02.jpg);">
							<span class="login100-form-title-1">
								Livraison
							</span>
						</div>
						<div class="ml-4 mt-4"><a href="/gestionlivraison/LivraisonServlet.html">Retour</a></div>
						<form class="login100-form validate-form" action="LivraisonServlet.html" method="post">
							<input type="hidden" name="action" value="MODIFIERLIVRAISON" />
							<input type="hidden" name="idLivraison" value="<%= livraison.getId() %>" />
							<div class="wrap-input100 validate-input m-b-26" data-validate="L'axe est requis">
								<span class="label-input100">Axe</span>
								<select name="idAxe" class="input100">
									<% for(int i=0; i<axes.length; i++){ %>
									<option value="<%= axes[i].getId() %>" <% if(axes[i].getId() == livraison.getIdAxe()){ %> selected <% }%> >
										<%= axes[i].getNom() %>
									</option>
									<% } %>
								</select>
								<span class="focus-input100"></span>
							</div>

							<div class="wrap-input100 validate-input m-b-26" data-validate="Le frais est requis">
								<span class="label-input100">Frais</span>
								<select name="idFrais" class="input100">
									<% for(int i=0; i<frais.length; i++){ %>
									<option value="<%= frais[i].getId() %>"	<% if(frais[i].getId() == livraison.getIdFrais()) { %>selected<% } %>>
										<%= frais[i].getValeur() %>
									</option>
									<% } %>
								</select>
								<span class="focus-input100"></span>
							</div>

							<div class="wrap-input100 validate-input m-b-26" data-validate="Le livreur est requis">
								<span class="label-input100">Livreur</span>
								<select name="idLivreur" class="input100">
									<% for(int i=0; i<livreurs.length; i++){ %>
									<option value="<%= livreurs[i].getId() %>" <% if(livreurs[i].getId() == livraison.getIdLivreur()) { %>selected <% } %>>
										<%= livreurs[i].getNom()+" "+livreurs[i].getPrenom()+" - "+livreurs[i].getIdAxe() %>
									</option>
									<% } %>
								</select>
								<span class="focus-input100"></span>
							</div>

							<div class="wrap-input100 validate-input m-b-26" data-validate="La date est requis">
								<span class="label-input100">Date</span>
								<input type="date" name="dateLivraison" class="input100" value="<%= livraison.getDateLivraison() %>">
								<span class="focus-input100"></span>
							</div>

							<div class="wrap-input100 validate-input m-b-26" data-validate="Le produit est requis">
								<span class="label-input100">Produit</span>
								<input type="text" name="produit" class="input100" value="<%= livraison.getProduit() %>">
								<span class="focus-input100"></span>
							</div>
							
							<div class="wrap-input100 validate-input m-b-26" data-validate="L'etat est requis">
								<span class="label-input100">Etat</span>
								<input class="input10" type="radio" name="etat" value="1" <% if(livraison.getEtat() == 1) { %> checked <% } %>/>
								En cours&emsp;
								<input class="input10" type="radio" name="etat" value="2" <% if(livraison.getEtat() == 2) { %> checked <% } %>/>
								Terminée&emsp;
								<input class="input10" type="radio" name="etat" value="3" <% if(livraison.getEtat() == 3) { %> checked <% } %>/>
								Inachevée&emsp;
								<span class="focus-input100"></span>
							</div>

							<div class="container-login100-form-btn">
								<button type="submit" class="login100-form-btn">
									Modifier
									<button>
									</div>
								</form>
								<% if(ex != null) { %>
								<div class="alert alert-danger" role="alert">
									<%= ex.getMessage() %>
								</div>
								<% } %>
							</div>
						</div>
						<% } %>
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