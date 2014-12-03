package prawesome;

import java.util.List;

import com.prawesome.R;

import prawesome.database.Activity;
import prawesome.database.DataSource;
import prawesome.database.DatabaseHelper;
import prawesome.database.State;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


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
					datasource.createActivity(Long.valueOf(-i),activities[i],"description", "location",0,"");
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
				datasource.createSuggestion(activities[i],"description", "location", 15, "20");
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
	
	public void clean_ldb_ignore(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			datasource.deleteAllFrom(DatabaseHelper.TABLE_IGNORE);
			datasource.close();
			Toast.makeText(this, "Table cleaned" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}	
	}
	
	public void clean_ldb_offline(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			datasource.deleteAllFrom(DatabaseHelper.TABLE_OFFLINE);
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
			List<Activity> allActivities = datasource.getAllActivitiesFrom(DatabaseHelper.TABLE_ACTIVITIES);
			datasource.close();
			String allActivitiesString = "All activities:";
			for (Activity activity : allActivities) {
				allActivitiesString += "\n" + activity.getId() + ":\t" + activity.toString();
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
			List<Activity> allActivities = datasource.getAllActivitiesFrom(DatabaseHelper.TABLE_SUGGESTIONS);
			datasource.close();
			String allActivitiesString = "All suggestions:";
			for (Activity activity : allActivities) {
				allActivitiesString += "\n" + activity.getId() + ":\t" + activity.toString();
			}
				
			Toast.makeText(this, allActivitiesString,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong:" + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	public void list_ldb_ignore_elements(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			List<State> states = datasource.getAllStates();
			datasource.close();
			
			String allStatesString = "All ignore elements:";
			for (State state: states) {
				allStatesString += "\n" + state;
			}				
			Toast.makeText(this, allStatesString,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
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
	
	public void count_ldb_ignore(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			int i = datasource.elementsCount(DatabaseHelper.TABLE_IGNORE);
			datasource.close();
			Toast.makeText(this, "Elements count: " + i,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}	
	}
	
	public void count_ldb_offline(View v) {
		try {
  			datasource = new DataSource(this);
			datasource.open();
			int i = datasource.elementsCount(DatabaseHelper.TABLE_OFFLINE);
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
	
	public void first_offline_to_activities(View v){
		try {
			datasource = new DataSource(this);
			datasource.open();
			datasource.suggestion_to_activity();
			datasource.close();
			Toast.makeText(this, "1st offline moved to activities",Toast.LENGTH_LONG).show();			
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
