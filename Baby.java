package ghadeer.android.baby.model;

//import java.sql.Date;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.text.format.DateFormat;

public class Baby {
	
	//Baby name
	static private String name;
	//Baby birthDate
	static private Date birthDate;
	//Baby initial Height
	static private long initialHeight;
	//Baby initial Weight
	static private long initialWeight;

	public Baby() {
	}

	 public String getName() {
		return name;
	}
	 public void setName( String name) {
		 Baby.name = name;
	}
	 public Date getBirthDate() {
		return birthDate;
	}
	  public void setBirthDate(java.util.Date date) {
		 //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//String dateToString = dateFormat.format(this.birthDate);
		//dateToString = BirthDate;
		  this.birthDate = date;

	}
	 public long getInitialHeight() {
		return initialHeight;
	}
	 public void setInitialHeight(long initialHeight) {
		Baby.initialHeight = initialHeight;
	}
	 public long getInitialWeight() {
		return initialWeight;
	}
	 public void setInitialWeight(long initialWeight) {
		 Baby.initialWeight = initialWeight;
	}

	/**
	 * @param obj the object that you want to compare it with this object.<br/>
	 * @return if obj equals this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Baby other = (Baby) obj;
		if (name != other.name)
			return false;
		return true;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
