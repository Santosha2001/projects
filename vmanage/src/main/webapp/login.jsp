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
                </div>
            </div>
        </nav>

        <div class="container d-flex justify-content-center p-3 border  border-dark mt-2 mb-3">
            <form action="signin" method="post">

                <h2 style="text-align: center; color: rgb(42, 43, 43); font-style: italic;">Log In</h2>
                <!-- EMAIL ADDRESS FOR LOG IN-->
                <div class="form-group mt-4">
                    <input type="email" class="form-control" id="vendorEmail" name="vendorEmail"
                        placeholder="Email address" value="${entity.getVendorEmail()}" onchange="loginMail()"
                        required />
                </div>
                <span id="emailError" style="color: red;"></span>

                <!-- OTP FOR LOG IN-->
                <div class="form-group mt-3">
                    <input type="number" class="form-control" id="vendorEmail" name="vendorEmail"
                        placeholder="Enter OTP" value="${entity.getVendorEmail()}" onchange="uniqueMail()" required />
                </div>
                <span id="emailError" style="color: red;"></span>

                <!-- <button type="submit" class="btn btn-primary form-control mt-3" id="registerButton">Register</button> -->

                <button type="button" class="btn btn-primary btn-sm mt-4">Generate OTP</button>
                <button type="button" class="btn btn-secondary btn-sm mt-4">Log In</button>

            </form>
        </div>

        <script>
            function loginMail() {
                console.log("runnig email in login.")
                const mail = document.getElementById("vendorEmail").value;
                console.log(mail);

                if (mail != null && mail != "" && mail.length > 5 && mail.length < 30) {
                    console.log("email exist.")
                    document.getElementById("emailError").innerHTML = "";

                    const xhtp = new XMLHttpRequest();
                    xhtp.open("GET", "http://localhost:8080/vmanage/mailLogInAjax/" + mail);

                    xhtp.send();

                    xhtp.onload = function () {
                        document.getElementById("emailError").innerHTML = this.responseText;
                    }
                } else {
                    console.log("mail not registered.");
                    document.getElementById("emailError").innerHTML = "mail not registered.";
                }
            }
        </script>
    </body>

    </html>