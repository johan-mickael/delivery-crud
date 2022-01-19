<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V15</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
</head>
<body>	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(assets/images/bg-02.jpg);">
					<span class="login100-form-title-1">
						Connexion
					</span>
				</div>

				<form class="login100-form validate-form" action="/gestionlivraison/LoginServlet.html" method="post">
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">Identifiant</span>
						<input class="input100" type="text" name="identifiant" placeholder="" value="Johan">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
						<span class="label-input100">Pass</span>
						<input class="input100" type="password" name="mdp" placeholder="" value="Johan">
						<span class="focus-input100"></span>
					</div>

					<div class="container-login100-form-btn text-center">
						<button type="submit" class="login100-form-btn">
							Se connecter
							<button>
							</div>
							<% if(request.getAttribute("erreur") != null) { %>
							<div class="alert alert-danger mt-4" role="alert">
								<%= request.getAttribute("erreur") %> 
							</div>
							<% } %>
						</form>
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