package com.example.prawesome;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.ActivityDataSource;

public class CreateActivity extends ActionBarActivity {
	private ActivityDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		
	}
	
	public void submitActivity(View view){
		datasource = new ActivityDataSource(this);
		datasource.open();
		EditText name = (EditText) findViewById(R.id.editText1);
		EditText desc = (EditText) findViewById(R.id.Description);
		EditText loc = (EditText) findViewById(R.id.Location);
		EditText cost = (EditText) findViewById(R.id.Cost);
		EditText time = (EditText) findViewById(R.id.Timeframe);
		String activityName = name.getText().toString();
		String activityDesc = desc.getText().toString();
		String activityLoc = loc.getText().toString();
		String activityCostString = cost.getText().toString();
		String activityTimeString = time.getText().toString();
		int activityCost = Integer.parseInt(cost.getText().toString());
		int activityTime = Integer.parseInt(time.getText().toString());
		
		if(activityName.matches("")||activityDesc.matches("")||activityLoc.matches("")||activityCostString.matches("")||activityTimeString.matches("")){
			//TO DO -- got to figure out what's inside EditText when it's empty.

			Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
			return;

		}
		else if(!(datasource.verification(activityName))){
			datasource.createActivity(activityName, activityDesc, activityLoc, activityCost, activityTime);
			
			Context context = getApplicationContext();
			CharSequence text = "Activity created!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show();
		}
		else{
			Context context = getApplicationContext();
			CharSequence text = "Activity with this name already exists!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show();
		}
		
		
		datasource.close();
		
	}

	
	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
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
