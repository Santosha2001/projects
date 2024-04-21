<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login - Vendor Management</title>

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

<style>
* {
	margin: 0;
	padding: 0;
}

.navbar, .footer-1 {
	background: #9148b7;
	height: 14vh;
}
/* 
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}

.card-sh {
	box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
	border-radius: 10px;
}

.form-control {
	margin-bottom: 10px;
	border-width: 2px;
} */

.card {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	border-radius: 30px;
	padding: 10px;
	margin-top: 40px; box-shadow : 0px 0px 5px rgba( 0, 0, 0, 0.2);
	margin-left: 15%;
	margin-bottom: 40px; 
	width: 70%;
	height: 480px;
	background: #12c2e9;
	/* fallback for old browsers */
	background: -webkit-linear-gradient(to right, #f64f59, #c471ed, #12c2e9);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to right, #f64f59, #c471ed, #12c2e9);
	box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.left-container {
	background: #000000;
	background: -webkit-linear-gradient(to right, #434343, #000000);
	background: linear-gradient(to right, #434343, #000000);
	flex: 1;
	max-width: 35%;
	display: flex;
	flex-direction: column;
	align-items: center;
	height: 430px;
	padding: 10px;
	margin: 30px;
	border-radius: 20px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}

.right-container {
    background: #000000;
    background: -webkit-linear-gradient(to left, #434343, #000000);
    background: linear-gradient(to left, #434343, #000000);
    flex: 1;
    max-width: 90%;
    height: 430px;
    padding: 10px;
    margin: 20px;
    border-radius: 30px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

.profile-img {
    width: 200px;
    height: 200px;
    max-width: 200px;
    border-radius: 50%;
    margin: 10px;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

h2 {
    font-size: 24px;
    margin-bottom: 5px;
}

h3 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 5px;
}

.gradienttext {
    background-image: linear-gradient(to right, #ee00ff 0%, #fbff00 100%);
    color: transparent;
    -webkit-background-clip: text;
    
}

p {
    font-size: 18px;
    margin-bottom: 20px;
    color: aliceblue
}

table {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 280px;
    border-collapse: collapse;
}

td {

    padding: 10px;
    border: none;
    border-radius: 20px;
    color: white;
}

td:first-child {
    font-weight: bold;
}

.credit a {
    text-decoration: none;
    color: #fff;
    font-weight: 800;
}

.credit {
    color: #fff;
    text-align: center;
    margin-top: 10px;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

.update{
	margin-top: 20px;
	text-align: center;
	text-decoration: none;
	background-image: linear-gradient(to right, #ee00ff 0%, #fbff00 100%);
    color: transparent;
    -webkit-background-clip: text;
}
</style>

</head>

<body>
	<!-- NAVBAR START -->
	<nav class="navbar navbar-expand-lg navbar-dark">

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
					<li class="nav-item"><a class="nav-link active "
						aria-current="page" href="${pageContext.request.contextPath}/"><i
							class="fa-solid fa-house"></i> Home</a></li>
				</ul>

				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/loadLoginPage"><i
							class="fa-solid fa-right-to-bracket"></i> LOGIN</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/loadRegister"><i
							class="fa-regular fa-address-card"></i> REGISTER</a></li>

					<%-- <li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/loadAdmin"><i
							class="fa-solid fa-user-plus"></i> ADMIN</a></li> --%>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END NAVBAR -->

				<div class="card-header text-center mt-2">
					<% if (session.getAttribute("update-success") != null) { %>
						<div class="text-center text-success fs-4"><%= session.getAttribute("update-success") %></div>
					<% session.removeAttribute("update-success"); %>
					<% } %>
				</div>
				
				<div class="card-header text-center mt-2">
					<% if (session.getAttribute("update-failed") != null) { %>
						<div class="text-center text-danger fs-4"><%= session.getAttribute("update-failed") %></div>
					<% session.removeAttribute("update-failed"); %>
					<% } %>
				</div>
				
				<div class="card-header text-centerr mt-3">
					<% if (session.getAttribute("login-success") != null) { %>
						<div class="text-center text-success fs-3"><%= session.getAttribute("login-success") %></div>
					<% session.removeAttribute("login-success"); %>
					<% } %>
				</div>
					
				<div class="card ">
		
					<div class="left-container">
						<img
							src="https://cdn.pixabay.com/photo/2015/01/08/18/29/entrepreneur-593358__480.jpg"
							alt="Profile Image" class="profile-img">
						<h2 class="gradienttext">${vendorEntity.getVendorNname()}</h2>
						
						<a href="${pageContext.request.contextPath}/load-update?email=${vendorEntity.getVendorEmail()}" 
							class="container btn btn-m btn-primary update">Update Details</a>
							
						<a href="${pageContext.request.contextPath}/load-delete-account" 
							class="container btn btn-m btn-primary update">Delete Account</a>
						
					</div>
					
					<div class="right-container">
						<h3 class="container gradienttext mb-4">Vendor Details</h3>
						<table>
							<tr>
								<td>Owner Name :</td>
								<td>${vendorEntity.getOwnerName()}</td>
							</tr>
							<tr>
								<td>Email :</td>
								<td>${vendorEntity.getVendorEmail()}</td>
							</tr>
							<tr>
								<td>Mobile :</td>
								<td>+91 ${vendorEntity.getContactNumber()}</td>
							</tr>
							<tr>
								<td>GST :</td>
								<td>${vendorEntity.getVendorGSTNumber()}</td>
							</tr>
							<tr>
								<td>Location :</td>
								<td>${vendorEntity.getVendorLocation()}</td>
							</tr>
							<tr>
								<td>Start Date :</td>
								<td>${vendorEntity.getCompanyStartDate()}</td>
							</tr>
							<tr>
								<td>State :</td>
								<td>${vendorEntity.getApplyStatus()}</td>
							</tr>
						</table>
						
						<!-- <div class="credit">
							Made with <span style="color: tomato; font-size: 20px;">❤
							</span>by<a href="https://www.learningrobo.com/"> Learning Robo</a>
						</div> -->
						
					</div>
				</div>

	<footer class="py-1 mt-2 footer-1 ">
		<div class="container text-light text-center">
			<p class="display">Vendor Management</p>
			<small class="text-white-60">&copy; Copyright by X-Workz. All
				rights reserved.</small>
		</div>
	</footer>

</body>

</html>