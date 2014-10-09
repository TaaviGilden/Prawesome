package com.example.database;

public class Activity {
	  private long id;
	  private String activity;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getActivity() {
	    return activity;
	  }

	  public void setActivity(String activity) {
	    this.activity = activity;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return activity;
	  }
	} 
