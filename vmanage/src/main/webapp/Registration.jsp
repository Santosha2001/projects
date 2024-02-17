<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> -->
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Registration Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
            integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>


        <style type="text/css">
            form {
                max-width: 800px;
                margin: 10px auto;
                padding: 20px;
                border: 1px solid #ccc;
                background-color: #f1c2c2;
                border-radius: 5px;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }

            input[type="text"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #161515;
                border-radius: 3px;
            }

            input[type="submit"] {
                background-color: black;
                color: #fff;
                padding: 10px 15px;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: olive;
            }
        </style>

    </head>

    <body>
        <nav class="navbar navbar-expand-lg bg-secondary">
            <div class="container-fluid justify-content-center">
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
                            <a class="nav-link" href="#">Register Here</a>
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
        <span style="color:red"><h3>${uniqueError}</h3></span>

        <form action="register" method="post">
            <div class="row">
                <div class="col">
                    <input type="text" class="form-control" name="vendorNname" id="vendorNname"
                        placeholder="Vendor Nname" value="" />
                    <span id="nameError" style="color: red"></span><br>
                </div>
                <div class="col">
                    <input type="text" class="form-control" name="vendorLocation" id="vendorLocation"
                        placeholder="Vendor Location" value="" />
                    <span id="nameError" style="color: red"></span><br>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <input type="text" class="form-control" name="vendorGSTNumber" id="vendorGSTNumber"
                        placeholder="Vendor GST Number" value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
                <div class="col">
                    <input type="date" class="form-control" name="companyStartDate" id="companyStartDate" value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <input type="text" class="form-control" name="ownerName" id="ownerName" placeholder="Owner Name"
                        value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
                <div class="col">
                    <select name="serviceType" class="form-control" id="serviceType">
                        <option value="">--Select Service Type--</option>
                        <option value="Water">Water</option>
                        <option value="Internet">Internet</option>
                        <option value="Food">Food</option>
                        <option value="Loptop">Loptop</option>
                    </select>
                    <span id="fieldError" style="color: red"></span><br>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <input type="number" class="form-control" name="contactNumber" id="contactNumber"
                        placeholder="Contact Number" value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
                <div class="col">
                    <input type="number" class="form-control" name="alternateContactNumber" id="alternateContactNumber"
                        placeholder="Alter Number" value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <input type="email" class="form-control" name="vendorEmail" id="vendorEmail"
                        placeholder="Vendor Email" value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
                <div class="col">
                    <input type="text" class="form-control" name="website" id="website" placeholder="Website"
                        value="" />
                    <span id="expeError" style="color: red"></span><br>
                </div>
            </div>


            <div class="row">
                <div class="col">
                    <button type="reset" class="form-control text-bg-secondary p-2">Reset</button>
                </div>
                <div class="col">
                    <button type="submit" class="form-control text-bg-secondary p-2" id="submit">Submit</button>
                </div>
            </div>
        </form>

        <footer class="bg-dark py-1 mt-5 footer-1">
            <div class="container text-light text-center">
                <p class="display-5 mb-3">Vendor Management</p>
                <small class="text-white-50">&copy; Copyright by X-Workz. All
                    rights reserved.</small>
            </div>
        </footer>
    </body>

    </html>