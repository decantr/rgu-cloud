package exception;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

public class InvalidParamException extends WebApplicationException {
public InvalidParamException( String message ) {
	super( Response.status( Response.Status.BAD_REQUEST ).entity( message ).type( "text/plain" ).build() );
} //end method
} //end class
