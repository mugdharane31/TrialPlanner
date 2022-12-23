var btnLogin = document.getElementById('do-login');


window.onload = function() {
    var path = window.location.pathname;


    var arr = path.split("/");

    if (arr[1] === "survey") {
        getQuestionaire(arr[2], arr[3]);
    } else {
        populateSurveys();
        getRespondentDetails();
    }
};

function showSurveyRespondents() {
    var e = document.getElementById("surveys1");
    var surveyId = e.value;
    if (surveyId === "") {

    } else {
        var showResponsesurl = "http://localhost:8080/admin/getsurveyrespondents?surveyId=" + surveyId;

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", showResponsesurl, true);
        xmlhttp.setRequestHeader('Accept', '*/*');
        xmlhttp.setRequestHeader("Content-Type", "application/json");

        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                var responsedetails = JSON.parse(xmlhttp.responseText);
                var tbltop = `<table id="respondents">
				<tr>
					<th>Respondent Name</th>
					<th>Email</th>
					<th>Response</th>
					
				</tr>`;

                var main = "";
                for (i = 0; i < responsedetails.length; i++) {

                    main += "<tr><td>" + responsedetails[i].firstName + " " + responsedetails[i].lastName +
                        "</td><td>" + responsedetails[i].email +

                        "</td><td id='" + responsedetails[i].id +
                        "' onclick='openPopup(" + '"' + responsedetails[i].id + '"' + ", " + '"' + surveyId + '"' +
                        ")'><div class='primary-button'><button class='tablinks' >Show Responses</button><div>" +
                        "</td></tr>";

                }
                var tblbottom = "</table>";
                var tbl = tbltop + main + tblbottom;
                document.getElementById("responsedetails").innerHTML = tbl;
            }
        }
        xmlhttp.send();
    }
};

function openPopup(userId, surveyId) {

    var showresponsesurl = "http://localhost:8080/admin/showresponses?surveyId=" + surveyId + "&userId=" + userId;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", showresponsesurl, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var responsedetails = JSON.parse(xmlhttp.responseText);
            var tbltop = `<table id="userresponsestbl">
			<tr>
				<th>Questions</th>
				<th>Response</th>
				
			</tr>`;

            var main = "";
            for (i = 0; i < responsedetails.length; i++) {

                main += "<tr><td>" + responsedetails[i].question +
                    "</td><td>" + "<label>" + responsedetails[i].response + "</label>" +
                    "</td></tr>";

            }
            var tblbottom = "</table>";
            var tbl = tbltop + main + tblbottom;
            document.getElementById("userresponses").innerHTML = tbl;
            document.getElementById("userresponses").style.display = "none";
            var popup = window.open("", "", "width=640,height=480,resizeable,scrollbars"),
                table = document.getElementById("userresponsestbl");
            popup.document.write(table.outerHTML);
            popup.document.close();
            if (window.focus)
                popup.focus();
        }
    }
    xmlhttp.send();
};

function sendEmail(id, email, firstName) {
    var e = document.getElementById("surveys");
    var surveyId = e.value;
    //var text = e.options[e.selectedIndex].text;
    var sendEmailurl = "http://localhost:8080/user/sendemail?surveyId=" + surveyId;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", sendEmailurl, true);
    xmlhttp.setRequestHeader('Accept', '*/*');
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    var jsonObj = {
        "id": id,
        "email": email,
        "firstName": firstName
    }
    var data = JSON.stringify(jsonObj);

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText === "true") {
                alert("Email sent successfully");
            }

        }
    };
    xmlhttp.send(data);
};

function populateSurveys() {
    var getsurveyurl = "http://localhost:8080/admin/getsurveys";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", getsurveyurl, true);
    var surveydetails;
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            surveydetails = JSON.parse(xmlhttp.responseText);
            let ele = document.getElementById('surveys');
            let ele1 = document.getElementById('surveys1');

            for (let i = 0; i < surveydetails.length; i++) {
                // POPULATE SELECT ELEMENT WITH JSON.
                ele.innerHTML = ele.innerHTML +
                    '<option value="' + surveydetails[i]['surveyId'] + '">' + surveydetails[i]['surveyName'] + '</option>';
                ele1.innerHTML = ele1.innerHTML +
                    '<option value="' + surveydetails[i]['surveyId'] + '">' + surveydetails[i]['surveyName'] + '</option>';
            }

        }
    };
    xmlhttp.send();
};

function getQuestionaire(userId, surveyId) {

    var getQuestionairesurl = "http://localhost:8080/user/getquestionaire?surveyId=" + surveyId + "&userId=" + userId;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", getQuestionairesurl, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var questionairedetails = JSON.parse(xmlhttp.responseText);
            var tbltop = `<table id="respondents">
			<tr>
				<th>Questions</th>
				<th>Response</th>
				
			</tr>`;

            var main = "";
            for (i = 0; i < questionairedetails.length; i++) {
                if (questionairedetails[i].response === "Yes") {

                } else if (questionairedetails[i].response === "No") {

                }
                if (questionairedetails[i].response === "Yes") {
                    main += "<tr><td>" + questionairedetails[i].question +
                        "</td><td><input type='radio' name='myRadios_" + questionairedetails[i].questionaireId + "' onchange='saveResponse(" + '"' + surveyId + '", ' + '"' + userId + '", ' +
                        '"' + questionairedetails[i].questionaireId + '"' + ", " +
                        '"Yes" ' + ");' checked='checked' /> <label>Yes</label>" +
                        " <input type='radio' name='myRadios_" + questionairedetails[i].questionaireId + "' onchange='saveResponse(" + '"' + surveyId + '", ' + '"' + userId + '", ' +
                        '"' + questionairedetails[i].questionaireId + '"' + ", " +
                        '"No"' + ");' /> <label>No</label>" +
                        "</td></tr>";
                } else if (questionairedetails[i].response === "No") {
                    main += "<tr><td>" + questionairedetails[i].question +
                        "</td><td><input type='radio' name='myRadios_" + questionairedetails[i].questionaireId + "' onchange='saveResponse(" + '"' + surveyId + '", ' + '"' + userId + '", ' +
                        '"' + questionairedetails[i].questionaireId + '"' + ", " +
                        '"Yes" ' + ");' /> <label>Yes</label>" +
                        " <input type='radio' name='myRadios_" + questionairedetails[i].questionaireId + "' onchange='saveResponse(" + '"' + surveyId + '", ' + '"' + userId + '", ' +
                        '"' + questionairedetails[i].questionaireId + '"' + ", " +
                        '"No"' + ");' checked='checked' /> <label>No</label>" +
                        "</td></tr>";
                } else {
                    main += "<tr><td>" + questionairedetails[i].question +
                        "</td><td><input type='radio' name='myRadios_" + questionairedetails[i].questionaireId + "' onchange='saveResponse(" + '"' + surveyId + '", ' + '"' + userId + '", ' +
                        '"' + questionairedetails[i].questionaireId + '"' + ", " +
                        '"Yes" ' + ");' /> <label>Yes</label>" +
                        " <input type='radio' name='myRadios_" + questionairedetails[i].questionaireId + "' onchange='saveResponse(" + '"' + surveyId + '", ' + '"' + userId + '", ' +
                        '"' + questionairedetails[i].questionaireId + '"' + ", " +
                        '"No"' + ");' /> <label>No</label>" +
                        "</td></tr>";
                }
            }
            var tblbottom = "</table>";
            var tbl = tbltop + main + tblbottom;
            document.getElementById("questionaire").innerHTML = tbl;
        }
    };
    xmlhttp.send();
};

function saveResponse(surveyId, userId, questionaireId, response) {
    var saveresponseurl = "http://localhost:8080/user/saveresponse?surveyId=" + surveyId + "&userId=" + userId + "&questionaireId=" + questionaireId + "&response=" + response;


    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", saveresponseurl, true);
    xmlhttp.setRequestHeader('Accept', '*/*');
    xmlhttp.setRequestHeader("Content-Type", "application/json");

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText === "true") {
                alert("center addedd successfully");
            }

        }
    };
    xmlhttp.send();

};

function getRespondentDetails() {
    var respondentdetailssurl = "http://localhost:8080/admin/getrespondentdetails";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", respondentdetailssurl, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            var respondentdetails = JSON.parse(xmlhttp.responseText);
            var tbltop = `<table id="respondents">
			<tr>
				<th>Respondent Email</th>
				<th>Respondent First Name</th>
				<th>Respondent Last Name</th>
				<th>City</th>
				<th>State</th>
				<th>Send Questionaire</th>
			</tr>`;

            var main = "";
            for (i = 0; i < respondentdetails.length; i++) {

                main += "<tr><td>" + respondentdetails[i].email +
                    "</td><td>" + respondentdetails[i].firstName +

                    "</td><td>" + respondentdetails[i].lastName +
                    "</td><td>" + respondentdetails[i].city +
                    "</td><td>" + respondentdetails[i].state +

                    "</td><td id='" + respondentdetails[i].email +
                    "' onclick='sendEmail(" + '"' + respondentdetails[i].id + '"' + ", " + '"' + respondentdetails[i].email + '"' + " , " + '"' +
                    respondentdetails[i].firstName + '"' + ")'><div class='primary-button'><button class='tablinks' >Send</button><div>" +
                    "</td></tr>";
            }
            var tblbottom = "</table>";
            var tbl = tbltop + main + tblbottom;
            document.getElementById("respondentdetails").innerHTML = tbl;
        }
    };
    xmlhttp.send();
};

function upload(event) {
    var selectedFile = event.files[0];
    if (selectedFile) {
        var fileReader = new FileReader();
        fileReader.onload = function(event) {
            var data = event.target.result;
            var workbook = XLSX.read(data, {
                type: "binary"
            });
            workbook.SheetNames.forEach(sheet => {
                let rowObject = XLSX.utils.sheet_to_row_object_array(
                    workbook.Sheets[sheet]);
                let jsonObject = JSON.stringify(rowObject);

                localStorage.removeItem('importrespondents');
                localStorage.setItem('importrespondents', jsonObject);

            });
        };
        fileReader.readAsBinaryString(selectedFile);
    }
};

function uploadClick() {

    var importrespondentsurl = "http://localhost:8080/admin/importrespondents";


    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", importrespondentsurl, true);
    xmlhttp.setRequestHeader('Accept', '*/*');
    xmlhttp.setRequestHeader("Content-Type", "application/json");

    var data = localStorage.getItem('importrespondents');

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            if (xmlhttp.responseText === "true") {
                alert("center addedd successfully");
            }

        }
    };
    xmlhttp.send(data);
};

function validateUser(email, password) {

    if (email === "admin@nyu.edu" && password === "admin@123") {
        document.location = "index.html";
    }
};


function exit() {
	document.location = "/";
	localStorage.clear();
};

/*
 ** validate email
 */
function validateEmail(mail) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
        return true;
    }
    alert("You have entered an invalid email address!")
    return false;
};

if (btnLogin != null) {
    btnLogin.onclick = function() {
        var email = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        validateEmail(email);
        validateUser(email, password);
    }
};