package com.example.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ActivityDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private ActivityDBHelper dbHelper;
	  private String[] allColumns = { ActivityDBHelper.COLUMN_ID,
	      ActivityDBHelper.COLUMN_ACTIVITY };

	  public ActivityDataSource(Context context) {
	    dbHelper = new ActivityDBHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Activity createActivity(String activity) {
	    ContentValues values = new ContentValues();
	    values.put(ActivityDBHelper.COLUMN_ACTIVITY, activity );
	    long insertId = database.insert(ActivityDBHelper.TABLE_ACTIVITIES, null,
	        values);
	    Cursor cursor = database.query(ActivityDBHelper.TABLE_ACTIVITIES,
	        allColumns, ActivityDBHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Activity newActivity = cursorToActivity(cursor);
	    cursor.close();
	    return newActivity;
	  }

	  public void deleteActivity(Activity  activity ) {
	    long id = activity.getId();
	    System.out.println("Activity deleted with id: " + id);
	    database.delete(ActivityDBHelper.TABLE_ACTIVITIES, ActivityDBHelper.COLUMN_ID
	        + " = " + id, null);
	  }
	  
	  public boolean verification(String activity) {
		    Cursor c = database.rawQuery("SELECT 1 FROM "+ActivityDBHelper.TABLE_ACTIVITIES+" WHERE "+ActivityDBHelper.COLUMN_ACTIVITY+"=?", new String[] {activity});
		    boolean exists = c.moveToFirst();
		    c.close();
		    return exists;
		}
	  
	  public void deleteAllActivities(){
		  database.delete(ActivityDBHelper.TABLE_ACTIVITIES, null, null);
	  }

	  public List<Activity> getAllActivities() {
	    List<Activity> activities = new ArrayList<Activity>();

	    Cursor cursor = database.query(ActivityDBHelper.TABLE_ACTIVITIES,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    Activity activity = cursorToActivity(cursor);
	      activities.add(activity);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return activities;
	  }

	  private Activity cursorToActivity(Cursor cursor) {
		Activity activity = new Activity();
	    activity.setId(cursor.getLong(0));
	    activity.setActivity(cursor.getString(1));
	    return activity;
	  }

}
