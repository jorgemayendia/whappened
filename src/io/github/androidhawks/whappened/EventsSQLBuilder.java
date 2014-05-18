package io.github.androidhawks.whappened;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EventsSQLBuilder extends SQLiteOpenHelper {

  public static final String TABLE_EVENT = "events";
  public static final String EVENT_COLUMN_ID = "_id";
  public static final String EVENT_COLUMN_COMMENT = "event_comment";
  public static final String EVENT_COLUMN_USER_ID = "event_user_id";
  public static final String EVENT_COLUMN_LOCATION = "event_location";
  public static final String EVENT_COLUMN_DATE_TIME = "event_date_time";
  public static final String EVENT_COLUMN_IS_RECOMMENDED = "event_is_recommended";


  private static final String EVENT_DATABASE_NAME = "events.db";
  private static final int EVENT_DATABASE_VERSION = 1;

  // Database creation sql statement:EVENT
  private static final String EVENT_DATABASE_CREATE = "CREATE TABLE "
      + TABLE_EVENT + "(" + EVENT_COLUMN_ID
      + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
      + EVENT_COLUMN_COMMENT + " TEXT," 
      + EVENT_COLUMN_USER_ID + " TEXT," 
      + EVENT_COLUMN_LOCATION + " TEXT," 
      + EVENT_COLUMN_DATE_TIME + " TEXT," 
      + EVENT_COLUMN_IS_RECOMMENDED + " TEXT)";
  

  public EventsSQLBuilder(Context context) {
    super(context, EVENT_DATABASE_NAME, null, EVENT_DATABASE_VERSION);
    System.out.println("in sql event builder");//debugging
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(EVENT_DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(EventsSQLBuilder.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
    onCreate(db);
  }

} 