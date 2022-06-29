var xhr = new XMLHttpRequest();
var r = r || [];
window.onload = csloaddata;

function csloaddata() {

    document.getElementById("Sector").addEventListener("keyup", function () { searchSector(this.value); }, false);
    document.getElementById("Community").addEventListener("keyup", function () { searchCommunity(this.value); }, false);
    document.getElementById("Category").addEventListener("keyup", function () { searchCategory(this.value); }, false);
    document.getElementById("Date").addEventListener("keyup", function () { searchDate(this.value); }, false);

    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            r = JSON.parse(xhr.responseText);
        }
    };
    xhr.open("GET", "https://data.calgary.ca/resource/848s-4m4z.json", true);
    xhr.send();
}

function searchSector(userInput) {

    var output = "<tr><th>Sector</th><th>Community</th><th>Category</th><th>Longitude</th><th>Latitude</th><th>Date</th><th>Google Maps Link</th></tr>";
    var sectorFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.geocoded_column.longitude;
        var lati = obj.geocoded_column.latitude;
        sectorFromUser = obj.sector;
        if (sectorFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.sector
            output += "</td><td>"
            output += obj.community_name
            output += "</td><td>"
            output += obj.group_category
            output += "</td><td>"
            output += obj.geocoded_column.longitude
            output += "</td><td>"
            output += obj.geocoded_column.latitude
            output += "</td><td>"
            output += obj.date
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("cst2").innerHTML = output;
}

function searchCommunity(userInput) {

    var output = "<tr><th>Sector</th><th>Community</th><th>Category</th><th>Longitude</th><th>Latitude</th><th>Date</th><th>Google Maps Link</th></tr>";
    var communityFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.geocoded_column.longitude;
        var lati = obj.geocoded_column.latitude;
        communityFromUser = obj.community_name;
        if (communityFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.sector
            output += "</td><td>"
            output += obj.community_name
            output += "</td><td>"
            output += obj.group_category
            output += "</td><td>"
            output += obj.geocoded_column.longitude
            output += "</td><td>"
            output += obj.geocoded_column.latitude
            output += "</td><td>"
            output += obj.date
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("cst2").innerHTML = output;
}

function searchCategory(userInput) {

    var output = "<tr><th>Sector</th><th>Community</th><th>Category</th><th>Longitude</th><th>Latitude</th><th>Date</th><th>Google Maps Link</th></tr>";
    var groupFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.geocoded_column.longitude;
        var lati = obj.geocoded_column.latitude;
        groupFromUser = obj.group_category;
        if (groupFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.sector
            output += "</td><td>"
            output += obj.community_name
            output += "</td><td>"
            output += obj.group_category
            output += "</td><td>"
            output += obj.geocoded_column.longitude
            output += "</td><td>"
            output += obj.geocoded_column.latitude
            output += "</td><td>"
            output += obj.date
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("cst2").innerHTML = output;
}

function searchDate(userInput) {

    var output = "<tr><th>Sector</th><th>Community</th><th>Category</th><th>Longitude</th><th>Latitude</th><th>Date</th><th>Google Maps Link</th></tr>";
    var dateFromUser;
    for (var i = 0; i < r.length; i++) {
        var obj = r[i];
        var longi = obj.geocoded_column.longitude;
        var lati = obj.geocoded_column.latitude;
        dateFromUser = obj.date;
        if (dateFromUser.includes(userInput)) {

            output += "<tr><td>"
            output += obj.sector
            output += "</td><td>"
            output += obj.community_name
            output += "</td><td>"
            output += obj.group_category
            output += "</td><td>"
            output += obj.geocoded_column.longitude
            output += "</td><td>"
            output += obj.geocoded_column.latitude
            output += "</td><td>"
            output += obj.date
            output += "</td><td>"
            output += "<a target=_blank href = https://maps.google.com/?q=" + lati + "," + longi + ">Click Here!</a>"
            output += "</td></tr>";
        }

    }
    document.getElementById("cst2").innerHTML = output;
}

