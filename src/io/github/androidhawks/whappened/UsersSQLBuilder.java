package io.github.androidhawks.whappened;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsersSQLBuilder extends SQLiteOpenHelper {

  public static final String TABLE_USER = "user";
  public static final String USER_COLUMN_ID = "_id";
  public static final String USER_COLUMN_FIRST_NAME = "user_first_name";
  public static final String USER_COLUMN_LAST_NAME = "user_last_name";
  public static final String USER_COLUMN_EMAIL = "user_email";
  public static final String USER_COLUMN_PASSWORD = "user_password";
  public static final String USER_COLUMN_CREATED_DATE = "user_created_date";
  public static final String USER_COLUMN_PROFILE = "user_profile";

  private static final String USER_DATABASE_NAME = "users.db";
  private static final int USER_DATABASE_VERSION = 1;

   
  // Database creation sql statement:USER
  private static final String USER_DATABASE_CREATE = "CREATE TABLE "+TABLE_USER+"( " 
		  + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
	      + USER_COLUMN_FIRST_NAME + " TEXT," 
	      + USER_COLUMN_LAST_NAME + " TEXT," 
	      + USER_COLUMN_EMAIL + " TEXT," 
	      + USER_COLUMN_PASSWORD + " TEXT," 
	      + USER_COLUMN_CREATED_DATE + " TEXT)";
	      //+ USER_COLUMN_PROFILE + " TEXT"
  		  //+ USER_COLUMN_PROFILE + " TEXT)";

  public UsersSQLBuilder(Context context) {
    super(context, USER_DATABASE_NAME, null, USER_DATABASE_VERSION);
    //System.out.println("in sql builder");//debugging
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(USER_DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(UsersSQLBuilder.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    onCreate(db);
  }

} 