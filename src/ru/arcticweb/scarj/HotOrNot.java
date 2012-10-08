package ru.arcticweb.scarj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_HOTNESS = "hotness";

	private static final String DB_NAME = "HotOrNot";
	private static final String DB_TABLE = "peoples";
	private static final int DB_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DB_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_HOTNESS + " TEXT NOT NULL );");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXIST " + DB_TABLE);
			onCreate(db);
		}
	}

	public HotOrNot(Context c) {
		ourContext = c;
	}

	public HotOrNot open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createEntry(String name, String hotness) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS, hotness);
		return ourDatabase.insert(DB_TABLE, null, cv);
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor cr = ourDatabase.query(DB_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iRow = cr.getColumnIndex(KEY_ROWID);
		int iName = cr.getColumnIndex(KEY_NAME);
		int iHotness = cr.getColumnIndex(KEY_HOTNESS);
		
		for(cr.moveToFirst();!cr.isAfterLast();cr.moveToNext()){
			result+=cr.getString(iRow) + " " + cr.getString(iName)+" " + cr.getString(iHotness) + "\n";
		}
		
		return result;
	}

	public String getName(long l) throws SQLException {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DB_TABLE, columns, KEY_ROWID+"="+l, null, null, null, null);
		if(c!=null) {
			c.moveToFirst();
			String name = c.getString(c.getColumnIndex(KEY_NAME));
			return name;
		}
		return null;
	}

	public String getHotness(long l) throws SQLException  {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_NAME, KEY_HOTNESS};
		Cursor c = ourDatabase.query(DB_TABLE, columns, KEY_ROWID+"="+l, null, null, null, null);
		if(c!=null) {
			c.moveToFirst();
			String hotness = c.getString(c.getColumnIndex(KEY_HOTNESS));
			return hotness;
		}		
		return null;
	}

	public void updateEntry (long lRow, String mName, String mHotness) throws SQLException {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_HOTNESS, mHotness);
		ourDatabase.update(DB_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
	}

	public void deleteEntry (long lRow1) throws SQLException {
		// TODO Auto-generated method stub
		ourDatabase.delete(DB_TABLE, KEY_ROWID+"="+lRow1,null);
		
	}
}