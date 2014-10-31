package com.example.prawesome;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.database.Activity;
import com.example.database.DataSource;

public class MainActivity extends FragmentActivity {
	private DataSource datasource;
	static List<Activity> values;

	public static HashMap<String, List<String>> activityDetails;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		datasource = new DataSource(this);
		datasource.open();

		values = datasource.getAllActivities();
		Log.d("info", Integer.toString(values.size()));
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			while (values.size() > 0 && pos <= values.size()) {
				// activityDetails.put(values.get(pos).getActivity(),
				// Arrays.asList(values.get(pos).getDescription(),
				// values.get(pos).getLocation(),
				// Integer.toString(values.get(pos).getCost()),
				// Integer.toString(values.get(pos).getTimeframe())));
				return FirstFragment.newInstance(values.get(pos).getActivity());
			}
			return FirstFragment.non();
			// switch(pos) {
			//
			// // case 0: return
			// FirstFragment.newInstance(values.get(0).toString());
			// // case 1: return
			// SecondFragment.newInstance(values.get(1).toString());
			// // case 2: return
			// ThirdFragment.newInstance("ThirdFragment, Instance 1");
			// // case 3: return
			// ThirdFragment.newInstance("ThirdFragment, Instance 2");
			// // case 4: return
			// ThirdFragment.newInstance("ThirdFragment, Instance 3");
			// // case 5: return
			// FirstFragment.newInstance(values.get(2).toString());
			// case 0:
			// while(i<=values.size()){
			// i++;
			// return ThirdFragment.newInstance(values.get(i).toString());
			// }
			//
			// default: return
			// ThirdFragment.newInstance("ThirdFragment, Default");
			//
			// }

			// return
			// FirstFragment.newInstance("@string/no_activities_to_show");
		}

		@Override
		public int getCount() {
			if (values.size() == 0) {
				return 1;
			}
			return values.size();
		}
	}

	public static List<Activity> getValues() {
		return values;
	}

	public void detailedView(View view) {
		Intent detail = new Intent(this, DetailActivity.class);
		startActivity(detail);
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

	public void createActivity(View view) {
		Intent create = new Intent(this, CreateActivity.class);
		startActivity(create);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		} else if (id == R.id.add_activity) {
			Intent create = new Intent(this, CreateActivity.class);
			startActivity(create);
			return true;
		} else if (id == R.id.action_about) {
			return true;
		} else if (id == R.id.action_db) {
			Intent create = new Intent(this, DataBaseDebugActivity.class);
			startActivity(create);
			return true;
		} else if (id == R.id.action_ldb) {
			Intent create = new Intent(this, LocalDataBaseDebugActivity.class);
			startActivity(create);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
