package com.example.prawesome;

import java.util.List;

import com.example.database.Activity;
import com.example.database.ActivityDataSource;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LocalDataBaseDebugActivity extends ActionBarActivity {
	private ActivityDataSource datasource;
	
	public void restore_ldb(View v) {
		// TODO no sure how the hard coded is done
		//		should be done differently 
		try {
			
		} catch (Exception e) {
				// TODO: handle exception					
		}
		Toast.makeText(this, "Nothing here yet" ,Toast.LENGTH_LONG).show();
	}
	
	public void clean_ldb(View v) {
		// TODO cleans right but hard coding implementation 
		//		generates them back after app restart
		try {
  			datasource = new ActivityDataSource(this);
			datasource.open();
			datasource.deleteAllActivities();
			datasource.close();
			Toast.makeText(this, "Table restored to hardcode" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	
	}
		
	
	public void list_ldb_elements(View v) {
		try {
			datasource = new ActivityDataSource(this);
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
	
	public void count_ldb(View v) {		
		try {
			datasource = new ActivityDataSource(this);
			datasource.open();
			int i = datasource.elementsCount();
			datasource.close();
			Toast.makeText(this, "Elements count: " + i,Toast.LENGTH_LONG).show();			
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
		return super.onOptionsItemSelected(item);
	}
}
