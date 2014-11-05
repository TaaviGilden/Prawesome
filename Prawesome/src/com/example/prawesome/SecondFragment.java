package com.example.prawesome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View v = inflater.inflate(R.layout.activity_second_fragment, container, false);
	    return v;
	}
	
	public static SecondFragment newInstance() {
	    SecondFragment f = new SecondFragment();
	    Bundle b = new Bundle();
	    f.setArguments(b);
	
	    return f;
	}
}