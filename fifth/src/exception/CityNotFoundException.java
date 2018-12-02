package exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CityNotFoundException extends WebApplicationException {
public CityNotFoundException( String code ) {
	super( Response.status( Response.Status.NOT_FOUND ).entity( "city code " + code + " not found" ).type( "text/plain" ).build() );
} //end method
} //end class
