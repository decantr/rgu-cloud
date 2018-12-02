package test.resource;

//JAX-RS

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//AWS SDK

@Path( "/test" )
public class TestResource {
@GET
@Produces( MediaType.TEXT_PLAIN )
public Response dummyGet() {
	return Response.status( 200 ).entity( "Congratulations! Jersey is working!" ).build();
} //end method
} //end class
