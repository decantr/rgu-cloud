package resource;

import cm4108.lab04.todo.model.ToDo;
import cm4108.lab04.todo.model.ToDoDatabase;
import cm4108.lab04.todo.util.Util;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class ToDoServlet extends HttpServlet {
@Override
protected void doGet( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
	try {
		UUID id = UUID.fromString( req.getPathInfo().substring( 1 ) );
		ToDo toDo = ToDoDatabase.getInstance().get( id );

		if ( toDo == null ) {
			resp.sendError( 404 , id + " not found" );
			return;
		}

		resp.setContentType( "application/json" );
		resp.getWriter().println( new Gson().toJson( toDo ) );


	} catch ( Exception ignored ) {

		Iterable< ToDo > toDos = ToDoDatabase.getInstance().getAll();

		resp.setContentType( "application/json" );
		resp.getWriter().println( new Gson().toJson( toDos ) );
	}
}

@Override
protected void doPost( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
	try {
		boolean completed = Util.stringToBoolean( req.getParameter( "completed" ) );
		String content = req.getParameter( "content" );
		Date date = Util.stringToDate( req.getParameter( "date" ) );
		ToDo todo = new ToDo( null , date , content , completed );

		ToDoDatabase.getInstance().add( todo );

		resp.setStatus( 201 );
	} catch ( Exception e ) {
		resp.sendError( 400 , e.toString() );
	}
}

@Override
protected void doDelete( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
	try {

		UUID id = UUID.fromString( req.getPathInfo().substring( 1 ) );
		ToDoDatabase.getInstance().delete( id );

		resp.setStatus( 200 );

	} catch ( Exception ignored ) {
		resp.sendError( 404 , "ToDo not Found" );
	}
}

@Override
protected void doPut( HttpServletRequest req , HttpServletResponse resp ) throws ServletException, IOException {
	try {

		UUID id = UUID.fromString( req.getPathInfo().substring( 1 ) );

		ToDo toDo = ToDoDatabase.getInstance().get( id );

		if ( toDo == null )
			throw new Exception( "id not found" );

		boolean completed = Util.stringToBoolean( req.getParameter( "completed" ) );
		String content = req.getParameter( "content" );

		toDo.setCompleted( completed );
		toDo.setContent( content );

	} catch ( Exception e ) {
		resp.sendError( 400 , e.toString() );
	}
}
}
