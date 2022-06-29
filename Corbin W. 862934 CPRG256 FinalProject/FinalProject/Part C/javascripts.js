var xhr = new XMLHttpRequest();
var r;
window.onload = loaddata;
var obj;

function loaddata() {

    document.getElementById("lastname").disabled = true;
    document.getElementById("firstname").disabled = true;
    document.getElementById("saddress").disabled = true;
    document.getElementById("state").disabled = true;
    document.getElementById("eaddress").disabled = true;
    document.getElementById("phone").disabled = true;
    document.getElementById("eaddress").disabled = true;
    document.getElementById("rentaldays").disabled = true;
    document.getElementById("compact").disabled = true;
    document.getElementById("midsize").disabled = true;
    document.getElementById("luxury").disabled = true;
    document.getElementById("vantruck").disabled = true;
    document.getElementById("rack").disabled = true;
    document.getElementById("gps").disabled = true;
    document.getElementById("childseat").disabled = true;

    document.getElementById("sb").disabled = true;
    document.getElementById("rb").disabled = true;


    
    document.getElementById("searchfield").addEventListener("keyup", function () { searchLastName(this.value); }, false);

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            r = JSON.parse(xhr.responseText);
        }
    };
    xhr.open("GET", "rentalclients.json", true);
    xhr.send();
}

function searchLastName(userInput) {

    var output = "<tr><th>Last Name</th><th>First Name</th><th>Rental Details</th></tr>";
    var lastNameFromUser;
    for (var i = 0; i < r.length; i++) {
        obj = r[i];
        lastNameFromUser = obj.last_name;

        var lastName = obj.last_name;
        var firstName = obj.first_name;
        var streetAddress = obj.address;
        var stateProvince = obj.state_prov;
        var emailAddress = obj.email;
        var contactPhone = obj.phone;

        if (lastNameFromUser.startsWith(userInput)) {

            output += "<tr><td>"
            output += obj.last_name
            output += "</td><td>"
            output += obj.first_name
            output += "</td><td>"
            output += "<input type=button id=" + i + " value=Select onclick=selectButton(this.id)>"
            output += "</td></tr>";

        }
    }
    document.getElementById("t2").innerHTML = output;
}

function selectButton(id) {

    document.getElementById("lastname").disabled = false;
    document.getElementById("firstname").disabled = false;
    document.getElementById("saddress").disabled = false;
    document.getElementById("state").disabled = false;
    document.getElementById("eaddress").disabled = false;
    document.getElementById("phone").disabled = false;
    document.getElementById("rentaldays").disabled = false;
    document.getElementById("compact").disabled = false;
    document.getElementById("midsize").disabled = false;
    document.getElementById("luxury").disabled = false;
    document.getElementById("vantruck").disabled = false;
    document.getElementById("rack").disabled = false;
    document.getElementById("gps").disabled = false;
    document.getElementById("childseat").disabled = false;

    document.getElementById("sb").disabled = false;
    document.getElementById("rb").disabled = false;

    document.getElementById("lastname").value = r[id].last_name;
    document.getElementById("firstname").value = r[id].first_name;
    document.getElementById("saddress").value = r[id].address;
    document.getElementById("state").value = r[id].state_prov;
    document.getElementById("eaddress").value = r[id].email;
    document.getElementById("phone").value = r[id].phone;
}

function calculate() {
    var cusLastName = document.getElementById("lastname").value;
    var cusFirstName = document.getElementById("firstname").value;
    var cusAddress = document.getElementById("saddress").value;
    var cusState = document.getElementById("state").value;
    var cusEmail = document.getElementById("eaddress").value;
    var cusPhone = document.getElementById("phone").value;
    var cusRentalDays = document.getElementById("rentaldays").value;
    var cusVehicleType = "";
    var cusVehicleExtras = "";

    var total = 0;

    if (document.getElementById("compact").checked == true) {
        total += (cusRentalDays * 15.00);
        cusVehicleType = "Compact";
    }
    else if (document.getElementById("midsize").checked == true) {
        total += (cusRentalDays * 20.00);
        cusVehicleType = "Mid-size";
    }
    else if (document.getElementById("luxury").checked == true) {
        total += (cusRentalDays * 35.00);
        cusVehicleType = "Luxury";
    }
    else if (document.getElementById("vantruck").checked == true) {
        total += (cusRentalDays * 40.00);
        cusVehicleType = "Van/Truck";
    }

    if (document.getElementById("rack").checked == true) {
        total += (cusRentalDays * 5.00);
        cusVehicleExtras +=
            "\n"
            + "Roof/Bicycle Rack";
    }
    if (document.getElementById("gps").checked == true) {
        total += (cusRentalDays * 10.00);
        cusVehicleExtras +=
            "\n"
            + "GPS";
    }
    if (document.getElementById("childseat").checked == true) {
        cusVehicleExtras +=
            "\n"
            + "Childseat";
    }

    alert(cusFirstName + " " + cusLastName
        + "\n"
        + cusAddress + " " + cusState
        + "\n"
        + cusEmail
        + "\n"
        + cusPhone
        + "\n"
        + "\n"
        + "Your Rental:"
        + "\n"
        + "\n"
        + cusVehicleType + " vehicle"
        + "\n"
        + "For: " + cusRentalDays + " days"
        + "\n"
        + "Extras: " + cusVehicleExtras
        + "\n"
        + "\n"
        + "Total: $" + total.toFixed(2));
}