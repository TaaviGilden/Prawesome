package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	  public static final String TABLE_ACTIVITIES = "activities";
	  public static final String TABLE_SUGESTIONS = "sugestions";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_ACTIVITY = "activity";
	  public static final String COLUMN_DESCRIPTION = "description";
	  public static final String COLUMN_LOCATION = "location";
	  public static final String COLUMN_COST = "cost";
	  public static final String COLUMN_TIMEFRAME = "timeframe";

	  private static final String DATABASE_NAME = "activities.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String CREATE_TABLE_ACTIVITIES = "create table "
	      + TABLE_ACTIVITIES + "(" 
			  + COLUMN_ID + " integer primary key autoincrement, " 
			  + COLUMN_ACTIVITY + " text not null, "
			  + COLUMN_DESCRIPTION + " text not null, " 
			  + COLUMN_LOCATION + " text not null, "
			  + COLUMN_COST + " integer not null, "
			  + COLUMN_TIMEFRAME + " integer not null);"; 
	  
	  private static final String CREATE_TABLE_SUGESTIONS = "create table "
		      + TABLE_SUGESTIONS + "(" 
				  + COLUMN_ID + " integer primary key autoincrement, " 
				  + COLUMN_ACTIVITY + " text not null, "
				  + COLUMN_DESCRIPTION + " text not null, " 
				  + COLUMN_LOCATION + " text not null, "
				  + COLUMN_COST + " integer not null, "
				  + COLUMN_TIMEFRAME + " integer not null);";

	  public DatabaseHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(CREATE_TABLE_ACTIVITIES);
	    database.execSQL(CREATE_TABLE_SUGESTIONS);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
	    onCreate(db);
	  }

}
