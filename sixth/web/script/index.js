//API keys
var mapBoxApiKey = "*** Your Mapbox API key ***";
var openWeatherAPIKey = "*** Your OpenWeatherMap API key here ***";
var baseURL = "api";

//the document ready function
try {
	$(function () {
			init();
		}
	);
} catch (e) {
	alert("*** jQuery not loaded. ***");
}

//
// Initialise page.
//
function init() {
	var map = makeMap("map", 1, 0.0, 0.0);	//make map using Leaflet
	var marker = makeMarker(map, 0.0, 0.0);	//make and put marker on map, keeping reference

//make dialog box
	alert("Add some options to your dialog call so that it does not\n show after creation and disable parent input.");
	$("#cityDetails").dialog({
			//options to dialog box as a map
			// *** Add some options here so that the dialog box
			// *** is hidden by default, and disable parent when it is opened.
			minWidth: 500,
			minHeight: 400
		}
	);	//end call to dialog

//set click handler of Add City button
	$("#addCity").click(function () {
			//*** Add JS code to clear city name input box. ***
			alert("Use jQuery to clear city name input box.");
			$("#cityDetails").dialog("open", true);	//open dialog box
		}
	);

//set click handler of Cancel button in Add City dialog
// *** Add JS code to register a click handler for the Cancel button. ***
// *** Close the dialog when the Cancel button is clicked.
	alert("Register an on-click handler for the Cancel button\n to close the dialog box when the Cancel button is clicked.");

//set click handler of Save City button in Add City dialog
	$("#saveCity").click(function () {
			saveCity(marker);	//save city to web service

			//*** Add code to close dialog box after city is saved. ***
			alert("Add JS to close dialog box after city is saved.");
		}
	);

//set click handler of Delete Selected button
// *** If you want to, handle the Delete Selected button to delete a selected city.

	populateCities();	//populate list of known cities
}

//
// save a city using the City service, given its position
//
function saveCity(marker) {
	var longitude = marker.getLatLng().lng;	//get longitude from position
	var latitude = marker.getLatLng().lat;	//get latitude from position

// *** Modify code to get city name from input text box. ***
	alert("Modify JS to get city name from input text box.");
	var name = "Somewhere";			//*** Modify this. ***

	var url = baseURL + "/city";					//URL of web service
	var data = {
		"name": name,				//request parameters as a map
		"longitude": longitude,
		"latitude": latitude
	};

//use jQuery shorthand Ajax POST function
	$.post(url,				//URL of service
		data,			//parameters of request
		function ()		//successful callback function
		{
			alert("City saved: " + name + " (" + longitude + "," + latitude + ")");
		} //end callback function
	); //end post call
} //end function

//
// retrieve all cities from City service and populate list
//
function populateCities() {
	var url = baseURL + "/city";		//URL of city service

//use jQuery shorthand Ajax function to get JSON data
	$.getJSON(url,				//URL of service
		function (cities)		//successful callback function
		{
			//*** Add JS code to remove all cities in the list first. ***
			alert("Add JS code to remove all old cities from the list first.");

			for (var i in cities) {
				var city = cities[i];		//get 1 city from the JSON list
				// *** Modify JS to get city ID from JSON data.
				// *** Also get city name.
				alert("Modify code to get city ID and name from JSON data.");
				var id = "";		//*** Modify this. ***
				var name = "";	//*** Modify this. ***

				// *** Modify JS code to compose the HTML of a list item using the city ID and name.
				alert("Modify code to compose HTML of a list item using city ID & name.");
				var htmlCode = "<li>Modify me!</li>";		//*** Modify this. ***

				$("#cities").append(htmlCode);	//add a child to the city list
			}

			//look for all list items (i.e. cities), set their click handler
			$("#cities li").click(function () {
					// *** Add JS code to call the cityClicked(...) function.
					// *** The parameter should the ID of the city clicked.
					alert("Add JS code to call the cityClicked(...) function with the city ID.");
				} //end click handler function
			); //end click call
		} //end Ajax callback function
	); //end Ajax call
} //end function

//
// click handler of a city in the list
// parameter ID is the unique city identifier
//
function cityClicked(id) {
	$("#cities li").removeClass("selected"); //remove all list items from the class "selected, thus clearing previous selection

// *** Add JS code to find the selected city (i.e. list item) and add the class "selected" to it.
// *** This will highlight it according to the "selected" class.
	alert("Use jQuery add class 'selected' to the selected city list item.");

//retrieve city coordinates from city service
	var url = baseURL + "/city/" + id;		//URL of service, notice that ID is part of URL path

//use jQuery shorthand Ajax function to get JSON data
	$.getJSON(url,					//URL of service
		function (jsonData)	//successful callback function
		{
			longitude = jsonData["longitude"];			//get longitude from JSON data
			latitude = jsonData["latitude"];			//get latitude from JSON data

			// *** Add JS code to update h1 on page to show city name
			alert("Add JS to show city name on page.\nThere is a h1 inside the section of weather details.");

			showCityWeather(longitude, latitude);
		}
	);
} //end function

//
// show a city's weather given its coordinates
//
function showCityWeather(longitude, latitude) {
	var url = "http://api.openweathermap.org/data/2.5/weather";		//URL of OpenWeatherMap service

// *** Compose request parameters as a map
	alert("Modify code to supply parameters to OpenWeatherMap service.");

	var data = {	//
		// *** Modify code to add required parameters here. ***
		//
		"cnt": 1,			//return only 1 entry
		"units": "metric",	//use metric unit
		"appid": "***Your OpenWeatherMap API key here***"	//***put your API key here
		//plus other parameters required
	};

//
// *** Add JS code to retrieve weather data from OpenWeatherMap web service.
// *** When the JSON data come back, update information on the web page.
	alert("Add JS code to retrieve weather data from OpenWeatherMap and update web page.");
} //end function

//
//create map in a given division, given its centre coordinates
//the map is returned as it is need to place the marker
//
function makeMap(divId, zoomLevel, longitude, latitude) {
	var location = L.latLng(longitude, latitude);		//create location
	var map = L.map(divId).setView(location, zoomLevel);	//put map into division
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapBoxApiKey,
		{
			attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
			maxZoom: 18,
			id: 'mapbox.streets',
			accessToken: mapBoxApiKey
		}
	).addTo(map);
	return map;	//return map object
} //end function

//
//create a marker on a map
//the marker is returned as we need to get its position later
//
function makeMarker(map, longitude, latitude) {
	var location = L.latLng({lon: longitude, lat: latitude});	//create marker at given position
	var marker = L.marker(location, {draggable: true});			//make a draggable marker
	marker.addTo(map);										//add marker to map
	return marker;	//return marker object
} //end function
