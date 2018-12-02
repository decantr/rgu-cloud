var apiURL = "/lab4_war_exploded/todo";

$(function () {
	//set reset button handler
	$("button#reset").click(
		function () {
			resetRequest();
		}
	);

	//set clear button handler
	$("button#clearResponse").click(
		function () {
			$("#format").html("---");
			$("#response textarea").val("");
		});

	//set send button handler
	$("button#send").click(
		function () {
			sendRequest();
		});

	//set method list change handler
	$("select#method").change(
		function () {
			methodChanged();
		});

	resetRequest();
	if (window.ActiveXObject) {
		alert("Do not use IE. Use a decent browser like FireFox/Chrome.");
	}
});


/**
 * Reset fields to default.
 */
function resetRequest() {
	$("#url").val(apiURL);	//default URL
	$("#method").prop("selectedIndex", 0);			//default to GET
	$("#datatype").prop("selectedIndex", 0);			//default to XML
	$(".param").val("");
	$(".value").val("");
} //end function


function methodChanged() {
	var method = $("#method").val();

	console.log(method);
	if (method == "get") {
		$("#url").val(apiURL);
		$("#datatype").prop("selectedIndex", 0);
		$(".param").val("");
		$(".value").val("");
		return;
	}
	if (method == "post") {
		$("#url").val(apiURL);
		$("#datatype").prop("selectedIndex", 2);
		populateParams({
			"content": "type something here...",
			"completed": false,
			"date": toISODate(new Date())
		});
		return;
	}

	if (method == "delete") {
		$("#url").val(apiURL + "/{id}");
		$("#datatype").prop("selectedIndex", 2);
		$(".param").val("");
		$(".value").val("");
		return;
	}

	if (method == "put") {
		$("#url").val(apiUrl + "/{id}");
		$("#datatype").prop("selectedIndex", 2);
		populateParams({
			"content": "something else...",
			"completed": false,
			"date": toISODate(new Date())
		});
		return;
	}

} //end function

function toISODate(date) {
	return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
} //end function

function populateParams(paramValues) {
	var index = 0;
	var fieldCount = $(".param").size();	//count the no. of param field in the web page

	for (var param in paramValues) {
		var value = paramValues[param];
		if (index < fieldCount - 1)	//only fill in value when there are empty param fields left
		{
			var paramField = $($(".param").get(index));
			var valueField = $($(".value").get(index));
			paramField.val(param);
			valueField.val(value);
		}
		index++;
	}
} //end function

/*
 * Send an HTTP request.
 */
function sendRequest() {
	var method = $("#method").val();	//get chosen method
	var url = $("#url").val();
	var datatype = $("#datatype").val();
	var data = {};
	$(".param").each(function () {
		var parameter = $(this).val();
		var value = $(this).parent().parent().find(".value").val();
		if (parameter.length != 0 && value.length != 0)
			data[parameter] = value;
	});

	var settings = {
		"type": method,
		"data": data,
		"dataType": datatype,
		"success": function (reply) {
			if (typeof reply == "string") {
				$("#format").html("text");
				$("#response textarea").val(reply);
			} else if (jQuery.isXMLDoc(reply)) {
				$("#format").html("xml");
				$("#response textarea").val(new XMLSerializer().serializeToString(reply.documentElement));
			} else {
				$("#format").html("json");
				$("#response textarea").val(JSON.stringify(reply));
			}
		},
		"error": function (xhr, status, error) {
			$("#response textarea").val("Status: " + status + "\nDetails: " + error);
		}
	};
	$.ajax(url, settings);
}
