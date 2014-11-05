package com.example.database;

public class State {
	private long id;
	private long activity_id;
	private boolean never_again;
	/*
	 * never_again:
	 * True = Never show again
	 * False = Not now
	 */
	
	public State(long id, long activity_id, boolean never_again) {
		this.id = id;
		this.activity_id = activity_id;
		this.never_again = never_again;
	}	
	public State() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}
	public boolean isStatus() {
		return never_again;
	}
	public void setNever(boolean status) {
		this.never_again = status;
	}
	public void setNever(int i) {
		never_again = 1 == i;		
	}
	
	@Override
	public String toString() {
		return activity_id + " " + never_again;
	}

}
