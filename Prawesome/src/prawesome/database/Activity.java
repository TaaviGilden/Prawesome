package prawesome.database;

public class Activity {
	  private long id;
	  private String name;
	  private String description;
	  private String location;
	  private int cost;
	  private String esttime;
	  private String timelimitstart;
	  private String timelimitend;

	  public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEsttime(String esttime) {
		this.esttime = esttime;
	}

	public String getTimelimitstart() {
		return timelimitstart;
	}

	public void setTimelimitstart(String timelimitstart) {
		this.timelimitstart = timelimitstart;
	}

	public String getTimelimitend() {
		return timelimitend;
	}

	public void setTimelimitend(String timelimitend) {
		this.timelimitend = timelimitend;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getEsttime() {
		return esttime;
	}


	public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getName() {
	    return name;
	  }



	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return name;
	  }
	} 
