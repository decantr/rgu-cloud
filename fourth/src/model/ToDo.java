package cm4108.lab04.todo.model;

import java.util.*;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * This simple class models a to-do item in a diary.
 * @author K. Hui
 *
 */
public class ToDo 
{
/**
 * The unique ID of the entity.
 * Note that we ask JPA/GAE to auto generate this attribute.
 * Once the object is stored into GAE, this attribute will have a Long object assigned.
 */
public UUID id;

/**
 * The date on which the to-do has to be done.
 */
public Date date;

/**
 * A brief description of the to-do item.
 */
public String content;

/**
 * The status of the to-do item.
 */
public boolean completed;

/**
 * Create a ToDo object.
 * If the id is null, we will generate a random UUID as the id.
 *
 * @param id		The ID of the to-do item if given. If this is null, a UUID will be generated.
 * @param date	The date of the to-do item.
 * @param content	The description of item.
 * @param completed	A true/false value, meaning the item is completed or not.
 */
public ToDo(UUID id,Date date,String content,boolean completed)
{
if (id!=null)
	this.id=id;
else this.id=UUID.randomUUID();
this.date=date;
this.content=content;
this.completed=completed;
} //end method

public void setDate(Date d)
{
this.date=d;
}

public Date getDate()
{
return this.date;
}

public boolean getCompleted()
{
return this.completed;
}

public void setCompleted(boolean completed)
{
this.completed=completed;
}

public String getContent()
{
return this.content;
}

public void setContent(String c)
{
this.content=c;
}
/**
 * Convert the ToDo object to an XML-formatted string.
 * @return	An XML representation of the to-do item.
 */
public String toXml()
{
String result="<todo ";
result+="date=\""+this.date+"\" ";
result+="id=\""+this.id+"\" ";
result+="completed=\""+this.completed+"\" ";
result+="content=\""+StringEscapeUtils.escapeXml10(this.content)+"\" />";
return result;
} //end method

@Override
/**
 * Return a plain text representation of the to-do item.
 */
public String toString() 
{
return "ToDo [id=" + id + ", date=" + date + ", content=" + content + ", completed=" + completed + "]";
} //end method
} //end class
