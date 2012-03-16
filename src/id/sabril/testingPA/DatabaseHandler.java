package id.sabril.testingPA;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PAapp";
	private static final String TABLE_PUSKESMAS = "puskesmas";
	private static final String KEY_NAMAPUSKESMAS = "namapuskesmas";
	private static final String KEY_LATITUDE = "latitude";
	private static final String KEY_LONGNITUDE = "longnitude";
	public Puskesmas puskesmas;
	private static String DB_PATH = "/data/data/id.sabril.testingPA/databases/";
	private final Context myContext;
	private SQLiteDatabase myDatabase;

	public DatabaseHandler(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		this.myContext = context;
	}
	
	//creating_tabel
	public void createDatabase() throws IOException{
		boolean dbExist = checkDatabase();
		if(dbExist){
			
		}else{
			this.getReadableDatabase();
			try{
				copyDatabase();
			}catch (IOException e) {
				throw new Error ("Error copying database");
			}
		}
	}
	
	private boolean checkDatabase() {
		SQLiteDatabase checkDB = null;
		try{
			String myPath = DB_PATH + DATABASE_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch (SQLiteException e) {
			
		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null ? true : false;
	}
	
	private void copyDatabase() throws IOException{
		InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
		String outFileName = DB_PATH + DATABASE_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0){
			myOutput.write(buffer,0,length);
		}
		
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}
	
	public void openDatabase() throws SQLiteException {
		String myPath = DB_PATH + DATABASE_NAME;
		myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	@Override
	public synchronized void close(){
		if(myDatabase != null)
			myDatabase.close();
		super.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		
	}
	
	//add puskesmas
	void addPuskesmas(Puskesmas puskesmas){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAMAPUSKESMAS, puskesmas.getNamaPuskesmas());
		values.put(KEY_LATITUDE, puskesmas.getLatitude());
		values.put(KEY_LONGNITUDE, puskesmas.getLongnitude());
		db.insert(TABLE_PUSKESMAS, null, values);
		db.close();
	}
	
	//get all puskesmas
	public List<Puskesmas> getAllPuskesmas() {
		List<Puskesmas> puskesmasList = new ArrayList<Puskesmas>();
		String selectQuery = "SELECT * FROM " + TABLE_PUSKESMAS ;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Puskesmas puskesmas = new Puskesmas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
				puskesmasList.add(puskesmas);
			}while(cursor.moveToNext());
		}
		return puskesmasList;
	}
	
	//get single puskesmas
	Puskesmas getPuskesmas(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_PUSKESMAS + " WHERE _id='"+ id +"';";
		Cursor cursor = db.rawQuery(query, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		Puskesmas puskesmas = new Puskesmas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
		return puskesmas;
		
	}

	//get_search_puskesmas
	Puskesmas searchPuskesmas(String search){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_PUSKESMAS + " WHERE namapuskesmas LIKE '%"+ search +"%' LIMIT 1;";
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			Puskesmas puskesmas = new Puskesmas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
			return puskesmas;
		}
		else{
			Puskesmas puskesmas = new Puskesmas(0, null, null, null, null);
			return puskesmas;
		}
		
	}
	
}
