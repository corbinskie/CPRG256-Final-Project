var xhr = new XMLHttpRequest();
var r = r || [];
window.onload = tcloaddata;

function tcloaddata() {

    document.getElementById("Quadrant").addEventListener("keyup", function () { searchQuadrant(this.value); }, false);
    document.getElementById("Description").addEventListener("keyup", function () { searchDescription(this.value); }, false);
    document.getElementById("Location").addEventListener("keyup", function () { searchLocation(this.value); }, false);
    document.getElementById("Type").addEventListener("keyup", function () { searchType(this.value); }, false);

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            r = JSON.parse(xhr.responseText);
        }
    };
    xhr.open("GET", "https://data.calgary.ca/resource/k7p9-kppz.json", true);
    xhr.send();
}

function searchQuadrant(userInput) {

    var output = "<tr><th>Description</th><th>Quadrant</th><th>Location</th><th>Longitude</th><th>Latitude</th><th>Type</th><th>Google Maps Link</th></tr>";
    var quadrantFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.point.coordinates[0];
        var lati = obj.point.coordinates[1];
        quadrantFromUser = obj.quadrant;
        if (quadrantFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.camera_url.description
            output += "</td><td>"
            output += obj.quadrant
            output += "</td><td>"
            output += obj.camera_location
            output += "</td><td>"
            output += obj.point.coordinates[0] //long
            output += "</td><td>"
            output += obj.point.coordinates[1] //lat
            output += "</td><td>"
            output += obj.point.type
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }
    }
    document.getElementById("tct2").innerHTML = output;
}

function searchDescription(userInput) {

    var output = "<tr><th>Description</th><th>Quadrant</th><th>Location</th><th>Longitude</th><th>Latitude</th><th>Type</th><th>Google Maps Link</th></tr>";
    var descriptionFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.point.coordinates[0];
        var lati = obj.point.coordinates[1];
        descriptionFromUser = obj.camera_url.description;
        if (descriptionFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.camera_url.description
            output += "</td><td>"
            output += obj.quadrant
            output += "</td><td>"
            output += obj.camera_location
            output += "</td><td>"
            output += obj.point.coordinates[0] //long
            output += "</td><td>"
            output += obj.point.coordinates[1] //lat
            output += "</td><td>"
            output += obj.point.type
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tct2").innerHTML = output;
}

function searchLocation(userInput) {

    var output = "<tr><th>Description</th><th>Quadrant</th><th>Location</th><th>Longitude</th><th>Latitude</th><th>Type</th><th>Google Maps Link</th></tr>";
    var locationFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.point.coordinates[0];
        var lati = obj.point.coordinates[1];
        locationFromUser = obj.camera_location;
        if (locationFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.camera_url.description
            output += "</td><td>"
            output += obj.quadrant
            output += "</td><td>"
            output += obj.camera_location
            output += "</td><td>"
            output += obj.point.coordinates[0] //long
            output += "</td><td>"
            output += obj.point.coordinates[1] //lat
            output += "</td><td>"
            output += obj.point.type
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tct2").innerHTML = output;
}

function searchType(userInput) {

    var output = "<tr><th>Description</th><th>Quadrant</th><th>Location</th><th>Longitude</th><th>Latitude</th><th>Type</th><th>Google Maps Link</th></tr>";
    var typeFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.point.coordinates[0];
        var lati = obj.point.coordinates[1];
        typeFromUser = obj.point.type;
        if (typeFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.camera_url.description
            output += "</td><td>"
            output += obj.quadrant
            output += "</td><td>"
            output += obj.camera_location
            output += "</td><td>"
            output += obj.point.coordinates[0] //long
            output += "</td><td>"
            output += obj.point.coordinates[1] //lat
            output += "</td><td>"
            output += obj.point.type
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("tct2").innerHTML = output;
}