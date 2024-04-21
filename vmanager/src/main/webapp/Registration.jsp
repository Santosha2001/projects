<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration-Vendor Management</title>

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

.form-control{
	margin-bottom: 10px;
	border-width: 2px;
}

.form-label{
	margin-bottom: 1px;
}

input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}
</style>

</head>

<body>
	<!-- NAVBAR START -->
	<nav class="navbar navbar-expand-lg  navbar-dark">

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

<!-- default error messages from the backend java -->
            <span class="error text-center text-danger">
                <c:forEach var="objectErrors" items="${errors}">
                    <h3>${objectErrors.defaultMessage}</h3><br>
                </c:forEach>
            </span>
            
             <!-- field existing error -->
            <span class="error text-center text-danger">
                <h3>${uniqueError}</h3>
            </span>
            
             <!-- no error message -->
            <span class="success text-center text-success">
                <h3>${message}</h3>
            </span>
            
	<div class="container mt-4 p-2">
		<div class="row">

			<div class="col-md-5 p-5">
				<img alt=""
					src="https://cdn-icons-png.flaticon.com/512/3456/3456388.png"
					width="100%" height="600px" />
			</div>

			<div class="col-md-6 mt-5 p-2">
				<div class="card shadow p-3 mb-5 bg-body-tertiary rounded">
					<div class="card-header">
						<p class="fs-4 text-center register-text">REGISTER HERE</p>

						<!-- default error messages from the backend java -->
						<span class="text-center text-danger"> <c:forEach
								var="objectErrors" items="${error}">
								<p>${objectErrors.defaultMessage}</p>
								<br>
							</c:forEach>
						</span>

						<% if (session.getAttribute("saveMsg") != null) { %>
							<div class="text-center text-success fs-3"><%= session.getAttribute("saveMsg") %></div>
						<% session.removeAttribute("saveMsg"); %>
						<% }  %>
						
						<% if (session.getAttribute("unsaveMsg") != null) { %>
							<div class="text-center text-danger fs-3"><%= session.getAttribute("unsaveMsg") %></div>
						<% session.removeAttribute("unsaveMsg"); %>
						<% }  %>
						
					</div>

					<div class="card-body">

						<!-- FORM -->
						<form action="register" method="post" >
							<div class="row">

								<!-- VENDOR NAME -->
								<div class="col">
									<label class="form-label">Vendor Name</label> <input
										class="form-control" name="vendorNname" type="text"
										id="vendorNname" value="${vendorEntity.getVendorNname()}"
										maxlength="20" onblur="validateName()" required /> <span
										id="nameError" style="color: red;"></span>
								</div>

								<!-- VENDOR LOATION -->
								<div class="col">
									<label class="form-label">Vendor Location</label> <input
										class="form-control" type="text" id="vendorLocation"
										name="vendorLocation"
										value="${vendorEntity.getVendorLocation()}" maxlength="20"
										onblur="validateLocation()" required /> <span
										id="locationError" style="color: red;"></span>
								</div>
							</div>

							<div class="row">
								<!-- GST NUMBER -->
								<div class="col">
									<label class="form-label">GST Number</label> <input
										class="form-control" type="text" id="vendorGSTNumber"
										name="vendorGSTNumber"
										value="${vendorEntity.getVendorGSTNumber()}" maxlength="15"
										onblur="gstAjax()" required /> <span id="gstError"
										style="color: red;"></span>
								</div>

								<!-- COMPANY START DATE -->
								<div class="col">
									<label class="form-label">Company Start Date</label> <input
										class="form-control" type="date" id="companyStartDate"
										name="companyStartDate"
										value="${vendorEntity.getCompanyStartDate()}"
										onclick="dateValidate()" /> <span id="startDateError"
										style="color: red;"></span>
								</div>
							</div>

							<div class="row">
								<!-- OWNER NAME -->
								<div class="col">
									<label class="form-label">Owner Name</label> <input
										class="form-control" type="text" id="ownerName"
										name="ownerName" value="${vendorEntity.getOwnerName()}"
										maxlength="20" onblur="validateOwnerName()" required /> <span
										id="ownerError" style="color: red;"></span>
								</div>

								<!-- SERVICE TYPE -->
								<div class="col">
									<label class="form-label">Service Type</label> <select
										class="form-select form-select-sm form-control"
										name="serviceType" id="serviceType" onclick="validateType()"
										required>
										<option value="">Select option</option>
										<option value="Water"
											<c:if test="${vendorEntity.getServiceType()=='Water'}">
                                                    selected="selected"</c:if>>Water</option>
										<option value="Milk"
											<c:if test="${vendorEntity.getServiceType()=='Milk'}">
                                                    selected="selected"</c:if>>Milk</option>
										<option value="Food"
											<c:if test="${vendorEntity.getServiceType()=='Food'}">
                                                    selected="selected"</c:if>>Food</option>
										<option value="Internet"
											<c:if test="${vendorEntity.getServiceType()=='Internet'}">
                                                    selected="selected"</c:if>>Internet</option>
										<option value="Laptop"
											<c:if test="${vendorEntity.getServiceType()=='Laptop'}">
                                                    selected="selected"</c:if>>Laptop</option>
									</select> <span id="serviceError" style="color: red;"></span>
								</div>
							</div>

							<div class="row">
								<!-- CONTACT NUMBER -->
								<div class="col">
									<label class="form-label">Contact Number</label> <input
										class="form-control" type="number" id="contactNumber"
										name="contactNumber"
										value="${vendorEntity.getContactNumber()}"
										onblur="numberAjax()" /> <span id="numberError"
										style="color: red;"></span>
								</div>

								<!-- ALTERNATE NUMBER -->
								<div class="col">
									<label class="form-label">Alternate Contact Number</label> <input
										class="form-control" type="number" id="alternateContactNumber"
										name="alternateContactNumber"
										value="${vendorEntity.getAlternateContactNumber()}" />
								</div>
							</div>

							<div class="row">
								<!-- EMAIL ADDRESS -->
								<div class="col">
									<label class="form-label">Email Address</label> <input
										class="form-control" type="email" id="vendorEmail"
										name="vendorEmail" value="${vendorEntity.getVendorEmail()}"
										onblur="uniqueMail()"  required />

									<span id="emailError" style="color: red;"></span>
								</div>

								<!-- WEBSITE -->
								<div class="col">
									<label class="form-label">Website</label> <input
										class="form-control" type="text" id="website" name="website"
										value="${vendorEntity.getWebsite()}" onblur="websiteAjax()"
										required /> <span id="websiteError" style="color: red;"></span>
								</div>
							</div>


							<button type="submit"
								class="btn bg-primary text-white col-md-12 mt-3"
								id="registerButton">Register</button>
						</form>
					</div>

					<div class="card-footer text-center">
						have an account ? <a
							href="${pageContext.request.contextPath}/loadLoginPage"
							class="text-decoration-none">Login here</a>
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

	<!-- javascript -->
	<script>
                // name validate
                function validateName() {
                    let name = document.getElementById("vendorNname").value;
                    let btn = document.getElementById("registerButton");

                    if (name == null || name == "") {
                        document.getElementById("nameError").innerHTML = "*name can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if (name.includes('  ')) {
                        document.getElementById("nameError").innerHTML = "*name can't be empty.";
                        btn.setAttribute("disabled", "");
                    } else if (name.match(/[0-9]/)) {
                        document.getElementById("nameError").innerHTML = "*name should be in characters.";
                        btn.setAttribute("disabled", "");
                    } else if (name.length < 3 || name.length > 20) {
                        document.getElementById("nameError").innerHTML = "*name should be in 3-20 range.";
                        btn.setAttribute("disabled", "");
                    } else {
                        document.getElementById("nameError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    }
                }


                // location validate
                function validateLocation() {
                    const location = document.getElementById("vendorLocation").value;
                    const btn = document.getElementById("registerButton");

                    if (location == null || location == "") {
                        document.getElementById("locationError").innerHTML = "*location can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if (location.includes('  ')) {
                        document.getElementById("locationError").innerHTML = "*location can't be empty.";
                        btn.setAttribute("disabled", "");
                    } else if (location.match(/[0-9]/)) {
                        document.getElementById("locationError").innerHTML = "*location should be in characters.";
                        btn.setAttribute("disabled", "");
                    } else if (location.length < 3 || location.length > 20) {
                        document.getElementById("locationError").innerHTML = "*location should be in 3-20 range.";
                        btn.setAttribute("disabled", "");
                    } else {
                        document.getElementById("locationError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    }
                }


                // gst validation with ajax
                function gstAjax() {
                    console.log("runnig gst ajax.");
                    const gst = document.getElementById("vendorGSTNumber").value;
                    const btn = document.getElementById("registerButton");
                    console.log(gst);

                    const gstRegex = /^(\d{2})([A-Z]{5})(\d{4})([A-Z]{1})([A-Z\d]{1})([A-Z\d]{1})$/;

                    if (gst != null && gst != "" && gst.length == 15) {
                        console.log(gst)
                        document.getElementById("gstError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8082/vmanager/gstAjax/" + gst);
                        xhttp.send();

                        xhttp.onload = function () {
                            document.getElementById("gstError").innerHTML = this.responseText;
                        }

                        document.getElementById("gstError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    } else if (gst == null || gst == "") {
                        document.getElementById("gstError").innerHTML = "gst can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if (gst.includes('  ')) {
                        document.getElementById("gstError").innerHTML = "gst can't be empty.";
                        btn.setAttribute("disabled", "");
                    } else if (gst.length < 15 || gst.length > 15) {
                        document.getElementById("gstError").innerHTML = "gst should be 15 characters.";
                        btn.setAttribute("disabled", "");
                    } else if (gstRegex.test(gst) == false) {
                        document.getElementById("gstError").innerHTML = "gst should be in proper format.";
                        btn.setAttribute("disabled", "");
                    }
                }

                //start date validation
                function dateValidate() {
                    let date = document.getElementById("companyStartDate").value;
                    let btn = document.getElementById("registerButton");

                    if (date == null || date === "") {
                        console.log("date is invalid");
                        document.getElementById("startDateError").innerHTML = "*please privide valid date.";
                        btn.setAttribute("disabled", "");
                    } else {
                        console.log("date is valid");
                        document.getElementById("startDateError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    }

                }

                // owner name validate
                function validateOwnerName() {
                    let owner = document.getElementById("ownerName").value;
                    let btn = document.getElementById("registerButton");

                    if (owner == null || owner == "") {
                        document.getElementById("ownerError").innerHTML = "*owner name can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if (owner.includes('  ')) {
                        document.getElementById("ownerError").innerHTML = "*owner name can't be empty.";
                        btn.setAttribute("disabled", "");
                    } else if (owner.match(/[0-9]/)) {
                        document.getElementById("ownerError").innerHTML = "*owner name should be in characters.";
                        btn.setAttribute("disabled", "");
                    } else if (owner.length < 3 || owner.length > 20) {
                        document.getElementById("ownerError").innerHTML = "*owner name should be in 3-20 range.";
                        btn.setAttribute("disabled", "");
                    } else {
                        document.getElementById("ownerError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    }
                }


                // service type validation
                function validateType() {
                    const serviceType = document.getElementById("serviceType");
                    let btn = document.getElementById("registerButton");

                    if (serviceType.selectedIndex === 0) {
                        document.getElementById("serviceError").innerHTML = "*select a type.";
                        btn.setAttribute("disabled", "");
                    } else {
                        document.getElementById("serviceError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    }
                }


                // contact number validation ajax
                function numberAjax() {
                    const mobile = document.getElementById("contactNumber").value;
                    const btn = document.getElementById("registerButton");
                    var mobileNumberRegex = /^[6-9]{1}[0-9]{9}$/;

                    if (mobile != "" && mobile.length == 10) {
                        console.log(mobile);
                        document.getElementById("numberError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8082/vmanager/mobileAjax/" + mobile);
                        xhttp.send();

                        xhttp.onload = function () {
                            document.getElementById("numberError").innerHTML = this.responseText;
                        }
                        btn.removeAttribute("disabled");
                    } else if (mobile == null || mobile == "") {
                        document.getElementById("numberError").innerHTML = "*number can't blank.";
                        btn.setAttribute("disabled", "");
                    } else if (mobile.includes('  ')) {
                        document.getElementById("numberError").innerHTML = "*number can't empty.";
                        btn.setAttribute("disabled", "");
                    } else if (mobile.length < 10 || mobile.length > 10) {
                        document.getElementById("numberError").innerHTML = "*number should be 10 numbers.";
                        btn.setAttribute("disabled", "");
                    }
                    else if (!mobileNumberRegex.test(mobile)) {
                        document.getElementById("numberError").innerHTML = "*number should in format.";
                        btn.setAttribute("disabled", "");
                    }
                }


                // email ajax
                function uniqueMail() {
                    var gmail = document.getElementById("vendorEmail").value;
                    const btn = document.getElementById("registerButton");
                    // const regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
                    const regex = /^([a-zA-Z0-9\.]+@+[a-zA-Z]+(\.)+[a-zA-Z]{2,})$/;
                    const result = regex.test(gmail);

                    if (gmail != null && gmail != "" && gmail.length > 5 && gmail.length < 30) {
                        console.log("email is valid.");
                        document.getElementById("emailError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8082/vmanager/emailAjax/" + gmail);
                        xhttp.send();

                        xhttp.onload = function () {
                            document.getElementById("emailError").innerHTML = this.responseText;
                        }

                        document.getElementById("emailError").innerHTML = "";
                        btn.removeAttribute("disabled");

                    } else if (gmail == null || gmail == "" || gmail.includes("  ")) {
                        document.getElementById("emailError").innerHTML = "*email can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if (gmail.length < 5 || gmail.length > 30) {
                        document.getElementById("emailError").innerHTML = "*email should be in 5-30 character.";
                        btn.setAttribute("disabled", "");
                    } else if (result == false) {
                        document.getElementById("emailError").innerHTML = "*email should be in format.";
                        btn.setAttribute("disabled", "");
                    }
                }

                // website ajax
                function websiteAjax() {
                    console.log("running website ajax.")
                    const site = document.getElementById("website").value;
                    console.log(site);
                    if (site.length != null && site.length != "" && site.length > 10 && site.length < 30) {
                        document.getElementById("websiteError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8082/vmanager/siteAjax/" + site);
                        xhttp.send();
                        xhttp.onload = function () {
                            document.getElementById("websiteError").innerHTML = this.responseText;
                        }
                    } else {
                        console.log("invalid website.")
                        document.getElementById("websiteError").innerHTML = "website is invalid.";
                    }
                }

                function autocomplete(inp, arr) {

                    var currentFocus;
                    inp.addEventListener("input", function (e) {
                        var a, b, i, val = this.value;
                        closeAllLists();
                        if (!val) { return false; }
                        currentFocus = -1;
                        a = document.createElement("DIV");
                        a.setAttribute("id", this.id + "autocomplete-list");
                        a.setAttribute("class", "autocomplete-items");
                        this.parentNode.appendChild(a);
                        for (i = 0; i < arr.length; i++) {
                            if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                                b = document.createElement("DIV");
                                b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                                b.innerHTML += arr[i].substr(val.length);
                                b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                                b.addEventListener("click", function (e) {
                                    inp.value = this.getElementsByTagName("input")[0].value;
                                    closeAllLists();
                                });
                                a.appendChild(b);
                            }
                        }
                    });
                    /*execute a function presses a key on the keyboard:*/
                    inp.addEventListener("keydown", function (e) {
                        var x = document.getElementById(this.id + "autocomplete-list");
                        if (x) x = x.getElementsByTagName("div");
                        if (e.keyCode == 40) {
                            currentFocus++;
                            addActive(x);
                        } else if (e.keyCode == 38) { //up
                            currentFocus--;
                            addActive(x);
                        } else if (e.keyCode == 13) {
                            e.preventDefault();
                            if (currentFocus > -1) {
                                if (x) x[currentFocus].click();
                            }
                        }
                    });
                    function addActive(x) {
                        if (!x) return false;
                        removeActive(x);
                        if (currentFocus >= x.length) currentFocus = 0;
                        if (currentFocus < 0) currentFocus = (x.length - 1);
                        x[currentFocus].classList.add("autocomplete-active");
                    }
                    function removeActive(x) {
                        for (var i = 0; i < x.length; i++) {
                            x[i].classList.remove("autocomplete-active");
                        }
                    }
                    function closeAllLists(elmnt) {
                        var x = document.getElementsByClassName("autocomplete-items");
                        for (var i = 0; i < x.length; i++) {
                            if (elmnt != x[i] && elmnt != inp) {
                                x[i].parentNode.removeChild(x[i]);
                            }
                        }
                    }
                    /*execute a function when someone clicks in the document:*/
                    document.addEventListener("click", function (e) {
                        closeAllLists(e.target);
                    });
                }

                /*An array containing all the country names in the world:*/
                var locations = ["Bengaluru", "Mumbai", "Pune", "hydrabad", "Mysore", "Hubli", "Mangalore", "Kochi", "Vishakapattanam", "Dehli", "Gurugram", "Jodpur", "Chennai", "Lucknow", "Kolkatta", "Belagam", "Davanagere", "Dandeli", "Jaipur", "Jammu & Kashmir", "Srinagar", "Andra pradesh", "Arunachal pradesh", "Gujarat", "Bellary"];

                /*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
                autocomplete(document.getElementById("vendorLocation"), locations);
            </script>
</body>

</html>