
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
    console.log(input, "# ",input.name, " : ", input.value);
    if (input.name){
		// check if entry with same name already exists ( with some value or ""), if it does, don't override it
		if(!data[input.name]){
			if(input.value != null){
				data[input.name] = input.value;
			}
		}
		console.log("Radio ",input.name.checked);
		if(input.name.checked){	// for radio buttons
			console.log("#Radio ",input.value," ", input.checked.value);
			data[input.name] = input.value;
		}
    }
  }
  console.log(form.method);
  console.log(form.action);
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
  //xhr.send(JSON.stringify(data));
/*
  xhr.onloadend = function () {
    // done
  };
  */
  var json=JSON.stringify(data);
  console.log("JSON ", json);
};


function jsonPostData(data) {
	console.log("In jsonPost");
	console.log(data);
	console.log(data.method);
	console.log(data.action);
	var url = "rest/student/addstudent";
	// construct an HTTP request
	var xhr = new XMLHttpRequest();
	//xhr.open(data.method, data.action, true);
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	// send the collected data as JSON
	xhr.send(data);

	xhr.onloadend = function() {
		result = xhr.responseText;
		//var result = JSON.parse(this.responseText);
		console.log(result);
		alert(result);
	};
}