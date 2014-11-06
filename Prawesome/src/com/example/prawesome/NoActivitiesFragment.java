package com.example.prawesome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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