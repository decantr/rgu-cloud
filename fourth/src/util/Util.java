package cm4108.lab04.todo.util;

import java.util.*;
import java.text.*;

/**
 * This handy utility class gives you some methods to convert be a String and the required types.
 * 
 * @author K. Hui
 *
 */
public class Util
{
/**
 * Convert the string "true" or "false" to the boolean value.
 * @param s	The string to convert. Anything not "true" is treated as false.
 * @return	The converted boolean value.
 */
public static boolean stringToBoolean(String s)
{
return s.equalsIgnoreCase("true");
} //end method

/**
 * Converting a String into a Date object.
 * @param s	A string which can be parsed into a Date. e.g. "2017/03/20".
 * @return	The converted Date object, or null if parsing failed.
 */
public static Date stringToDate(String s)
{
try	{
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");	//specify the date format
	Date d=format.parse(s);	//parse the string
	return d;
	} catch (Exception e)
		{
		return null;
		}
} //end method
} //end class
