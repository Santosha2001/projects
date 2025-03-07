<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Status Approve - Vendor Management</title>

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
	height: 13vh;
}

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
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/"><i
							class="fa-solid fa-house"></i> Home</a></li>
				</ul>

				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<%-- <li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/loadLoginPage"><i
							class="fa-solid fa-right-to-bracket"></i> LOGIN</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/loadRegister"><i
							class="fa-regular fa-address-card"></i> REGISTER</a></li> --%>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${pageContext.request.contextPath}/loadAdmin"><i
							class="fa-solid fa-user-plus"></i> ADMIN</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END NAVBAR -->
	

	<div class="container mt-2 p-5">
		<div class="row">
			<div class="col-md-5 offset-md-4">
				<div class="card card-sh">
					<div class="card-header text-center">
						<p class="fs-3 text-uppercase">Vendor Details</p>
						
						<% if (session.getAttribute("message") != null) { %>
						<div class="text-center text-danger fs-4"><%= session.getAttribute("message") %></div>
						<% session.removeAttribute("message"); %>
						<% } %>
						
					</div>

					<div class="card-body">
						<form action="statusUpdate" method="post">
						
							<div class="mb-3">
								<label class="form-label">Vendor name</label> 
								<input type="text" class="form-control"
									id="vendorNname" name="vendorNname"
									value="${vendorEntity.getVendorNname()}" 
									readonly="readonly" />
							</div>
							
							<div class="mb-3">
								<label class="form-label">Vendor Email</label> 
								<input type="email" class="form-control" id="vendorEmail" 
									name="vendorEmail" value="${vendorEntity.getVendorEmail()}" 
									readonly="readonly" />
							</div>
							
							<div class="mb-3">
								<label class="form-label">Vendor GST</label> 
								<input type="text" class="form-control" id="vendorGSTNumber" 
									name="vendorGSTNumber" value="${vendorEntity.getVendorGSTNumber()}"
									readonly="readonly" />
							</div>
							
							<div class="mb-3">
								<label class="form-label">Contact Number</label> 
								<input type="number" class="form-control" id="contactNumber"
									name="contactNumber" value="${vendorEntity.getContactNumber()}"
									readonly="readonly" />
							</div>
							
							<div class="mb-3">
								<label class="form-label">Owner name</label> 
								<input type="text" class="form-control" id="ownerName" 
									name="ownerName" value="${vendorEntity.getOwnerName()}" 
									readonly="readonly" />
							</div>

						
							<button type="submit" id="otpVerifyBtn"
								class="btn form-control btn-primary btn-sm mt-4 mb-2">Approve Status</button>
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<footer class="py-1 mt-2 footer-1">
		<div class="container text-light text-center">
			<p class="display">Vendor Management</p>
			<small class="text-white-60">&copy; Copyright by X-Workz. All
				rights reserved.</small>
		</div>
	</footer>

</body>

</html>