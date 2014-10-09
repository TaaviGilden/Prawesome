package com.example.prawesome;

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
import com.example.database.ActivityDataSource;


public class MainActivity extends FragmentActivity {
	private ActivityDataSource datasource;
	List<Activity> values;
	Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new ActivityDataSource(this);
		datasource.open();
		String[] activities = new String[] {"do_that","do_this","do_whatever"};
		for(int i = 0;i<3;i++){
			//Log.d("foract", activities[i]);
			activity = datasource.createActivity(activities[i]);
//			if(!datasource.dbContains(activities[i])){
//
//			}

		}
		
		values = datasource.getAllActivities();
		
		for(int i =0;i<values.size();i++){
	    	Log.d("calues1",values.get(i).toString());
	    }
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

            case 0: return FirstFragment.newInstance(values.get(0).toString());
            case 1: return SecondFragment.newInstance(values.get(1).toString());
            case 2: return ThirdFragment.newInstance("ThirdFragment, Instance 1");
            case 3: return ThirdFragment.newInstance("ThirdFragment, Instance 2");
            case 4: return ThirdFragment.newInstance("ThirdFragment, Instance 3");
            case 5: return FirstFragment.newInstance(values.get(2).toString());
            default: return ThirdFragment.newInstance("ThirdFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 6;
        }       
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
	
	public void createActivity(View view){
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
		}
		else if(id == R.id.add_activity){
			Intent create = new Intent(this, CreateActivity.class);
			startActivity(create);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}


