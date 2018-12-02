package city.resource;

//general Java

import city.model.City;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import util.Config;
import util.DynamoDBUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

//JAX-RS
//AWS SDK

@SuppressWarnings( "serial" )

@Path( "/city" )
public class CityResource {
@POST
@Produces( MediaType.TEXT_PLAIN )
public Response addACity( @FormParam( "name" ) String name ,
													@FormParam( "longitude" ) double longitude ,
													@FormParam( "latitude" ) double latitude ) {
	try {
		City city = new City( name , longitude , latitude );

		DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );
		mapper.save( city );
		return Response.status( 201 ).entity( "city saved" ).build();
	} catch ( Exception e ) {
		return Response.status( 400 ).entity( "error in saving city" ).build();
	}
} //end method

@Path( "/{id}" )
@GET
@Produces( MediaType.APPLICATION_JSON )
public City getOneCity( @PathParam( "id" ) String id ) {
	DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );
	City city = mapper.load( City.class , id );

	if ( city == null )
		throw new WebApplicationException( 404 );

	return city;
} //end method

@GET
@Produces( MediaType.APPLICATION_JSON )
public Collection< City > getAllCities() {
	DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();  //create scan expression
	List< City > result = mapper.scan( City.class , scanExpression );      //retrieve all cities from DynamoDB
	return result;
} //end method


@Path( "/{id}" )
@DELETE
public Response deleteOneCity( @PathParam( "id" ) String id ) {
	DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );
	City city = mapper.load( City.class , id );

	if ( city == null )
		throw new WebApplicationException( 404 );

	mapper.delete( city );
	return Response.status( 200 ).entity( "deleted" ).build();
} //end method
} //end class
