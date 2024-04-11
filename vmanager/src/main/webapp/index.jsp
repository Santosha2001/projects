<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Home - Vendor Management</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>


<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	background-image:
		url("https://media.istockphoto.com/id/1677846143/photo/time-management-planning.webp?b=1&s=170667a&w=0&k=20&c=Y7-sCkWDieJ2SVxL_dUaWBOVzSSqLU8rEKSbaI-eEsE=");
	/* background-repeat: no-repeat; */
	background-size: cover;
}

.navbar, .footer-1 {
	background: #9148b7;
	height: 13vh;
}

.vendor-info {
	/* border: 2px solid black; */
	height: 60vh;
	margin-top: 30vh;
	width: 70%;
	line-height: 30px;
	font-size: 1.5rem;
}
</style>

</head>

<body>

	<!-- NAVBAR START -->
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark">

		<div class="container">
			<img class="navbar-brand"
				src="https://www.x-workz.in/static/media/Logo.cf195593dc1b3f921369.png"
				alt="img" width="100px" height="60px" />

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/"><i
							class="fa-solid fa-house"></i> Home</a></li>
				</ul>

				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/loadLoginPage"><i
							class="fa-solid fa-right-to-bracket"></i> LOGIN</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/loadRegister"><i
							class="fa-regular fa-address-card"></i> REGISTER</a></li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="AdminLogin.jsp"><i
							class="fa-solid fa-user-plus"></i> ADMIN</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END NAVBAR -->

	<div
		class="container d-flex justify-content-center align-items-center text-white vendor-info">
		Vendor management is the process that organizations use to manage
		their suppliers, also known as vendors. It includes activities such
		as: selecting vendors, negotiating contracts, controlling costs,
		reducing vendor-related risks, and ensuring service delivery. The
		vendor management process includes four stages that can ensure a
		productive relationship with vendors: Segmentation, Collaboration,
		Implementation, and Evaluation.</div>



	<footer class="py-1 mt-5 footer-1">
		<div class="container text-light text-center">
			<p class="display">Vendor Management</p>
			<small class="text-white-60">&copy; Copyright by X-Workz. All
				rights reserved.</small>
		</div>
	</footer>
</body>

</html>