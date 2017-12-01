
package ghadeer.android.baby.model;

//import java.sql.Date;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.text.format.DateFormat;

public class Measurements {

	//Baby name
        static private String babyName;
	// number of edit
	static private int num;
	//Baby Height
	static private long Height;
	//Baby  Weight
	static private long Weight;

	public Measurements() {
	}

	public String getbabyName() {
		return babyName;
	}
	 public void setbabyName( String name) {
		 Measurements.babyName = name;
	}

	 public int getNum() {
			// TODO Auto-generated method stub
			return num;
		}

	 public void setNum( int Num) {
		 Measurements.num = Num;
	}

	 public long getHeight() {
		return Height;
	}
	 public void setHeight(long height) {
		 Measurements.Height = height;
	}
	 public long getWeight() {
		return Weight;
	}
	 public void setWeight(long weight) {
		 Measurements.Weight = weight;
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
		Measurements other = (Measurements) obj;
		if (babyName != other.babyName)
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


