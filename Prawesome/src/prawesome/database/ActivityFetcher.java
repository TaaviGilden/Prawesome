package prawesome.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import prawesome.ActivityData;
import android.os.AsyncTask;

import com.google.gson.Gson;

public class ActivityFetcher extends AsyncTask<Void, Void, ActivityData[]> {
	
	//TO-DO Add ?userid= to url end
	public static final String SERVER_URL = "http://ec2-54-149-59-127.us-west-2.compute.amazonaws.com/getactivities.php";

	@Override
	protected ActivityData[] doInBackground(Void... params) {
		Gson gson = new Gson();

		String fromURL = null;
		ActivityData[] data = null;
		try {
			fromURL = readUrl(SERVER_URL);
			fromURL = fromURL.replace("<meta charset=\"UTF-8\">", "");
			data = gson.fromJson(fromURL, ActivityData[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}		

		return data;
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