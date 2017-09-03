/**
function objectifyForm(formArray) {//serialize data function
	alert("Came here.");
	console.log("Huh...");
	var returnArray = {};
	for (var i = 0; i < formArray.length; i++) {
		returnArray[formArray[i]['name']] = formArray[i]['value'];
	}
	return returnArray;
}
 */

function addStudentAndShowResult(form) {
	// collect the form data while iterating over the inputs
	console.log("In addStudentAndShowResult()");
	var parsedFormData = parseFormData(form);
	var jsonData = convertToJSON(parsedFormData);
	jsonPostData(jsonData);
}
  

function parseFormData(form) {
	alert("Came in parseFormData.");
	console.log("In parseFormData()");
	// collect the form data while iterating over the inputs
	var data = {};
	for (var i = 0, ii = form.length; i < ii; ++i) {
		var input = form[i];
		console.log(input, "# ", input.name, " : ", input.value);
		if (input.name) {
			// Check f data is any of the form specific values, don't ad them  to json for server side parsing 
			if(input.name === "form_id" || input.name === "sumbit"){
				// don't add to json
			}else{
				// check if entry with same name already exists ( with some value or
				// ""), if it does, don't override it
				if (!data[input.name]) {
					if (input.value != null) {
						data[input.name] = input.value;
						console.log('## ', data[input.name],' ', input.name,' ', input.value);
					}
				}
				console.log("Radio ", input.name.checked);
				if (input.name.checked) { // for radio buttons
					console.log("#Radio ", input.value, " ", input.checked.value);
					data[input.name] = input.value;
				}
			}
		}
	}
	return data;
}

function convertToJSON(data) {
	var jsonData = JSON.stringify(data)
	console.log("JSON data ", jsonData);
	return jsonData;
}

function someFunction(data) {
	// construct an HTTP request
	var xhr = new XMLHttpRequest();
	xhr.open(form.method, form.action, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	// send the collected data as JSON
	// xhr.send(JSON.stringify(data));
	/*
	xhr.onloadend = function () { // done };
	 */
	var json = JSON.stringify(data);
	console.log("JSON ", json);
};


function jsonPostData(data) {
	console.log("In jsonPost");
	console.log(data);
	var url = "rest/student/addstudent";
	// construct an HTTP request
	var xhr = new XMLHttpRequest();
	// xhr.open(data.method, data.action, true);
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	// send the collected data as JSON
	xhr.send(data);

	xhr.onloadend = function() {
		result = xhr.responseText;
		// var result = JSON.parse(this.responseText);
		console.log(result);
		alert(result);
	};
}


// http://jsfiddle.net/doktormolle/RR7RK/
function getStudentsInPopupWindowPost(frmAction,//url the form has to be sended to
        frmMethod,//post||get
        winName,//name used for window and form-target
        winOpts,//options for window.open
        jsonName,//the name of the json inside _POST
        jsonObj//the object to send
        )
{
	  //open the window
	  var win=window.open('about:blank',winName,winOpts);
	      win.focus();
	  //create form & input and append it to the body
	  var f=document.createElement('form');
	      f.setAttribute('action',frmAction);
	      f.style.display='none';
	      f.setAttribute('target',winName);
	      f.setAttribute('method',frmMethod);

	  var e=document.createElement('input');
	      e.setAttribute('name',jsonName);
	      e.setAttribute('value',JSON.stringify(jsonObj));
	      f.appendChild(e);
	      document.body.appendChild(f);

	  //send the form 
	      f.submit();
	  //remove the form after a moment
	      setTimeout(function(){f.parentNode.removeChild(f);},1000);
	      return false;
}

function getStudentsInPopupWindow(frmAction,//url the form has to be sended to
        frmMethod,//post||get
        winName,//name used for window and form-target
        winOpts//options for window.open
        )
{
	console.log("In method getStudentsInPopupWindow()");
	  //open the window
	  var win=window.open('about:blank',winName,winOpts);
	      win.focus();
	  //create form & input and append it to the body
	  var f=document.createElement('form');
	      f.setAttribute('action',frmAction);
	      f.style.display='none';
	      f.setAttribute('target',winName);
	      f.setAttribute('method',frmMethod);

	  var e=document.createElement('input');
	      f.appendChild(e);
	      document.body.appendChild(f);

	  //send the form 
      f.submit();
	  //remove the form after a moment
      setTimeout(function(){f.parentNode.removeChild(f);},1000);
      return false;
}

function getStudentsInPopupWindow1(){
	var frmAction = "rest/students/allstudents";
	var frmMethod = "GET";
	var winName = "someNameWithoutSpaces";
	var winOpts = "width=400,height=300,toolbar=yes";
	console.log("In method getStudentsInPopupWindow1()");
	getStudentsInPopupWindow(frmAction,frmMethod, winName, winOpts);
    return false;
}

function createTableStudent(studentData){
	console.log("In createTableStudent()",studentData);
	// alert("In createTableStudent()");
	var txt = "<table border='1'>";
	var val;
	var address;
	// table header
	txt += "<tr>";
	// https://stackoverflow.com/a/684693/1679643, https://stackoverflow.com/a/19323734/1679643
	for (var key in studentData) {
		if (!studentData.hasOwnProperty(key)) {
			// https://stackoverflow.com/a/684693/1679643
			//The current property is not a direct property of studentData
	        continue;
		}else{
			txt += "<th>"+key+"</th>";
		}
	}
	address = studentData.address;
	for (var addressKey in address) {
		if (!address.hasOwnProperty(addressKey)) {
			//The current property is not a direct property of address
	        continue;
		}else{
		    txt += "<th>"+addressKey+"</th>";
		}
	}
	txt += "</tr>";
	// single row table
	txt += "<tr>";
	for (var key in studentData) {
		if (!studentData.hasOwnProperty(key)) {
			//The current property is not a direct property of studentData
	        continue;
		}else{
		    val = studentData[key];
		    if(val)
		    	if(key === "dob")
		    		txt += "<td>"+new Date(parseInt(val))+"</td>";
		    	else
		    		txt += "<td>"+val+"</td>";
		}
	}
	address = studentData.address;
	for (var addressKey in address) {
		if (!address.hasOwnProperty(addressKey)) {
			//The current property is not a direct property of address
	        continue;
		}else{
		    val = address[addressKey];
		    if(val)
		    	txt += "<td>"+val+"</td>";
		}
	}
   	txt += "</tr>";
    txt += "</table>";
    console.log("In createTableStudent()",txt);
    var divContainer = document.getElementById("show_student_by_id").style.visibility = "visible";
	//divContainer.innerHTML = txt;
    document.getElementById("show_student_by_id").innerHTML = txt;
}

function getStudentByID(student_id){
	//getStudentByID(student_id, "rest/student/", "GET");
	var studentidParam = "studentid"
	data = { studentidParam:student_id};
	dataAsJson = JSON.stringify(student_id);
	console.log("After converting to JSON", dataAsJson);
	//alert("After converting to JSON", dataAsJson);
	// https://stackoverflow.com/a/15218370/1679643
	var url = "rest/student/"+"?" + studentidParam +"="+ encodeURIComponent(student_id);		// rest/student/?studentid=1
	console.log("URL ", url);
	// alert("URL ", url);
	// callback https://www.w3schools.com/js/js_ajax_http_response.asp
	getResponseFromServer(dataAsJson, url, "GET", responseAsJson);
}

function responseAsJson(xhr) {
	var resp  = JSON.parse(this.responseText);
	console.log("In responseAsJson", resp);
	alert("In responseAsJson", resp);
	createTableStudent(resp);
}


// https://stackoverflow.com/a/5362513/1679643
function getResponseFromServer(data, url, method, callback) {
	console.log("In getResponseFromServer");
	console.log(data);
	console.log(url);
	// construct an HTTP request
	var xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Here the callback gets implemented
            	//if (typeof callback == "function") {
            		//object = JSON.parse(xhr.responseText);
                    // apply() sets the meaning of "this" in the callback
                    callback.apply(xhr);
            	//}
            } else {

            }
        }
    };
    
	xhr.open(method, url, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	// send the collected data as JSON
	xhr.send(data);
	
	xhr.onloadend = function() {
		//result = xhr.responseText;
		var result = JSON.parse(this.responseText);
		console.log("onloadend ", result);
		createTableStudent(result);
		//alert(result);
		//return result;
	};
}
