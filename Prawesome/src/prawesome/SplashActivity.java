package prawesome;

import java.util.concurrent.ExecutionException;

import prawesome.database.ActivityFetcher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.example.prawesome.R;

public class SplashActivity extends Activity {

	private static ActivityData[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ActivityFetcher fetcher = new ActivityFetcher();
		try {
			data = fetcher.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
