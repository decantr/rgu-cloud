package city.model;

/**
 * This class models a city/place.
 * <p>
 * You will have to
 * <p>
 * 1. Annotate the class to map it to the DynamoDB table "cm4108-lab05-city".
 * 2. Annotate the setter/getter of the city code as the primary hash key.
 * 3. Annotate other attributes' setters/getters as DynamoDB attributes.
 */
public class City {
/**
 * The unique code that identifies a city.
 */
public String code;

/**
 * The name of the city. Note that city name is not unique. e.g. There are many Aberdeen in the world.
 */
public String name;

/**
 * The longitude of the city. We can use this to show the city's map.
 */
public double longitude;

/**
 * The latitude of the city.
 */
public double latitude;

/**
 * Need this default constructor for AWS SDK.
 */
public City() {
} //end method

/**
 * Create a City object.
 *
 * @param code The city's unique code.
 * @param name The city's name.
 * @param lon  The city's longitude.
 * @param lat  The city's latitude.
 */
public City( String code , String name , double lon , double lat ) {
//
// Use setters because of AWS SDK.
//
	this.setCode( code );
	this.setName( name );
	this.setLongitude( lon );
	this.setLatitude( lat );
} //end method

public String getCode() {
	return this.code;
} //end method

public void setCode( String code ) {
	this.code = code;
} //end method

public String getName() {
	return this.name;
} //end method

public void setName( String name ) {
	this.name = name;
} //end method

public double getLongitude() {
	return this.longitude;
} //end method

public void setLongitude( double longitude ) {
	this.longitude = longitude;
} //end method

public double getLatitude() {
	return this.latitude;
} //end method

public void setLatitude( double latitude ) {
	this.latitude = latitude;
} //end method
} //end class
