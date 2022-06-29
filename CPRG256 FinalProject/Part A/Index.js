var xhr = new XMLHttpRequest();

function loadDataset() {

    clearData();
    
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            document.getElementById("displaySearch").innerHTML = xhr.responseText;
        }
    };

    if (document.getElementById("tiRadio").checked == true) {
        setTimeout(tiloaddata, 1000);
        xhr.open("GET", "TI Dataset.html", true);
        xhr.send();
    }
    else if (document.getElementById("tcRadio").checked == true) {
        setTimeout(tcloaddata, 1000);
        xhr.open("GET", "TC Dataset.html", true);
        xhr.send();
    }
    else if (document.getElementById("csRadio").checked == true) {
        setTimeout(csloaddata, 1000);
        xhr.open("GET", "CS Dataset.html", true);
        xhr.send();
    }
    else if (document.getElementById("bpRadio").checked == true) {
        setTimeout(bploaddata, 1000);
        xhr.open("GET", "BP Dataset.html", true);
        xhr.send();
    }
}

function clearData() {
    document.getElementById("bpt2").innerHTML = "";
    document.getElementById("cst2").innerHTML = "";
    document.getElementById("tct2").innerHTML = "";
    document.getElementById("tit2").innerHTML = "";
}
