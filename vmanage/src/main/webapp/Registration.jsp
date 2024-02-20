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

            <div class="container d-flex justify-content-center p-3 border  border-dark mt-2 mb-3">
                <form action="register" method="post">
                    <!-- VENDOR NAME -->
                    <div class="form-group mt-1">
                        <label for="vendorNname">Vendor name</label>
                        <input type="text" class="form-control" id="vendorNname" name="vendorNname"
                            placeholder="" />
                    </div>

                    <!-- VENDOR LOCATION -->
                    <div class="form-group mt-3">
                        <label for="vendorLocation">Vendor Location</label>
                        <input type="text" class="form-control" id="vendorLocation" name="vendorLocation"
                            placeholder="" />
                    </div>

                    <!-- GST NUMBER -->
                    <div class="form-group mt-3">
                        <label for="vendorGSTNumber">GST Number</label>
                        <input type="text" class="form-control" id="vendorGSTNumber" name="vendorGSTNumber"
                            placeholder="" />
                    </div>

                    <!-- COMPANY START DATE -->
                    <div class="form-group mt-3">
                        <label for="companyStartDate">Company Start Date</label>
                        <input type="date" class="form-control" id="companyStartDate" name="companyStartDate"
                            placeholder="" />
                    </div>

                    <!-- OWNER NAME -->
                    <div class="form-group mt-3">
                        <label for="ownerName">Company Owner Name</label>
                        <input type="text" class="form-control" id="ownerName" name="ownerName"
                            placeholder="" />
                    </div>

                    <!-- SERVICE TYPE -->
                    <div class="form-group mt-3">
                        <label for="serviceType">Service Type</label>
                        <select class="form-select form-select-sm form-control" name="serviceType" id="serviceType">
                            <option selected>Select option</option>
                            <option value="Water">Water</option>
                            <option value="Milk">Milk</option>
                            <option value="Food">Food</option>
                            <option value="Internet">Internet</option>
                            <option value="Laptop">Laptop</option>
                        </select>
                    </div>

                    <!-- CONTACT NUMBER -->
                    <div class="form-group mt-3">
                        <label for="contactNumber">Contect Number</label>
                        <input type="number" class="form-control" id="contactNumber" name="contactNumber"
                            placeholder="" readonly />
                    </div>

                    <!-- ALTERNATE NUMBER -->
                    <div class="form-group mt-3">
                        <label for="alternateContactNumber">Alternate Contact Number</label>
                        <input type="number" class="form-control" id="alternateContactNumber"
                            name="alternateContactNumber" placeholder="" readonly />
                    </div>

                    <!-- EMAIL ADDRESS -->
                    <div class="form-group mt-3">
                        <label for="vendorEmail">Email address</label>
                        <input type="email" class="form-control" id="vendorEmail" name="vendorEmail"
                            placeholder="" />
                    </div>

                    <!-- WEBSITE -->
                    <div class="form-group mt-3">
                        <label for="website">Vendor Website</label>
                        <input type="text" class="form-control" id="website" name="website"
                            placeholder="" />
                    </div>

                    <button type="submit" class="btn btn-primary form-control mt-3">Register</button>
                </form>
            </div>

            <footer class="bg-dark py-1 mt-5 footer-1">
                <div class="container text-light text-center">
                    <p class="display-5 mb-3">Vendor Management</p>
                    <small class="text-white-50">&copy; Copyright by X-Workz. All
                        rights reserved.</small>
                </div>
            </footer>
        </body>

        </html>