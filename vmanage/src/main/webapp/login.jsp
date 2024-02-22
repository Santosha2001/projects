<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Success page</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
			integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
			integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
			crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
			integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
			crossorigin="anonymous"></script>

		<!-- <link rel="stylesheet" href="custom.css"> -->

	</head>

	<body class="style-body">

		<nav class="navbar navbar-expand-lg bg-secondary ">
			<div class="container-fluid ">
				<a class="navbar-brand" href="#">Vendor</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
					data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active" aria-current="page" href="index.jsp">Home</a></li>
						<li class="nav-item"><a class="nav-link" href="Registration.jsp">Register Here</a></li>
					</ul>
				</div>
			</div>
		</nav>

        <h3>${noErrors}</h3>

		<footer class="bg-dark py-1 mt-5 footer-1">
			<div class="container text-light text-center">
				<p class="display-5 mb-3">Vendor Management</p>
				<small class="text-white-50">&copy; Copyright by X-Workz. All
					rights reserved.</small>
			</div>
		</footer>
	</body>

	</html>