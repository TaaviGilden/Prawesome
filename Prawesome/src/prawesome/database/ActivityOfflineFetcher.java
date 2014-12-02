package prawesome.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import prawesome.ActivityData;
import prawesome.database.DataSource;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

public class ActivityOfflineFetcher extends AsyncTask<Void, Integer, String> {

	public static final String SERVER_URL = "http://ec2-54-69-156-10.us-west-2.compute.amazonaws.com/getactivities.php";
	public DataSource datasource;

	public ActivityOfflineFetcher(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	protected void onPreExecute (){
        Log.d("PreExceute","On pre Exceute......");
    }
	
	protected String doInBackground(Void... arg0) {
		Gson gson = new Gson();

		String fromURL = null;
		try {
			fromURL = readUrl(SERVER_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		fromURL = fromURL.replace("<meta charset=\"UTF-8\">", "");
		ActivityData[] data = gson.fromJson(fromURL, ActivityData[].class);
				
		datasource.open();
		for (ActivityData i : data) {
			datasource.createActivityOffline(Long.parseLong(i.id), i.name,i.description, i.location, Integer.parseInt(i.cost), i.esttime);
			}
		return "done";
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

}
