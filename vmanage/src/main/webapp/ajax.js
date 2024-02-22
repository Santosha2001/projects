function uniqueMail() {
    console.log("running uniqueMail");
    var gmail = document.getElementById("vendorEmail").value;
    console.log(gmail);

    if (gmail != null && gmail != "" && gmail.length > 5 && gmail.length < 30) {
        console.log("email is valid.");
        document.getElementById("emailError").innerHTML = "";

        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/vmanage/emailAjax/" + gmail);
        xhttp.send();

        xhttp.onload = function () {
            document.getElementById("emailError").innerHTML = this.responseText;
        }
    } else {
        console.log("in-valid email");
        document.getElementById("emailError").innerHTML = "Please enter valid email.";
    }
}

// gst ajax
function gstAjax() {
    console.log("runnig gst ajax.");
    const gst = document.getElementById("vendorGSTNumber").value;
    console.log(gst);
    if (gst != null && gst != "" && gst.length == 15) {
        console.log(gst)
        document.getElementById("gstError").innerHTML = "";

        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/vmanage/gstAjax/" + gst);
        xhttp.send();

        xhttp.onload = function () {
            document.getElementById("gstError").innerHTML = this.responseText;
        }
    } else {
        console.log("in-valid gst number.")
        document.getElementById("gstError").innerHTML = "Please enter valid gst.";
    }
}

// contact number ajax
function numberAjax() {
    const mobile = document.getElementById("contactNumber").value;
    console.log(mobile);
    if (mobile != "" && mobile.length == 10) {
        console.log(mobile);
        document.getElementById("numberError").innerHTML = "";

        const xhttp = new XMLHttpRequest();
        xhttp.open("GET", "http://localhost:8080/vmanage/mobileAjax/" + mobile);
        xhttp.send();

        xhttp.onload = function () {
            document.getElementById("numberError").innerHTML = this.responseText;
        }
    } else {
        console.log("in-valid mobile number.")
        document.getElementById("numberError").innerHTML = "Enter valid number.";
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
        xhttp.open("GET", "http://localhost:8080/vmanage/siteAjax/" + site);
        xhttp.send();
        xhttp.onload = function () {
            document.getElementById("websiteError").innerHTML = this.responseText;
        }
    } else {
        console.log("invalid website.")
        document.getElementById("websiteError").innerHTML = "website is invalid.";
    }
}