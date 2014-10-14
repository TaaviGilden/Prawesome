package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ActivityDBHelper extends SQLiteOpenHelper{
	
	  public static final String TABLE_ACTIVITIES = "activities";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_ACTIVITY = "activity";
	  public static final String COLUMN_DESCRIPTION = "description";
	  public static final String COLUMN_LOCATION = "location";
	  public static final String COLUMN_COST = "cost";
	  public static final String COLUMN_TIMEFRAME = "timeframe";

	  private static final String DATABASE_NAME = "activities.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_ACTIVITIES + "(" 
			  + COLUMN_ID + " integer primary key autoincrement, " 
			  + COLUMN_ACTIVITY + " text not null, "
			  + COLUMN_DESCRIPTION + " text not null, " 
			  + COLUMN_LOCATION + " text not null, "
			  + COLUMN_COST + " integer not null, "
			  + COLUMN_TIMEFRAME + " integer not null);"; 

	  public ActivityDBHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(ActivityDBHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
	    onCreate(db);
	  }

}
