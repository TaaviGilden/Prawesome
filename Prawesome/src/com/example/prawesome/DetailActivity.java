package com.example.prawesome;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends Activity {
	List<com.example.database.Activity> values;

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
			if(activityName.equals(values.get(i).getActivity().toString())){
				name.setText(values.get(i).getActivity().toString());
				desc.setText(values.get(i).getDescription().toString());
				loc.setText(values.get(i).getLocation().toString());
				cost.setText(Integer.toString(values.get(i).getCost()));
				time.setText(Integer.toString(values.get(i).getTimeframe()));
			}
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
