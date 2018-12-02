package cm4108.lab04.todo.model;

import java.util.*;

/**
 * This class models a simple database that stores {@link ToDo ToDo} object.
 * To create a {@link ToDoDatabase ToDoDatabase} object, do not do <code>new ToDoDatabase()</code>.
 * Instead, use the static method {@link ToDoDatabase#getInstance ToDoDatabase.getInstance()}.
 * 
 * @author K. Hui
 *
 */
public class ToDoDatabase
{
private static ToDoDatabase instance=null;
private Map<UUID,ToDo> data;

private ToDoDatabase()
{
this.data=new HashMap<UUID,ToDo>();
} //end method

/**
 * Use this static method to create a {@link ToDoDatabase ToDoDatabase} instance.
 * @return A {@link ToDoDatabase ToDoDatabase} object.
 */
public static ToDoDatabase getInstance()
{
if (ToDoDatabase.instance==null)	
	ToDoDatabase.instance=new ToDoDatabase();
return ToDoDatabase.instance;
} //end method

/**
 * Add a {@link ToDo ToDo} item into the database.
 * @param x	The item to add.
 */
public void add(ToDo x)
{
this.data.put(x.id,x);
} //end method

/**
 * Given a UUID, retrieve a {@link ToDo ToDo} item.
 * 
 * @param id
 * @return	The {@link ToDo ToDo} item retrieve, or null if none has this UUID.
 */
public ToDo get(UUID id)
{
return this.data.get(id);
} //end method

/**
 * Retrieve all {@link ToDo ToDo} items as a {@link Iterable java.util.Iterable}.
 * @return	All {@link ToDo ToDo} items in the database.
 */
public Iterable<ToDo> getAll()
{
return this.data.values();
} //end method

/**
 * Given a UUID, delete a {@link ToDo ToDo} item.
 * 
 * @param id		The ID of the {@link ToDo ToDo} item to delete from the database.
 */
public void delete(UUID id)
{
this.data.remove(id);
} //end method
} //end class
