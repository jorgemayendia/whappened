package io.github.androidhawks.whappened;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserDataSource{
	

	  // Database fields
	  private SQLiteDatabase database;
	  private UsersSQLBuilder dbHelper;
	  UserDAO user;
	  List<UserDAO> users;
	  Cursor cursor;
	  private String[] allColumns = { UsersSQLBuilder.USER_COLUMN_ID,
			  UsersSQLBuilder.USER_COLUMN_FIRST_NAME,
			  UsersSQLBuilder.USER_COLUMN_LAST_NAME,
			  UsersSQLBuilder.USER_COLUMN_EMAIL,
			  UsersSQLBuilder.USER_COLUMN_PASSWORD,
			  UsersSQLBuilder.USER_COLUMN_CREATED_DATE};


	  public UserDataSource(Context context) {
		  dbHelper = new UsersSQLBuilder(context);
	  }

	  public void open(Context context) throws SQLException {
		  dbHelper = new UsersSQLBuilder(context);
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public void createUser(String first_name, String last_name, String email, 
		String password, String created_date, String profile) {
	    ContentValues values = new ContentValues();
	    values.put(UsersSQLBuilder.USER_COLUMN_FIRST_NAME, first_name);
	    values.put(UsersSQLBuilder.USER_COLUMN_LAST_NAME, last_name);
	    values.put(UsersSQLBuilder.USER_COLUMN_EMAIL, email);
	    values.put(UsersSQLBuilder.USER_COLUMN_PASSWORD, password);
	    values.put(UsersSQLBuilder.USER_COLUMN_CREATED_DATE, created_date);
	    database = dbHelper.getWritableDatabase();
	    System.out.println("database createUser: database is open: "+database.isOpen());
	    try{
		    long insertId = database.insertOrThrow(UsersSQLBuilder.TABLE_USER, null, values);
		    System.out.println("inster or throw returns: "+insertId);
	    }catch(Exception e){
	    	System.out.println("Unable to commit to database. "+e);
	    }
	  }

	  public void deleteUser(UserDAO user) {
	    long id = user.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(UsersSQLBuilder.TABLE_USER, UsersSQLBuilder.USER_COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<UserDAO> getAllUsers() {
		  try{
			  database = dbHelper.getReadableDatabase();
			  System.out.println("in datasource: is open: "+database.isOpen());
			  users = new ArrayList<UserDAO>();
			  cursor = database.query(UsersSQLBuilder.TABLE_USER,allColumns, null, null, null, null, null);
			  cursor.moveToFirst();
			   while (!cursor.isAfterLast()) {
				   UserDAO user = cursorToUser(cursor);
				   users.add(user);
				   System.out.println("IN GETALLUSERS: "+user.getLast_name());
				   cursor.moveToNext();
			   }
		    // make sure to close the cursor
		    cursor.close();
		  }
		  catch(Exception e){
			  System.out.println("In UserDataSource.getAllUsers : " +e);
		  }
	    return users;
	  }

	  private UserDAO cursorToUser(Cursor cursor) {
		  try{
			user = new UserDAO();
		    user.setId(cursor.getInt(0));
		    user.setFirst_name(cursor.getString(1));
		    user.setLast_name(cursor.getString(2));
		    user.setEmail(cursor.getString(3));
		    user.setCreated_date(cursor.getString(5));
		  }
		  catch(Exception e){
			  System.out.println("In UserDataSource.curcorToUser :" +e);
		  }
	    return user;
	  }
	
}