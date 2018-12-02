package city.resource;

import city.model.City;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import config.Config;
import exception.CityNotFoundException;
import util.DynamoDBUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Jersey
//AWS

@Path( "/city" )
public class CityResource {
/**
 * This method handles a POST request to the URL "/lab05/api/city" to store a City object into DynamoDB.
 *
 * @param code
 * @param name
 * @param longitude
 * @param latitude
 * @return
 */
@POST
@Produces( MediaType.TEXT_PLAIN )
public Response addCity( @FormParam( "code" ) String code ,
												 @FormParam( "name" ) String name ,
												 @FormParam( "long" ) double longitude ,
												 @FormParam( "lat" ) double latitude ) {
	try {
		//
		// You should:
		//
		// *** 1. Annotate method to handle POST requests.
		// *** 2. Set response content-type to plain-text (i.e. MediaType.TEXT_PLAIN).
		// *** 3. Inject form parameters into method.
		// *** 4. Use submitted parameters to create a City object.
		// *** 5. Use DynamoDBMapper to persist City object into DynamoDB.
		//
		City city = new City( code , name , longitude , latitude );  //create a City object using the submitted values
		DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );  //create DynamoDBMapper
		mapper.save( city );

		return Response.
											 status( 201 ).
																				entity( code + "/" + name + " (" + longitude + "," + latitude + ") saved sucessfully" ).
																																																																	 build();      //a successful reply
	} catch ( Exception e ) {
		return Response.
											 status( 400 ).
																				entity( "Something went wrong. Parameters accepted: code, name, long, lat" ).
																																																												build();    //if the client did something wrong
	}
} //end method

/**
 * This method should handle a GET request to the URL "/lab05/api/city/<code>"
 * to retrieve a city's information and return it as a JSON map.
 *
 * @param code
 * @return
 */
@GET
@Produces( MediaType.APPLICATION_JSON )
@Path( "/{code}" )
public City retrieveOneCity( @PathParam( "code" ) String code ) {
//
// You should:
//
// *** 1. Annotation method for GET handling.
// *** 2. Set response content-type to JSON.
// *** 3. Use injection to get city ID/code from the URL path.
// *** 4. Use DynamoDBMapper to load City object using city ID/code.
// *** 5. Return City object in JSON. Jackson will do the conversion to JSON.
// *** Note: If city ID/code cannot be found, you should return an appropriate error.
//
	DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );
	City place = mapper.load( City.class , code );  //find city by its code
	if ( place != null )
		return place;
	throw new CityNotFoundException( code );
} //end method

/**
 * This method handles a GET request to the URL "/lab05/api/city"
 * to retrieve all cities as a JSON list.
 *
 * @return
 */
@GET
@Produces( MediaType.APPLICATION_JSON )
public Iterable< City > retrieveAllCities() {
//
// You should:
//
// *** 1. Annotation method for GET handling.
// *** 2. Set response content-type is JSON.
// *** 3. Use DynamoDBMapper to scan and get all cities as a Iterable/List.
// *** 4. Return the Iterable/List in JSON. Jackson will do the conversion.
//
	DynamoDBMapper mapper = DynamoDBUtil.getDBMapper( Config.REGION , Config.LOCAL_ENDPOINT );  //create DynamoDBMapper
	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();  //create scan expression
	List< City > result = mapper.scan( City.class , scanExpression );      //retrieve all cities from DynamoDB
	return result;
} //end method

} //end class
