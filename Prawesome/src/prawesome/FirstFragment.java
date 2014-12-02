package prawesome;

import com.prawesome.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {
	private Boolean ignored = false;

    public Boolean getIgnored() {
		return ignored;
	}


	public void setIgnored(Boolean ignored) {
		this.ignored = ignored;
	}

	@Override 
	public void onActivityResult(int requestCode, int resultCode, Intent data) {     
	  super.onActivityResult(requestCode, resultCode, data); 
	  switch(requestCode) { 
	    case (1) : { 
	      if (resultCode == 1) { 
	    	  ignored = true;
	    	  Log.d("ignore","pressed not now or never");
	      } else{
	    	  Log.d("ignore","left other way");
	      }    	  
	      break; 
	    } 
	  } 
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_first_fragment, container, false);

        final TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
        tv.setText(getArguments().getString("msg"));
        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("Name", tv.getText().toString());
                startActivityForResult(intent, 1);
            }
        });
        return v;
    }
    

    public static FirstFragment newInstance(String text) {

        FirstFragment f = new FirstFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}