package com.example.prawesome;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
		EditText ed1 = (EditText) findViewById(R.id.editText1);
		String activityName = ed1.getText().toString();
		if(activityName == ""){
			//TO DO -- got to figure out what's inside EditText when it's empty.
			Context context = getApplicationContext();
			CharSequence text = "Please insert activity name!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
			toast.show();
		}
		else if(!(datasource.verification(activityName))){
			datasource.createActivity(activityName);
			
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
