import cm4108.lab03.weather.model.WeatherDatabase;
import cm4108.lab03.weather.model.WeatherInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class weatherServlet extends HttpServlet {
protected void doPost( HttpServletRequest request , HttpServletResponse response )
		throws IOException {
}

protected void doGet( HttpServletRequest request , HttpServletResponse response ) throws ServletException, IOException {
	String place = "";
	char unit = 'C';
	double temp;
	String p = request.getParameter( "place" );
	String u = request.getParameter( "unit" );

//	check if p was not null
	if ( p != null )
		place = p;
//	check if u was not null and if its wasn't make sure F was passed
	if ( u != null )
		if ( u.toUpperCase().charAt( 0 ) == 'F' )
			unit = 'F';

	response.setContentType( " text/plain" );

	WeatherDatabase wd = new WeatherDatabase();

	WeatherInfo wi = wd.find( place );

// if weather info fails return 403
	if ( wi == null ) {
		response.sendError( 403 );
		return;
	}

	// if unit has been passed as Fahrenheit then convert otherwise fallback to Celsius
	temp = unit == 'F' ? Math.round(( wi.temperature * 1.8 ) + 32) : wi.temperature;

	response.getWriter().println( place + ": " + wi.condition + " " + temp + unit );
}
}
