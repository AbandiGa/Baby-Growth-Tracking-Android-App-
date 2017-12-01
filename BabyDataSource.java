package ghadeer.android.baby;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ghadeer.android.baby.model.Baby;
import ghadeer.android.baby.model.Measurements;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BabyDataSource {
	// SQLite Database object
	private SQLiteDatabase database;
	// Our SQLiteOpenHelper
	private MySQLiteHelper dbHelper;
	//Database columns
	private String[] allColumns = { MySQLiteHelper.COLUMN_NAME,
			MySQLiteHelper.COLUMN_BIRTH_DATE, MySQLiteHelper.COLUMN_INITIAL_HEIGHT,
			MySQLiteHelper.COLUMN_INITIAL_WEIGHT, MySQLiteHelper.COLUMN_HEIGHT,
			MySQLiteHelper.COLUMN_WEIGHT, MySQLiteHelper.COLUMN_NUM};

	public BabyDataSource(Context context){
		dbHelper = new MySQLiteHelper(context);
	}
	/** Used to open connection with database. */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	/** Used to close connection with database. */
	public void close() {
		dbHelper.close();
	}
	/**Used to parse from Date to String and the other way around*/
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	/** Add baby to Database. */
	public Baby createBaby(Baby baby) {
		//Used to store data like : COLUMN_NAME , VALUE.
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, baby.getName());
		values.put("BIRTH_DATE" , dateFormat.format(baby.getBirthDate()));
		values.put(MySQLiteHelper.COLUMN_INITIAL_HEIGHT, baby.getInitialHeight());
		values.put(MySQLiteHelper.COLUMN_INITIAL_WEIGHT, baby.getInitialWeight());
		//Insert new Baby and get the name of it.
		long insertName = database.insert(MySQLiteHelper.TABLE_BABY, null,
				values);
		//To show how to query (To get baby by his/her name).
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BABY,
				allColumns, MySQLiteHelper.COLUMN_NAME + " = " + insertName, null,
				null, null, null);
		//Move Cursor to the first row.
		cursor.moveToFirst();
		return cursorToBaby(cursor);
	}
	/** Update Baby in Database. */
	public Baby updateBaby(Baby baby) {
		//Used to store data like : COLUMN_NAME , VALUE.
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, baby.getName());
		values.put("BIRTH_DATE" , dateFormat.format(baby.getBirthDate()));
		values.put(MySQLiteHelper.COLUMN_INITIAL_HEIGHT, baby.getInitialHeight());
		values.put(MySQLiteHelper.COLUMN_INITIAL_WEIGHT, baby.getInitialWeight());
		database.update(MySQLiteHelper.TABLE_BABY, values, MySQLiteHelper.COLUMN_NAME + "=" + baby.getName(), null);
		//To show how to query (To get contact by it name).
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BABY,
				allColumns, MySQLiteHelper.COLUMN_NAME + " = " + baby.getName(), null,
				null, null, null);
		//Move Cursor to the first row.
		cursor.moveToFirst();
		return cursorToBaby(cursor);
	}
	/** Delete BABY from Database. */
	public void deleteBaby(Baby baby) {
		System.out.println("Contact deleted with Name: " + baby.getName());
		//Delete contact by it id.
		database.delete(MySQLiteHelper.TABLE_BABY, MySQLiteHelper.COLUMN_NAME
				+ " = " + baby.getName(), null);
	}
	/** Get all BABY from Database. */
	public List<Baby> getAllBabys() {
		//Used to store all contacts.
		List<Baby> comments = new ArrayList<Baby>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BABY,
				allColumns, null, null, null, null, null);
		//Move Cursor to the first row.
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Baby baby = cursorToBaby(cursor);
			comments.add(baby);
			//Move Cursor to the next row.
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return comments;
	}
	/** Used to get baby by it name. */
	public Baby getBaby(String name){
		Cursor cursor = database.query(MySQLiteHelper.TABLE_BABY,
				allColumns, MySQLiteHelper.COLUMN_NAME + " = " + name, null,
				null, null, null);
		cursor.moveToFirst();
		return cursorToBaby(cursor);
	}

	/** used to get Last height & weight from TABLE_MEASUREMENTS by Num.*/

	 @SuppressWarnings("null")
	public long getLastHeight(){
		 long i ;
		 Measurements measurement = new Measurements();
		MySQLiteHelper sqlliteHelper = null ;
		i= sqlliteHelper.getLastInsertId();
		 String[] asColumnsToReturn = new String[] { MySQLiteHelper.COLUMN_HEIGHT};
		Cursor cursor = database.query(MySQLiteHelper.TABLE_MEASUREMENTS,asColumnsToReturn, "COLUMN_NUM = i",null,null,null,null );
		cursor.moveToFirst();
		long height = cursor.getInt(0);
		return height;
	 }

/** used to get Last h  n eight & weight from TABLE_MEASUREMENTS by Num.*/

	 @SuppressWarnings("null")
	public long getLastWeight(){
		 long i ;
		 Measurements measurement = new Measurements();
		MySQLiteHelper sqlliteHelper = null ;
		i= sqlliteHelper.getLastInsertId();  
		 String[] asColumnsToReturn = new String[] { MySQLiteHelper.COLUMN_WEIGHT};
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_MEASUREMENTS,asColumnsToReturn, "COLUMN_NUM = i",null,null,null,null );
			cursor.moveToFirst();
		long weight = cursor.getInt(1);
		return weight;
	 }

	 public int caluclateAge() throws ParseException{
		 String[] asColumnsToReturn = new String[] { MySQLiteHelper.COLUMN_BIRTH_DATE};
		 Cursor cursor = database.query(MySQLiteHelper.TABLE_BABY,asColumnsToReturn, null,null,null,null,null );
		 Date  Birthdate = dateFormat.parse(cursor.getString(1));
		 Calendar dob = Calendar.getInstance();
			dob.setTime(Birthdate);
		Calendar today = Calendar.getInstance();
		 int age = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);

	      return age;

	 }

	/** Used to get baby from Cursor to Baby Object. */
	private Baby cursorToBaby(Cursor cursor) {
		Baby baby = new Baby();
		baby.setName(cursor.getString(0));
		try {
			baby.setBirthDate(dateFormat.parse(cursor.getString(1)));
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		baby.setInitialHeight(cursor.getLong(2));
		baby.setInitialWeight(cursor.getLong(3));
		return baby;
	}

	private Measurements cursorToMEASUREMENTS(Cursor cursor) {
		Measurements m = new Measurements();
		m.setHeight(cursor.getLong(0));
		m.setWeight(cursor.getLong(1));
		m.setNum(cursor.getInt(2));
		m.setbabyName(cursor.getString(3));

		return m;
	}


}
