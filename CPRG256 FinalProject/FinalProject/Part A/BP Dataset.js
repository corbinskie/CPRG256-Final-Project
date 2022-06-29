var xhr = new XMLHttpRequest();
var r = r || [];
window.onload = bploaddata;

function bploaddata() {

    document.getElementById("ID #").addEventListener("keyup", function () { searchID(this.value); }, false);
    document.getElementById("Date").addEventListener("keyup", function () { searchDate(this.value); }, false);
    document.getElementById("Type").addEventListener("keyup", function () { searchType(this.value); }, false);
    document.getElementById("Class").addEventListener("keyup", function () { searchClass(this.value); }, false);

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            r = JSON.parse(xhr.responseText);
        }
    };
    xhr.open("GET", "https://data.calgary.ca/resource/c2es-76ed.json", true);
    xhr.send();
}


function searchID(userInput) {

    var output = "<tr><th>Id #</th><th>Date</th><th>Type</th><th>Longitude</th><th>Latitude</th><th>Class</th><th>Google Maps Link</th></tr>";
    var idFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        idFromUser = obj.permitnum;
        if (idFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.permitnum
            output += "</td><td>"
            output += obj.applieddate
            output += "</td><td>"
            output += obj.permittype
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.workclass
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("bpt2").innerHTML = output;
}

function searchDate(userInput) {

    var output = "<tr><th>Id #</th><th>Date</th><th>Type</th><th>Longitude</th><th>Latitude</th><th>Class</th><th>Google Maps Link</th></tr>";
    var dateFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        dateFromUser = obj.applieddate;
        if (dateFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.permitnum
            output += "</td><td>"
            output += obj.applieddate
            output += "</td><td>"
            output += obj.permittype
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.workclass
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("bpt2").innerHTML = output;
}

function searchType(userInput) {

    var output = "<tr><th>Id #</th><th>Date</th><th>Type</th><th>Longitude</th><th>Latitude</th><th>Class</th><th>Google Maps Link</th></tr>";
    var typeFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        typeFromUser = obj.permittype;
        if (typeFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.permitnum
            output += "</td><td>"
            output += obj.applieddate
            output += "</td><td>"
            output += obj.permittype
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.workclass
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("bpt2").innerHTML = output;
}

function searchClass(userInput) {

    var output = "<tr><th>Id #</th><th>Date</th><th>Type</th><th>Longitude</th><th>Latitude</th><th>Class</th><th>Google Maps Link</th></tr>";
    var classFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        classFromUser = obj.workclass;
        if (classFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.permitnum
            output += "</td><td>"
            output += obj.applieddate
            output += "</td><td>"
            output += obj.permittype
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.workclass
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("bpt2").innerHTML = output;
}