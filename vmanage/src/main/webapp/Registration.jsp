<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Registration page</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                crossorigin="anonymous"></script>

            <!-- <link rel="stylesheet" href="custom.css"> -->
            <!-- <script src="ajax.js"></script> -->

            <style>
                /* form outer border */
                .border {
                    width: 40vw;
                }

                /* form input */
                .form-control {
                    width: 35vw;
                    border-color: rgb(35, 34, 34);
                }

                input::-webkit-outer-spin-button,
                input::-webkit-inner-spin-button {
                    -webkit-appearance: none;
                    margin: 0;
                }
            </style>

        </head>

        <body>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark p-3 sticky-top">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Vendor</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Register here</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Log In</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    Dropdown
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <span style="color:red">
                <c:forEach var="objectErrors" items="${error}">
                    ${objectErrors.defaultMessage}<br>
                </c:forEach>
            </span>
            <span style="color:red">
                <h3>${uniqueError}</h3>
            </span>

            <span style="justify-content: center; color: green;">${noErrors}</span>

            <div class="container d-flex justify-content-center p-3 border  border-dark mt-2 mb-3">
                <form action="register" method="post">
                    <!-- VENDOR NAME -->
                    <div class="form-group mt-1">
                        <label for="vendorNname">Vendor name</label>
                        <input type="text" class="form-control" id="vendorNname" name="vendorNname"
                            value="${vendorEntity.getVendorNname()}" maxlength="20" 
                            onchange="validateName()" required />
                    </div>
                    <span id="nameError" style="color: red;"></span>

                    <!-- VENDOR LOCATION -->
                    <div class="form-group mt-3">
                        <label for="vendorLocation">Vendor Location</label>
                        <input type="text" class="form-control" id="vendorLocation" name="vendorLocation"
                            value="${vendorEntity.getVendorLocation()}" maxlength="20" 
                            onchange="validateLocation()" required />
                    </div>
                    <span id="locationError" style="color: red;"></span>

                    <!-- GST NUMBER -->
                    <div class="form-group mt-3">
                        <label for="vendorGSTNumber">GST Number</label>
                        <input type="text" class="form-control" id="vendorGSTNumber" name="vendorGSTNumber"
                            placeholder="" value="${vendorEntity.getVendorGSTNumber()}" 
                            maxlength="15" onblur="gstAjax()" required />
                    </div>
                    <span id="gstError" style="color: red;"></span>

                    <!-- COMPANY START DATE -->
                    <div class="form-group mt-3">
                        <label for="companyStartDate">Company Start Date</label>
                        <input type="date" class="form-control" id="companyStartDate" name="companyStartDate"
                            placeholder="" value="${vendorEntity.getCompanyStartDate()}" />
                    </div>
                    <span id="startDateError" style="color: red;"></span>

                    <!-- OWNER NAME -->
                    <div class="form-group mt-3">
                        <label for="ownerName">Company Owner Name</label>
                        <input type="text" class="form-control" id="ownerName" name="ownerName"
                            value="${vendorEntity.getOwnerName()}" maxlength="20" 
                            onchange="validateOwnerName()" required />
                    </div>
                    <span id="ownerError" style="color: red;"></span>

                    <!-- SERVICE TYPE -->
                    <div class="form-group mt-3">
                        <label for="serviceType">Service Type</label>
                        <select class="form-select form-select-sm form-control" name="serviceType" id="serviceType">
                            <option value="">Select option</option>
                            <option value="Water" <c:if test="${vendorEntity.getServiceType()=='Water'}"> selected="selected"</c:if>>Water</option>
                            <option value="Milk" <c:if test="${vendorEntity.getServiceType()=='Milk'}"> selected="selected"</c:if>>Milk</option>
                            <option value="Food" <c:if test="${vendorEntity.getServiceType()=='Food'}"> selected="selected"</c:if>>Food</option>
                            <option value="Internet" <c:if test="${vendorEntity.getServiceType()=='Internet'}"> selected="selected"</c:if>>Internet</option>
                            <option value="Laptop" <c:if test="${vendorEntity.getServiceType()=='Laptop'}"> selected="selected"</c:if>>Laptop</option>
                        </select>
                    </div>
                    <span id="serviceError" style="color: red;"></span>

                    <!-- CONTACT NUMBER -->
                    <div class="form-group mt-3">
                        <label for="contactNumber">Contect Number</label>
                        <input type="number" class="form-control" id="contactNumber" name="contactNumber"
                            value="${vendorEntity.getContactNumber()}" onblur="numberAjax()" />
                             <!-- [0-9]{3}-[0-9]{3}-[0-9]{4} -->
                    </div>
                    <span id="numberError" style="color: red;"></span>

                    <!-- ALTERNATE NUMBER -->
                    <div class="form-group mt-3">
                        <label for="alternateContactNumber">Alternate Contact Number</label>
                        <input type="number" class="form-control" id="alternateContactNumber"
                            name="alternateContactNumber" value="${vendorEntity.getAlternateContactNumber()}" />
                    </div>

                    <!-- EMAIL ADDRESS -->
                    <div class="form-group mt-3">
                        <label for="vendorEmail">Email address</label>
                        <input type="email" class="form-control" id="vendorEmail" name="vendorEmail"
                            value="${vendorEntity.getVendorEmail()}" onchange="uniqueMail()"
                            maxlength="30" minlength="5" required />
                    </div>
                    <span id="emailError" style="color: red;"></span>

                    <!-- WEBSITE -->
                    <div class="form-group mt-3">
                        <label for="website">Vendor Website</label>
                        <input type="text" class="form-control" id="website" name="website"
                            value="${vendorEntity.getWebsite()}" onchange="websiteAjax()" required />
                    </div>
                    <span id="websiteError" style="color: red;"></span>

                    <button type="submit" class="btn btn-primary form-control mt-3" id="registerButton">Register</button>
                </form>
            </div>

            <footer class="bg-dark py-1 mt-5 footer-1">
                <div class="container text-light text-center">
                    <p class="display-5 mb-3">Vendor Management</p>
                    <small class="text-white-50">&copy; Copyright by X-Workz. All
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
                    } else if(name.includes('  ')) {
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
                    } else if(location.includes('  ')) {
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
                        xhttp.open("GET","http://localhost:8080/vmanage/gstAjax/" + gst);
                        xhttp.send();

                        xhttp.onload = function() {
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
                    } else if (!gstRegex.test(gst)) {
                        document.getElementById("gstError").innerHTML = "gst should be in proper format.";
                        btn.setAttribute("disabled", "");
                    }
                }
                
                /*
                function gstAjax() {
                    console.log("runnig gst ajax.");
                    const gst = document.getElementById("vendorGSTNumber").value;
                    const btn = document/getElementById("registerButton");

                    console.log(gst);
                    if (gst == null || gst == "") {
                        document.getElementById("gstError").innerHTML = "*gst can't be blank";
                        btn.setAttribute("disabled", "");
                    } else if (gst.includes('  ')) {
                        console.log("in-valid gst number.")
                        document.getElementById("gstError").innerHTML = "*gst can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if (gst.length != 15) {
                        document.getElementById("gstError").innerHTML = "*gst should be 15 characters.";
                        btn.setAttribute("disabled", "");
                    } else {
                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET","http://localhost:8080/vmanage/gstAjax/" + gst);
                        xhttp.send();

                        xhttp.onload = function() {
                            document.getElementById("gstError").innerHTML = this.responseText;
                        }

                        document.getElementById("gstError").innerHTML = "";
                        btn.removeAttribute("disabled");
                    }
                }
                */


                // owner name validate
                function validateOwnerName() {
                    let owner = document.getElementById("ownerName").value;
                    let btn = document.getElementById("registerButton");

                    if (owner == null || owner == "") {
                        document.getElementById("ownerError").innerHTML = "*owner name can't be blank.";
                        btn.setAttribute("disabled", "");
                    } else if(owner.includes('  ')) {
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


                // contact number validation ajax
                function numberAjax(){
                    const mobile = document.getElementById("contactNumber").value;
                    const btn = document.getElementById("registerButton");
                    var mobileNumberRegex = /^[6-9]{1}[0-9]{9}$/;

                    if (mobile != "" && mobile.length == 10) {
                        console.log(mobile);
                        document.getElementById("numberError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET","http://localhost:8080/vmanage/mobileAjax/" + mobile);
                        xhttp.send();

                        xhttp.onload = function() {
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
                function uniqueMail(){
                    var gmail = document.getElementById("vendorEmail").value;
                    const btn = document.getElementById("registerButton");
                    const regex = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

                    if (gmail != null && gmail != "" && gmail.length > 5 && gmail.length < 30) {
                        console.log("email is valid.");
                        document.getElementById("emailError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8080/vmanage/emailAjax/" + gmail);
                        xhttp.send();

                        xhttp.onload = function() {
                            document.getElementById("emailError").innerHTML = this.responseText;
                        }

                        document.getElementById("emailError").innerHTML = "";                        
                        btn.removeAttribute("disabled");

                    } else if(gmail == null || gmail == "" || gmail.includes("  ")) {
                        document.getElementById("emailError").innerHTML = "*email can't be blank.";
                        btn.setAttribute("disabled", "");
                    }  else if (gmail.length < 5 || gmail.length > 30) {
                        document.getElementById("emailError").innerHTML = "*email should be in 5-30 character.";
                        btn.setAttribute("disabled", "");
                    } else if (!regex.test(gmail)){
                        document.getElementById("emailError").innerHTML = "*email should be in format.";
                        btn.setAttribute("disabled", "");
                    }
                }

                

                
            
                // website ajax
                function websiteAjax() {
                    console.log("running website ajax.")
                    const site = document.getElementById("website").value;
                    console.log(site);
                    if (site.length != null && site.length != "" && site.length > 10 && site.length <30) {
                        document.getElementById("websiteError").innerHTML = "";

                        const xhttp = new XMLHttpRequest();
                        xhttp.open("GET", "http://localhost:8080/vmanage/siteAjax/" + site);
                        xhttp.send();
                        xhttp.onload = function() {
                            document.getElementById("websiteError").innerHTML = this.responseText;
                        }
                    } else {
                        console.log("invalid website.")
                        document.getElementById("websiteError").innerHTML = "website is invalid.";
                    }
                }
            </script>
        </body>

        </html>