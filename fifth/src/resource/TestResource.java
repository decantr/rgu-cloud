package resource;

//JAX-RS

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//AWS SDK

@Path( "/test" )
public class TestResource {
@GET
@Produces( MediaType.TEXT_PLAIN )
public Response dummyGet() {
	return Response.status( 200 ).entity( "Congratulations! Jersey is working!" ).build();
} //end method
} //end class
