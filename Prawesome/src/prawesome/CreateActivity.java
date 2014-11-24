package prawesome;

import com.example.prawesome.R;

import prawesome.database.DataSource;
import prawesome.database.DatabaseHelper;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;


public class CreateActivity extends ActionBarActivity {
	private DataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
	}

	public void submitActivity(View view) {
		datasource = new DataSource(this);
		datasource.open();
		EditText name = (EditText) findViewById(R.id.non_to_show_message);
		EditText desc = (EditText) findViewById(R.id.Description);
		EditText loc = (EditText) findViewById(R.id.Location);
		EditText cost = (EditText) findViewById(R.id.Cost);
		EditText time = (EditText) findViewById(R.id.Timeframe);
		String activityName = name.getText().toString();
		String activityDesc = desc.getText().toString();
		String activityLoc = loc.getText().toString();
		String activityCost = cost.getText().toString();
		String activityTime = time.getText().toString();
		// int activityCost = Integer.parseInt(cost.getText().toString());
		// activityTime = Integer.parseInt(time.getText().toString());

		if (activityName.matches("") || activityDesc.matches("")
				|| activityLoc.matches("") || activityCost.matches("")
				|| activityTime.matches("")) {
			// TO DO -- got to figure out what's inside EditText when it's
			// empty.
			Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT)
					.show();
			return;

		} else if (!(datasource.verification(activityName))) {
			datasource.createSuggestion(activityName, activityDesc, activityLoc,
					Integer.parseInt(activityCost),
					activityTime);

			Context context = getApplicationContext();
			CharSequence text = "Activity created!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
			toast.show();
		} else {
			Context context = getApplicationContext();
			CharSequence text = "Activity with this name already exists!";
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
			toast.show();
		}

		datasource.close();
		
		//Doesn't work
		time.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_DPAD_CENTER:
					case KeyEvent.KEYCODE_ENTER:
						submitActivity(v);
						return true;
					default:
						break;
					}
				}
				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
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
