package prawesome;

import com.prawesome.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoActivitiesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View v = inflater.inflate(R.layout.no_activity_fragment, container, false);
	    return v;
	}
	
	public static NoActivitiesFragment newInstance() {
	    NoActivitiesFragment f = new NoActivitiesFragment();
	    Bundle b = new Bundle();
	    f.setArguments(b);
	    return f;
	}
}