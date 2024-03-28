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

        <style>
            * {
                margin: 0;
                padding: 0;
            }

            body {
                background-color: #93a4ae45;
            }

            .nav-link {
                color: #91eded;
            }

            .nav-link:hover {
                color: #dd7be4;
            }

            /* form outer border */
            .border {
                background-color: #c0eef2;
                border-radius: 20px;
                height: 59.7vh;
                /* width: 35vw; */
                width: 30vw;
                text-align: justify;
                /* align-items: center; */
            }

            /* form input */
            .form-control {
                width: 25vw;
                border-color: rgb(35, 34, 34);
            }

            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            .otpExpire {
                text-decoration: none;
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
                            <a class="nav-link" aria-current="page" href="index.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Registration.jsp">Register here</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Log In</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="AdminLogin.jsp">Admin LogIn</a>
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


        <span class="container d-flex justify-content-center" style="color:red">
            <h3>${otpNotInTime}</h3>
        </span>

        <span class="container d-flex justify-content-center" style="color:red">
            <h3>${unlockedAccount}</h3>
        </span>


        <div class="container d-flex justify-content-center p-3 border  border-dark mt-2 mb-3">
            <form action="otpVerify" method="post">
                <!-- action="${pageContext.request.contextPath}/otp/otpVerify?otp=${otp}&email=${email}" -->

                <h2 style="text-align: center; color: #29b03e; font-style: italic;">LOG IN</h2>

                <!-- EMAIL ADDRESS FOR LOG IN-->
                <div class="form-group mt-5">
                    <input type="email" class="form-control" id="vendorEmail" name="vendorEmail"
                        placeholder="Email address" value="${mail}" onblur="emailCheck()" />
                </div>
                <span id="emailError" style="color: red;"></span>

                <!-- VERIFY OTP -->
                <div class="form-group mt-3">
                    <input type="number" class="form-control" id="otp" name="otp" placeholder="Enter OTP"
                        onchange="otpVerify()" required />
                </div>
                <span class="container d-flex" id="otpError" style="color:red">${wrongOTP}</span>

                <button type="submit" class="btn form-control btn-primary btn-sm mt-4 mb-2" id="otpVerifyBtn">Log
                    In</button>

            </form>
        </div>

        <!-- footer -->
        <footer class="bg-dark py-1 mt-5 footer-1">
            <div class="container text-light text-center">
                <p class="display">Vendor Management</p>
                <small class="text-white-60">&copy; Copyright by X-Workz. All
                    rights reserved.</small>
            </div>
        </footer>

        <script>
            function emailCheck() {
                var gmail = document.getElementById("vendorEmail").value;
                const btn = document.getElementById("otpVerifyBtn");

                if (gmail != null && gmail != "") {
                    console.log("email is valid.");
                    document.getElementById("emailError").innerHTML = "";
                    btn.removeAttribute("disabled");

                } else if (gmail == null || gmail == "" || gmail.includes("  ")) {
                    document.getElementById("emailError").innerHTML = "*email can't be blank.";
                    btn.setAttribute("disabled", "");
                }
            }

            function otpVerify() {
                let otp = document.getElementById("otp").value;
                const btn = document.getElementById("otpVerifyBtn");

                if (otp != null && otp != "") {
                    document.getElementById("otpError").innerHTML = "";
                    btn.removeAttribute("disabled");
                } else if (otp == null || otp == "" || otp.includes(' ')) {
                    document.getElementById("otpError").innerHTML = "*otp can't empty.";
                    btn.setAttribute("disabled", "");
                }
            }
        </script>

    </body>

    </html>