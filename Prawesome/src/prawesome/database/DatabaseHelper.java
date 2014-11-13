package prawesome.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
	
	  public static final String TABLE_ACTIVITIES = "activities";
	  public static final String TABLE_SUGGESTIONS = "suggestions";
	  public static final String TABLE_OFFLINE = "offline";
	  public static final String TABLE_IGNORE = "ignore";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "activity";
	  public static final String COLUMN_DESCRIPTION = "description";
	  public static final String COLUMN_LOCATION = "location";
	  public static final String COLUMN_COST = "cost";
	  public static final String COLUMN_ESTTIME = "esttime";
	  public static final String COLUMN_TIMELIMITSTART = "start";
	  public static final String COLUMN_TIMELIMITEND = "end";
	  public static final String COLUMN_ACTIVITY_ID = "activity_id";
	  public static final String COLUMN_ACTIVITY_STATUS = "status";

	  private static final String DATABASE_NAME = "activities.db";
	  
	  //IF YOU CHANGE SOMETHING IN DATABASE, UPDATE VERSION
	  private static final int DATABASE_VERSION = 11;

	  // Database creation sql statement
	  private static final String CREATE_TABLE_ACTIVITIES = "create table "
			  + TABLE_ACTIVITIES + "(" 
			  + COLUMN_ID + " integer, " 
			  + COLUMN_NAME + " text not null, "
			  + COLUMN_DESCRIPTION + " text not null, " 
			  + COLUMN_LOCATION + " text not null, "
			  + COLUMN_COST + " integer not null, "
			  + COLUMN_ESTTIME + " text not null, "
			  + COLUMN_TIMELIMITSTART + " text not null, "
			  + COLUMN_TIMELIMITEND + " text not null);"; 
	  
	  private static final String CREATE_TABLE_SUGGESTIONS = "create table "
		      + TABLE_SUGGESTIONS + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_NAME + " text not null, "
			  + COLUMN_DESCRIPTION + " text not null, " 
			  + COLUMN_LOCATION + " text not null, "
			  + COLUMN_COST + " integer not null, "
			  + COLUMN_ESTTIME + " text not null, "
			  + COLUMN_TIMELIMITSTART + " text not null, "
			  + COLUMN_TIMELIMITEND + " text not null);"; 
	  
	  private static final String CREATE_TABLE_OFFLINE = "create table "
		      + TABLE_OFFLINE + "(" 
		      + COLUMN_ID + " integer, " 
		      + COLUMN_NAME + " text not null, "
			  + COLUMN_DESCRIPTION + " text not null, " 
			  + COLUMN_LOCATION + " text not null, "
			  + COLUMN_COST + " integer not null, "
			  + COLUMN_ESTTIME + " text not null, "
			  + COLUMN_TIMELIMITSTART + " text not null, "
			  + COLUMN_TIMELIMITEND + " text not null);";
	  
	  private static final String CREATE_TABLE_IGNORE = "create table "
			  + TABLE_IGNORE + "("
			  + COLUMN_ID + " integer primary key autoincrement, "
			  + COLUMN_ACTIVITY_ID + " integer, "
			  + COLUMN_ACTIVITY_STATUS + " integer"
			  + ");";
	  
	  public DatabaseHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(CREATE_TABLE_ACTIVITIES);
	    database.execSQL(CREATE_TABLE_SUGGESTIONS);
	    database.execSQL(CREATE_TABLE_IGNORE);
	    database.execSQL(CREATE_TABLE_OFFLINE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(DatabaseHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUGGESTIONS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_IGNORE);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFLINE);	    
	    onCreate(db);
	  }

}
