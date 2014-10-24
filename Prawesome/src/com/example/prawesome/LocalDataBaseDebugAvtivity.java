package com.example.prawesome;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LocalDataBaseDebugAvtivity extends ActionBarActivity {
	
	public void clean_ldb(View v) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		Toast.makeText(this, "Nothing here yet" ,Toast.LENGTH_LONG).show();
	}
	
	public void restore_ldb(View v) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		Toast.makeText(this, "Nothing here yet" ,Toast.LENGTH_LONG).show();
	}
	
	public void list_ldb_elements(View v) {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		Toast.makeText(this, "Nothing here yet" ,Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_data_base_debug_avtivity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.local_data_base_debug_avtivity, menu);
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
