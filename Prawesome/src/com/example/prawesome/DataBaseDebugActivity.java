package com.example.prawesome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class DataBaseDebugActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data_base_debug);
	}
	
	public void jsonQuery(View v) {
	    try {
	        // http://androidarabia.net/quran4android/phpserver/connecttoserver.php

	        // Log.i(getClass().getSimpleName(), "send  task - start");
	        HttpParams httpParams = new BasicHttpParams();
	        //Timeout millisecs
	        HttpConnectionParams.setConnectionTimeout(httpParams,
	                2000);
	        HttpConnectionParams.setSoTimeout(httpParams, 2000);
	        //
	        HttpParams p = new BasicHttpParams();
	        // p.setParameter("name", pvo.getName());
	        p.setParameter("user", "1");

	        // Instantiate an HttpClient
	        HttpClient httpclient = new DefaultHttpClient(p);
	        String url = "http://ec2-54-69-156-10.us-west-2.compute.amazonaws.com/getactivities.php";
	        HttpPost httppost = new HttpPost(url);

	        // Instantiate a GET HTTP method
	        try {
	            Log.i(getClass().getSimpleName(), "send  task - start");
	            //
	            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
	                    2);
	            nameValuePairs.add(new BasicNameValuePair("user", "1"));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            ResponseHandler<String> responseHandler = new BasicResponseHandler();
	            String responseBody = httpclient.execute(httppost,
	                    responseHandler);
	            // Parse
	            JSONObject json = new JSONObject(responseBody);
	            JSONArray jArray = json.getJSONArray("posts");
	            ArrayList<HashMap<String, String>> mylist = 
	                   new ArrayList<HashMap<String, String>>();

	            for (int i = 0; i < jArray.length(); i++) {
	                HashMap<String, String> map = new HashMap<String, String>();
	                JSONObject e = jArray.getJSONObject(i);
	                String s = e.getString("post");
	                JSONObject jObject = new JSONObject(s);

	                map.put("id", jObject.getString("id"));
	                map.put("name", jObject.getString("name"));
	                map.put("timelimitstart", jObject.getString("timelimitstart"));
	                map.put("timelimitend", jObject.getString("timelimitend"));
	                map.put("location", jObject.getString("location"));
	                map.put("description", jObject.getString("description"));

	                mylist.add(map);
	            }
	            Toast.makeText(this, responseBody, Toast.LENGTH_LONG).show();

	        } catch (ClientProtocolException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        // Log.i(getClass().getSimpleName(), "send  task - end");

	    } catch (Throwable t) {
	        Toast.makeText(this, "Request failed: " + t.toString(),
	                Toast.LENGTH_LONG).show();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data_base_debug, menu);
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
		else if (id == R.id.action_ldb) {
			Intent create = new Intent(this, LocalDataBaseDebugActivity.class);
			startActivity(create);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
