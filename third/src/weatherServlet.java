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
	String place = request.getParameter( "place" );

	response.setContentType( " text/plain" );

	WeatherDatabase wd = new WeatherDatabase();

	WeatherInfo wi = wd.find( place );

	if ( wi == null ) {
		response.sendError( 403 );
		return;
	}

	response.getWriter().println( place + ": " + wi.condition + " " + wi.temperature + wi.unit );
}
}
