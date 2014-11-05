package com.example.prawesome;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.DataSource;

public class DetailActivity extends Activity {
	List<com.example.database.Activity> values;
	int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Intent intent = getIntent();
		String activityName = intent.getStringExtra("Name");

		values = MainActivity.getValues();
		TextView name = (TextView) findViewById(R.id.name);
		TextView desc = (TextView) findViewById(R.id.desc);
		TextView loc = (TextView) findViewById(R.id.loc);
		TextView cost = (TextView) findViewById(R.id.cost);
		TextView time = (TextView) findViewById(R.id.time);

		for(int i = 0;i<values.size();i++){
			if(activityName.equals(values.get(i).getName().toString())){
				index = i;
				break;
			}
		}
		name.setText(values.get(index).getName().toString());
		desc.setText(values.get(index).getDescription().toString());
		loc.setText(values.get(index).getLocation().toString());
		cost.setText(Integer.toString(values.get(index).getCost()));
//		time.setText(Integer.toString(values.get(index).getEsttime()));
		time.setText(values.get(index).getEsttime());
	}
	
	public void notNow(View v){
		try {
  			DataSource datasource = new DataSource(this);
			datasource.open();
			datasource.addIgnore(values.get(index).getId(), false);
			datasource.close();
			Toast.makeText(this, "not now" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	
	public void never(View v){
		try {
  			DataSource datasource = new DataSource(this);
			datasource.open();
			datasource.addIgnore(values.get(index).getId(), true);
			datasource.close();
			Toast.makeText(this, "never" ,Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong: " + e.getMessage() ,Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
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
