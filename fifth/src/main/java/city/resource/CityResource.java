package city.resource;

import city.model.City;
import com.amazonaws.regions.*;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

//Jersey
//AWS

/**
 * This class with handle requests to the URL pattern "/lab05/api/city".
 *
 * @author K. Hui
 */
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
public Response addCity( String code ,
												 String name ,
												 double longitude ,
												 double latitude ) {
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

		//return 201 status code if everything is fine
		return Response.
											 status( 201 ).
																				entity( code + "/" + name + " (" + longitude + "," + latitude + ") saved sucessfully" ).
																																																																	 build();      //a successful reply
	} catch ( Exception e ) {
		// if there is an exception, return a 400 status code
		return Response.
											 status( 400 ).
																				entity( "Something went wrong. Parameters accepted: code, name, long, lat" ).
																																																												build();    //if the client did something wrong
	}
} //end method

/**
 * This method should handle a GET request to the URL pattern "/lab05/api/city/<code>"
 * to retrieve a city's information and return it as a JSON map.
 *
 * @param code
 * @return The Cit.y object retrieved using the given code.
 * This City object should be converted to JSON by Jackson
 */
public City retrieveOneCity( String code ) {
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
	return null;  //***This should be replaced by your code.
} //end method

/**
 * This method handles a GET request to the URL "/lab05/api/city"
 * to retrieve all cities as a JSON list.
 *
 * @return
 */
public Iterable< City > retrieveAllCities() {
//
// You should:
//
// *** 1. Annotation method for GET handling.
// *** 2. Set response content-type is JSON.
// *** 3. Use DynamoDBMapper to scan and get all cities as a Iterable/List.
// *** 4. Return the Iterable/List in JSON. Jackson will do the conversion.
//
	return null;  //***This should be replaced by your code.
} //end method

} //end class
