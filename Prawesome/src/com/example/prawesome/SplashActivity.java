package com.example.prawesome;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	private static ActivityData[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//ActivityFetcher fetcher = new ActivityFetcher();
		//fetcher.execute();

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				finish();
				Intent openMainActivity = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(openMainActivity);

			}
		}, 2000);
	}

	private static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	private class ActivityFetcher extends AsyncTask<Void, Void, ActivityData[]> {

		public static final String SERVER_URL = "http://ec2-54-69-156-10.us-west-2.compute.amazonaws.com/getactivities.php";

		@Override
		protected ActivityData[] doInBackground(Void... params) {
			Gson gson = new Gson();

			String fromURL = null;
			try {
				fromURL = readUrl(SERVER_URL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			fromURL = fromURL.replace("<meta charset=\"UTF-8\">", "");
			data = gson.fromJson(fromURL, ActivityData[].class);

			return null;
		}
	}

	public static ActivityData[] getData() {
		return data;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}
}
