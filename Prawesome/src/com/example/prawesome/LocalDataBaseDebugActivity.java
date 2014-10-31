package com.example.prawesome;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.database.Activity;
import com.example.database.DataSource;
import com.example.database.DatabaseHelper;

public class LocalDataBaseDebugActivity extends ActionBarActivity {
	private DataSource datasource;
	
	public void restore_ldb_activities(View v) {
		try {
			datasource = new DataSource(this);
			datasource.open();
			datasource.deleteAllFrom(DatabaseHelper.TABLE_ACTIVITIES);
			
			String[] activities = new String[] {"do_that","do_this","do_whatever"};
			for(int i = 0;i<3;i++){
				if(!(datasource.verification(activities[i]))){
					datasource.createActivity(activities[i],"description", "location", 15, 20);
				}
			}			
			datasource.close();
			Toast.makeText(this, "Table restored to hardcode" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	public void restore_ldb_suggestions(View v) {
		try {
			datasource = new DataSource(this);
			datasource.open();
			datasource.deleteAllFrom(DatabaseHelper.TABLE_SUGGESTIONS);
			String[] activities = new String[] {"suggestion_1","suggestion_2","suggestion_2"};
			for(int i = 0;i<3;i++){
				datasource.createSugestion(activities[i],"description", "location", 15, 20);
			}			
			datasource.close();
			Toast.makeText(this, "Table restored to hardcode" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	public void clean_ldb_activties(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			datasource.deleteAllFrom(DatabaseHelper.TABLE_ACTIVITIES);
			datasource.close();
			Toast.makeText(this, "Table cleaned" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}	
	}
	
	public void clean_ldb_suggestions(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			datasource.deleteAllFrom(DatabaseHelper.TABLE_SUGGESTIONS);
			datasource.close();
			Toast.makeText(this, "Table cleaned" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}	
	}
	
	
	public void list_ldb_activities_elements(View v) {
		try {
			datasource = new DataSource(this);
			datasource.open();
			List<Activity> allActivities = datasource.getAllActivities();
			datasource.close();
			String allActivitiesString = "All activities:";
			for (Activity activity : allActivities) {
				allActivitiesString += "\n" + activity.toString();
			}
			Toast.makeText(this, allActivitiesString,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong:" + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	public void list_ldb_suggestion_elements(View v) {
		try {
			datasource = new DataSource(this);
			datasource.open();
			List<Activity> allActivities = datasource.getAllSugsestions();
			datasource.close();
			String allActivitiesString = "All suggestions:";
			for (Activity activity : allActivities) {
				allActivitiesString += "\n" + activity.toString();
			}
				
			Toast.makeText(this, allActivitiesString,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong:" + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	
	public void count_ldb_activities(View v) {		
		try {
			datasource = new DataSource(this);
			datasource.open();
			int i = datasource.elementsCount(DatabaseHelper.TABLE_ACTIVITIES);
			datasource.close();
			Toast.makeText(this, "Elements count: " + i,Toast.LENGTH_LONG).show();			
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	public void count_ldb_suggestions(View v) {		
		try {
			datasource = new DataSource(this);
			datasource.open();
			int i = datasource.elementsCount(DatabaseHelper.TABLE_SUGGESTIONS);
			datasource.close();
			Toast.makeText(this, "Elements count: " + i,Toast.LENGTH_LONG).show();			
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	public void first_suggestion_to_activities(View v){
		try {
			//TODO
			datasource = new DataSource(this);
			datasource.open();
			datasource.suggestion_to_activity();
			datasource.close();
			Toast.makeText(this, "1st suggestion moved to activities",Toast.LENGTH_LONG).show();			
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_data_base_debug_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local_data_base_debug_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		else if (id == R.id.action_db) {
			Intent create = new Intent(this, DataBaseDebugActivity.class);
			startActivity(create);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
