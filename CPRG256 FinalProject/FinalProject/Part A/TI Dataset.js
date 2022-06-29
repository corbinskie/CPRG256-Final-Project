var xhr = new XMLHttpRequest();
var r = r || [];
window.onload = tiloaddata;

function tiloaddata() {

    document.getElementById("Incident Info").addEventListener("keyup", function () { searchIncidentInfo(this.value); }, false);
    document.getElementById("Description").addEventListener("keyup", function () { searchDescription(this.value); }, false);
    document.getElementById("Date").addEventListener("keyup", function () { searchDate(this.value); }, false);
    document.getElementById("ID #").addEventListener("keyup", function () { searchID(this.value); }, false);

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            r = JSON.parse(xhr.responseText);
        }
    };
    xhr.open("GET", "https://data.calgary.ca/resource/35ra-9556.json", true);
    xhr.send();
}


function searchIncidentInfo(userInput) {

    var output = "<tr><th>Incident Info</th><th>Description</th><th>Date</th><th>Longitude</th><th>Latitude</th><th>ID #</th><th>Google Maps Link</th></tr>";
    var incidentInfoFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        incidentInfoFromUser = obj.incident_info;
        if (incidentInfoFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.incident_info
            output += "</td><td>"
            output += obj.description
            output += "</td><td>"
            output += obj.start_dt
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.id
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tit2").innerHTML = output;
}

function searchDescription(userInput) {

    var output = "<tr><th>Incident Info</th><th>Description</th><th>Date</th><th>Longitude</th><th>Latitude</th><th>ID #</th><th>Google Maps Link</th></tr>";
    var descriptionFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        descriptionFromUser = obj.description;
        if (descriptionFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.incident_info
            output += "</td><td>"
            output += obj.description
            output += "</td><td>"
            output += obj.start_dt
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.id
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tit2").innerHTML = output;
}

function searchDate(userInput) {

    var output = "<tr><th>Incident Info</th><th>Description</th><th>Date</th><th>Longitude</th><th>Latitude</th><th>ID #</th><th>Google Maps Link</th></tr>";
    var dateFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        dateFromUser = obj.start_dt;
        if (dateFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.incident_info
            output += "</td><td>"
            output += obj.description
            output += "</td><td>"
            output += obj.start_dt
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.id
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tit2").innerHTML = output;
}

function searchID(userInput) {

    var output = "<tr><th>Incident Info</th><th>Description</th><th>Date</th><th>Longitude</th><th>Latitude</th><th>ID #</th><th>Google Maps Link</th></tr>";
    var idFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.longitude;
        var lati = obj.latitude;
        idFromUser = obj.id;
        if (idFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.incident_info
            output += "</td><td>"
            output += obj.description
            output += "</td><td>"
            output += obj.start_dt
            output += "</td><td>"
            output += obj.longitude
            output += "</td><td>"
            output += obj.latitude
            output += "</td><td>"
            output += obj.id
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tit2").innerHTML = output;
}
