<%@ page import="cm4108.lab03.weather.model.*" %>
<%--
  Created by IntelliJ IDEA.
  User: decanter
  Date: 02/12/2018
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        img {
            vertical-align: middle;
            width: 50px;
        }
    </style>
</head>
<body>
<%
    double temp;
    char unit = 'C';
    String place = "aberdeen";
    String p = request.getParameter( "place" );
    String u = request.getParameter( "unit" );

//  check place is not null otherwise default to aberdeen
    if ( p != null )
        place = p;
//	check if u was not null and if its wasn't make sure F was passed
    if ( u != null )
        if ( u.toUpperCase().charAt( 0 ) == 'F' )
            unit = 'F';

    WeatherDatabase wd = new WeatherDatabase();
    WeatherInfo wi = wd.find( place );

    if ( wi == null ) {
        return;
    }

//    default temperature to show
    temp = unit == 'F' ? Math.round(( wi.temperature * 1.8 ) + 32) : wi.temperature;
    String temperature = "" + temp;
//    default unit image
    String unitUrl = "degree-" + unit + ".jpg";
//     get the condition image
//         had to rename rain-heavy and rain-moderate to rain_heavy and
//         rain_moderate to match the response from wi.condition
    String conditionUrl = wi.condition.toString().toLowerCase() + ".jpg";


%>
<h1>Weather of <%=place%>
</h1>
<p>
    Temperature:
    <img src="images/thermometer.jpg">
    <%=temperature%>
    <img src="images/<%=unitUrl%>">
</p>
<p>
    Condition: <img src="images/<%=conditionUrl%>">
</p>
</body>
</html>

