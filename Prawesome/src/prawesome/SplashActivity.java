package prawesome;

import java.util.concurrent.ExecutionException;

import prawesome.database.ActivityFetcher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prawesome.R;

public class SplashActivity extends Activity {

	private static ActivityData[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		


		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				try {
					ActivityFetcher fetcher = new ActivityFetcher();
					data = fetcher.execute().get();
					for(ActivityData i : data){
						Log.d("splash",i.name);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
				Intent openMainActivity = new Intent(SplashActivity.this,
						MainActivity.class);
				startActivity(openMainActivity);

			}
		}, 2000);
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
