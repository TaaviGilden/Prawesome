package com.example.prawesome;

import com.google.gson.annotations.SerializedName;

public class ActivityData {
	@SerializedName("id")
	public String id;

	@SerializedName("name")
	public String name;

	@SerializedName("timelimitstart")
	public String timelimitstart;

	@SerializedName("timelimitend")
	public String timelimitend;

	@SerializedName("esttime")
	public String esttime;

	@SerializedName("location")
	public String location;

	@SerializedName("description")
	public String description;
	
	@SerializedName("cost")
	public String cost;

}
