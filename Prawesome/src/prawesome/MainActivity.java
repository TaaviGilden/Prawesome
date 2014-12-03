package prawesome;

import java.util.HashMap;
import java.util.List;

import com.prawesome.R;

import prawesome.database.Activity;
import prawesome.database.ActivityOfflineFetcher;
import prawesome.database.CheckNetClass;
import prawesome.database.DataSource;
import prawesome.database.DatabaseHelper;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
	private DataSource datasource;
	private long currentActivtyId;
	static List<Activity> values;
	private ActivityData[] data;

	public static HashMap<String, List<String>> activityDetails;

	
	public void open_internet_settings(View v) {
		/*
		Intent intent= new Intent();
		   ComponentName cName = new ComponentName("com.android.phone","com.android.phone.NetworkSetting");
		   intent.setComponent(cName); 
		   */
		startActivityForResult(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		data = SplashActivity.getData();
		
		datasource = new DataSource(this);
		datasource.open();
		datasource.deleteAllFrom(DatabaseHelper.TABLE_ACTIVITIES);
		//Adding activities from external to local database
		if (data == null){
			int n = DataSource.elementsCount(DatabaseHelper.TABLE_OFFLINE);
			for (int i = 0; i < 10 && i < n; i++) {
				datasource.offline_to_activity();
			}
		}else if(data.length > 0){
			for (ActivityData i : data) {
				datasource.createActivity(Long.parseLong(i.id), i.name,i.description, i.location, Integer.parseInt(i.cost), i.esttime);
				}
		}
		if (CheckNetClass.checknetwork(getApplicationContext())) {			
			new ActivityOfflineFetcher(datasource).execute();
		}

		// values =
		// datasource.getAllActivitiesFrom(DatabaseHelper.TABLE_ACTIVITIES);
		values = datasource.getAllActivitiesNotIgnored();
		Log.d("info", Integer.toString(values.size()));
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}

	private class MyPagerAdapter extends FragmentStatePagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int pos) {
			while (values.size() > 0 && pos <= values.size()) {
				currentActivtyId = values.get(pos).getId();
				return FirstFragment.newInstance(values.get(pos).getName());
			}
			if (!CheckNetClass.checknetwork(getApplicationContext())){
				return NoConectionFragment.newInstance();
			}
			return NoActivitiesFragment.newInstance();
			
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
		} else if (id == R.id.action_ldb) {
			Intent create = new Intent(this, LocalDataBaseDebugActivity.class);
			startActivity(create);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
