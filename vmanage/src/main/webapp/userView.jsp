<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
                width: 35vw;
            }

            /* form input */
            .form-control {
                width: 30vw;
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
                            <a class="nav-link" href="Registration.jsp">Register here</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Log In</a>
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
                    <!-- <span class="btn btn-outline-success" type="">${entity.getVendorNname()}</span> -->
                    <!-- <ul class="d-flex">
                        <li class="nav-item btn btn-outline-success">
                            <a class="nav-link" href="#">${entity.getVendorNname()}</a>
                        </li>
                    </ul> -->
                    <form class="d-flex">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                      </form>
                </div>
            </div>
        </nav>

        <span class="container d-flex justify-content-center" style="color: green;">${otpMatched}</span>

        <!-- footer -->
        <footer class="bg-dark py-1 mt-5 footer-1 fixed-bottom">
            <div class="container text-light text-center">
                <p class="display-5 mb-3">Vendor Management</p>
                <small class="text-white-50">&copy; Copyright by X-Workz. All
                    rights reserved.</small>
            </div>
        </footer>


    </body>

    </html>