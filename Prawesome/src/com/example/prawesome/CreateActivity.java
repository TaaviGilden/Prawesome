package com.example.prawesome;

import com.example.database.Activity;
import com.example.database.ActivityDataSource;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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
		
		
		Activity activity = datasource.createActivity(activityName);
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
