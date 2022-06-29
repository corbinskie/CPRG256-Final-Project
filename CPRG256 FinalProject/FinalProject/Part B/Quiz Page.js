function readXML() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            processXML(xhr);
        }
    };
    xhr.open("GET", "FinalQuiz.xml", true);
    xhr.send();
}

function processXML(xhr) {
    var i;
    var xmldoc = xhr.responseXML;
    var table = "<tr><th>#</th><th>Question</th></tr>";
    var x = xmldoc.getElementsByTagName("question");
    for (i = 0; i < x.length; i++) {
        table += "<tr><td>" +
            x[i].getElementsByTagName("qnumber")[0].childNodes[0].nodeValue +
            "</td><td>" +
            x[i].getElementsByTagName("qtitle")[0].childNodes[0].nodeValue +
            "</td><td>" +
            "<input type=radio id=choicea" + i + " name=choice" + i + ">" + "A: " + x[i].getElementsByTagName("a")[0].childNodes[0].nodeValue +
            "</td><td>" +
            "<input type=radio id=choiceb" + i + " name=choice" + i + ">" + "B: " + x[i].getElementsByTagName("b")[0].childNodes[0].nodeValue +
            "</td><td>" +
            "<input type=radio id=choicec" + i + " name=choice" + i + ">" + "C: " + x[i].getElementsByTagName("c")[0].childNodes[0].nodeValue +
            "</td><td>" +
            "<input type=radio id=choiced" + i + " name=choice" + i + ">" + "D: " + x[i].getElementsByTagName("d")[0].childNodes[0].nodeValue +
            "</td></tr>";
    }
    document.getElementById("parsedxml").innerHTML = table;
}

function calculateAnswers() {
    var correctAnswers = 0;

    if (document.getElementById("choiceb0").checked == true) {
        correctAnswers++;
    }
    if (document.getElementById("choicea1").checked == true) {
        correctAnswers++;
    }
    if (document.getElementById("choiced2").checked == true) {
        correctAnswers++;
    }
    if (document.getElementById("choicea3").checked == true) {
        correctAnswers++;
    }
    if (document.getElementById("choicec4").checked == true) {
        correctAnswers++;
    }

    alert("You scored " + correctAnswers + "/5!");

}