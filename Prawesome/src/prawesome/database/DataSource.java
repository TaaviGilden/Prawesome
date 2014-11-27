package prawesome.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

	// Database fields
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] allActivityColumns = { DatabaseHelper.COLUMN_ID,
			DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_DESCRIPTION,
			DatabaseHelper.COLUMN_LOCATION, DatabaseHelper.COLUMN_COST,
			DatabaseHelper.COLUMN_ESTTIME};
	
	private String[] allStateColumns = {DatabaseHelper.COLUMN_ID, 
			DatabaseHelper.COLUMN_ACTIVITY_ID,
			DatabaseHelper.COLUMN_ACTIVITY_STATUS};
	
	private String notInIgnore = DatabaseHelper.COLUMN_ID 
			+ " not in "
			+ "("
			+ "SELECT " + DatabaseHelper.COLUMN_ACTIVITY_ID + " FROM " 
			+ DatabaseHelper.TABLE_IGNORE
			+ ")";
	
	public DataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public void createActivity(long id,String name, String description,
			String location, int cost, String esttime){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_ID, id);
		values.put(DatabaseHelper.COLUMN_NAME, name);
		values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
		values.put(DatabaseHelper.COLUMN_LOCATION, location);
		values.put(DatabaseHelper.COLUMN_COST, cost);
		values.put(DatabaseHelper.COLUMN_ESTTIME, esttime);
		database.insert(DatabaseHelper.TABLE_ACTIVITIES, null,values);
	}
	
	public void createActivityOffline(long id,String name, String description,
			String location, int cost, String esttime, String timelimitstart, String timelimitend){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_ID, id);
		values.put(DatabaseHelper.COLUMN_NAME, name);
		values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
		values.put(DatabaseHelper.COLUMN_LOCATION, location);
		values.put(DatabaseHelper.COLUMN_COST, cost);
		values.put(DatabaseHelper.COLUMN_ESTTIME, esttime);
		values.put(DatabaseHelper.COLUMN_TIMELIMITSTART, timelimitstart);
		values.put(DatabaseHelper.COLUMN_TIMELIMITEND, timelimitend);
		database.insert(DatabaseHelper.TABLE_OFFLINE, null,values);
	}

	public void createSuggestion(String name, String description,
			String location, int cost, String esttime) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_NAME, name);
		values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
		values.put(DatabaseHelper.COLUMN_LOCATION, location);
		values.put(DatabaseHelper.COLUMN_COST, cost);
		values.put(DatabaseHelper.COLUMN_ESTTIME, esttime);
		database.insert(DatabaseHelper.TABLE_SUGGESTIONS, null,
				values);
	}

	public int elementsCount(String table) {
		String countQuery = "SELECT  * FROM " + table;
		Cursor cursor = database.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public void deleteActivity(Activity activity) {
		long id = activity.getId();
		System.out.println("Activity deleted with id: " + id);
		database.delete(DatabaseHelper.TABLE_ACTIVITIES,
				DatabaseHelper.COLUMN_ID + " = " + id, null);
	}
	
	public void deleteSuggestion(long id){
		database.delete(DatabaseHelper.TABLE_SUGGESTIONS,
				DatabaseHelper.COLUMN_ID + " = " + id, null);
	}
	
	public void deleteOffline(long id){
		database.delete(DatabaseHelper.TABLE_OFFLINE,
				DatabaseHelper.COLUMN_ID + " = " + id, null);
	}
	

	public boolean verification(String activity) {
		Cursor c = database.rawQuery("SELECT 1 FROM "
				+ DatabaseHelper.TABLE_SUGGESTIONS + " WHERE "
				+ DatabaseHelper.COLUMN_NAME + "=?",
				new String[] { activity });
		boolean exists = c.moveToFirst();
		c.close();
		return exists;
	}

	public void deleteAllFrom(String table) {
		database.delete(table, null, null);
	}
	
	public List<Activity> getAllActivitiesFrom(String table) {
		List<Activity> activities = new ArrayList<Activity>();
		
		Cursor cursor = database.query(table,
				allActivityColumns, null, null, null, null, null);
		
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
	
	public List<Activity> getAllActivitiesNotIgnored() {
		List<Activity> activities = new ArrayList<Activity>();
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_ACTIVITIES,
				allActivityColumns, notInIgnore, null, null, null, null);
		
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
	
	public List<State> getAllStates() {
		List<State> states = new ArrayList<State>();
		
		Cursor cursor = database.query(DatabaseHelper.TABLE_IGNORE,
				allStateColumns, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			State state = cursorToState(cursor);
			states.add(state);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		
		return states;		
	}
	
	public void suggestion_to_activity(){
		Activity activity = null;
		Cursor cursor = database.query(DatabaseHelper.TABLE_SUGGESTIONS,
				allActivityColumns, null, null, null, null, null);

		cursor.moveToFirst();
		activity = cursorToActivity(cursor);
		
		// THIS IS ONLY USED FOR DEBUG SO ID = -1 IS OKEY
		createActivity(-1,activity.getName(), activity.getDescription(), activity.getLocation(),
				activity.getCost(), activity.getEsttime());
		deleteSuggestion(activity.getId());
	}
	
	public void offline_to_activity(){
		Activity activity = null;
		Cursor cursor = database.query(DatabaseHelper.TABLE_OFFLINE,
				allActivityColumns, null, null, null, null, null);

		cursor.moveToFirst();
		activity = cursorToActivity(cursor);
		
		createActivity(activity.getId(),activity.getName(), activity.getDescription(), activity.getLocation(),
				activity.getCost(), activity.getEsttime(), activity.getTimelimitstart(), activity.getTimelimitend());
		deleteOffline(activity.getId());
	}
	
	public void addIgnore(long id,boolean never){
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_ACTIVITY_ID, id);	
		values.put(DatabaseHelper.COLUMN_ACTIVITY_STATUS, boolToInteger(never));
		database.insert(DatabaseHelper.TABLE_IGNORE, null,
				values);
	}
	
	private int boolToInteger(Boolean b){
		if (b) {
			return 1;
		}else {
			return 0;
		}			
	}
	
	public void deleteNotNow(){
		database.delete(DatabaseHelper.TABLE_IGNORE,
				DatabaseHelper.COLUMN_ACTIVITY_STATUS + " = " + 0, null);
	}
	
	private Activity cursorToActivity(Cursor cursor) {
		Activity activity = new Activity();
		activity.setId(cursor.getLong(0));
		activity.setName(cursor.getString(1));
		activity.setDescription(cursor.getString(2));
		activity.setLocation(cursor.getString(3));
		activity.setCost(cursor.getInt(4));
		activity.setEsttime(cursor.getString(5));
		return activity;
	}
	
	private State cursorToState(Cursor cursor){
		State state = new State();
		state.setId(cursor.getLong(0));
		state.setActivity_id(cursor.getLong(1));
		state.setNever(cursor.getInt(2));		
		return state;		
	}

}
