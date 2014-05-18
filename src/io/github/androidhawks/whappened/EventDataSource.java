package io.github.androidhawks.whappened;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EventDataSource{

	  // Database fields
	  private SQLiteDatabase database;
	  private EventsSQLBuilder dbHelper;
	  EventDAO event;
	  List<EventDAO> events;
	  private String[] allColumns = { EventsSQLBuilder.EVENT_COLUMN_ID,
			  /*EventsSQLBuilder.EVENT_COLUMN_ID,*/ 
			  EventsSQLBuilder.EVENT_COLUMN_COMMENT,	
			  EventsSQLBuilder.EVENT_COLUMN_USER_ID, 
			  EventsSQLBuilder.EVENT_COLUMN_LOCATION, 
			  EventsSQLBuilder.EVENT_COLUMN_DATE_TIME,
			  EventsSQLBuilder.EVENT_COLUMN_IS_RECOMMENDED};

	  public EventDataSource(Context context) {
	    dbHelper = new EventsSQLBuilder(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void createEvent(String comment, String user_id, String location, String date_time, String is_rec) {
	    ContentValues values = new ContentValues();
	    /*values.put(EventsSQLBuilder.EVENT_COLUMN_ID, event_id);*/
	    values.put(EventsSQLBuilder.EVENT_COLUMN_COMMENT, comment);
	    values.put(EventsSQLBuilder.EVENT_COLUMN_USER_ID, user_id);
	    values.put(EventsSQLBuilder.EVENT_COLUMN_LOCATION, location);
	    values.put(EventsSQLBuilder.EVENT_COLUMN_DATE_TIME, date_time);
	    values.put(EventsSQLBuilder.EVENT_COLUMN_IS_RECOMMENDED, is_rec);
	    open();
	    long insertId = database.insert(EventsSQLBuilder.TABLE_EVENT, null,values);
	    System.out.println("inster or throw returns: " + insertId);
	  }

	  public void deleteEvent(EventDAO event) {
	    long id = event.getId();
	    System.out.println("Event deleted with id: " + id);
	    database.delete(EventsSQLBuilder.TABLE_EVENT, EventsSQLBuilder.EVENT_COLUMN_ID + " = " + id, null);
	  }

	  public List<EventDAO> getAllEvents() {
			database = dbHelper.getReadableDatabase();
			System.out.println("in datasource: is event open: " + database.isOpen());
		    List<EventDAO> events = new ArrayList<EventDAO>();
		    Cursor cursor = database.query(EventsSQLBuilder.TABLE_EVENT,allColumns, null, null, null, null, null);
		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
			      EventDAO event = cursorToEvent(cursor);
			      events.add(event);
			      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return events;
	  }

	  private EventDAO cursorToEvent(Cursor cursor) {
		  	EventDAO event = new EventDAO();
		    event.setId(cursor.getLong(0));
		    event.setComment(cursor.getString(1));
		    event.setUser_id(cursor.getString(2));
		    event.setLocation(cursor.getString(3));
		    event.setDate_time(cursor.getString(4));
		    event.setIsRecommended(cursor.getString(5));
		    return event;
	  }

}