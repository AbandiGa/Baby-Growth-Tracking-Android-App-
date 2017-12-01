package ghadeer.android.baby;

import java.util.Date;

import android.content.Context;
import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

	public class MySQLiteHelper extends SQLiteOpenHelper {
		//Table name
		public static final String TABLE_BABY = "Baby_table";
		//Table columns
		public static final String COLUMN_NAME = "_name";
		public static final String COLUMN_BIRTH_DATE = "birth_date";
		public static final String COLUMN_INITIAL_HEIGHT = "initail_height";
		public static final String COLUMN_INITIAL_WEIGHT = "initail_weight";
		//Table name
		public static final String TABLE_MEASUREMENTS = "Measurements_table";
		//Table columns
		public static final String COLUMN_HEIGHT = "_height";
		public static final String COLUMN_WEIGHT = "_weight";
		public static final String COLUMN_NUM = "_num";
		public static final String COLUMN_BABY_NAME = "_Babyname";	
		
		//Database file name
		private static final String DATABASE_NAME = "Baby.db";
		//Database version
		private static final int DATABASE_VERSION = 1;
		
		static final String viewBaby ="ViewBaby";
		
		//Constructor
		public MySQLiteHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Creating Tables
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	    	
	       db.execSQL("CREATE TABLE " + TABLE_BABY + "("
	                + COLUMN_NAME+ " TEXT PRIMARY KEY," + COLUMN_BIRTH_DATE + " DATE,"
	                + COLUMN_INITIAL_HEIGHT + " LONG" +COLUMN_INITIAL_WEIGHT+ "LONG");
	       
	      
	       db.execSQL("CREATE TABLE " + TABLE_MEASUREMENTS + "("
	               + COLUMN_NUM+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_HEIGHT + " LONG" +COLUMN_WEIGHT+ "LONG"+COLUMN_BABY_NAME+"TEXT NOT NULL,FOREIGN KEY ("+ COLUMN_BABY_NAME +") REFERENCES" +TABLE_BABY+" ("+ COLUMN_NAME +"));");
	       
	        //db.execSQL(DATABASE_CREATE);
	       db.execSQL("CREATE TRIGGER fk_babyname_measurmentname1" +
	    		    " BEFORE INSERT "+
	    		    " ON "+TABLE_MEASUREMENTS+
	    		    
	    		    " FOR EACH ROW BEGIN"+
	    		    " SELECT CASE WHEN ((SELECT "+ COLUMN_NAME +" FROM "+TABLE_BABY+
	    		  " WHERE "+COLUMN_NAME+"=new."+COLUMN_BABY_NAME+" ) IS NULL)"+
	    		    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
	    		    "  END;");
	       
	       db.execSQL("CREATE TRIGGER fk_babyname_measurmentname2" +
	    		    " BEFORE DELETE"+
	    		    " ON "+TABLE_BABY+
	    		    " FOR EACH ROW BEGIN"+
	    		    " SELECT CASE WHEN ((SELECT "+ COLUMN_BABY_NAME +" FROM "+TABLE_MEASUREMENTS+
	    		  " WHERE "+COLUMN_BABY_NAME+"=OLD."+COLUMN_NAME+" ) IS NOT NULL)"+
	    		    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
	    		    "  END;");
	       
	  	 db.execSQL("CREATE TRIGGER fk_babyname_measurmentname3" +
	    		    " BEFORE UPDATE"+
	    		    " ON "+TABLE_MEASUREMENTS+
	    		    
	    		    " FOR EACH ROW BEGIN"+
	    		    " SELECT CASE WHEN ((SELECT "+ COLUMN_NAME +" FROM "+TABLE_BABY+
	    		  " WHERE "+COLUMN_NAME+"=new."+COLUMN_BABY_NAME+" ) IS NULL)"+
	    		    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
	    		    "  END;");
	  	 
	  	 // PRIMARY KEY CONSTRAINT
	  	 
	  	 db.execSQL("CREATE TRIGGER fk_babyname_measurmentname4" +
	    		    " BEFORE UPDATE"+
	    		    " ON "+TABLE_BABY+
	    		    
	    		    " FOR EACH ROW BEGIN"+
	    		    " SELECT CASE WHEN ((SELECT "+ COLUMN_BABY_NAME +" FROM "+TABLE_MEASUREMENTS+
	    		  " WHERE "+COLUMN_BABY_NAME+"=new."+COLUMN_NAME+" ) IS NULL)"+
	    		    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
	    		    "  END;");
	  	 // Create view
	  	  db.execSQL("CREATE VIEW "+viewBaby+
	  		    " AS SELECT "+TABLE_MEASUREMENTS+"."+COLUMN_NUM+" AS _id,"+
	  		    " "+TABLE_MEASUREMENTS+"."+COLUMN_HEIGHT+","+
	  		    " "+TABLE_MEASUREMENTS+"."+COLUMN_WEIGHT+","+
	  		    " "+TABLE_BABY+"."+COLUMN_INITIAL_HEIGHT+","+
	  		    " "+TABLE_BABY+"."+COLUMN_INITIAL_WEIGHT+" "+
	  		    " FROM "+TABLE_MEASUREMENTS+" JOIN "+TABLE_BABY+
	  		    " ON "+TABLE_MEASUREMENTS+"."+COLUMN_BABY_NAME+" ="+TABLE_BABY+"."+COLUMN_NAME
	  		    );
	    }
	    
		 
		
		/** Called on first creation of the database. */
		/**@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL(DATABASE_CREATE);
		}*/
		/** Called when the DATABASE_VERSION is changed to higher version */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(MySQLiteHelper.class.getName(),
					"Upgrading database from version " + oldVersion + " to "
							+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS" + TABLE_BABY + TABLE_MEASUREMENTS);
			 db.execSQL("DROP TRIGGER IF EXISTS fk_babyname_measurmentname1");
			 db.execSQL("DROP TRIGGER IF EXISTS fk_babyname_measurmentname2");
			 db.execSQL("DROP TRIGGER IF EXISTS fk_babyname_measurmentname3");
			 db.execSQL("DROP TRIGGER IF EXISTS fk_babyname_measurmentname4");
			  db.execSQL("DROP VIEW IF EXISTS "+viewBaby);
			onCreate(db);
		}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * @return
	 */
	public long getLastInsertId() {
		 long index = 0;
		    SQLiteDatabase sdb = getReadableDatabase();
		    Cursor cursor = sdb.query(
		            "sqlite_sequence",
		            new String[]{"seq"},
		            "name = ?",
		            new String[]{MySQLiteHelper.TABLE_MEASUREMENTS},
		            null,
		            null,
		            null,
		            null
		    );
		    if (cursor.moveToFirst()) {
		        index = cursor.getLong(cursor.getColumnIndex("seq"));
		    }
		    cursor.close();
		    return index;
	}

}
