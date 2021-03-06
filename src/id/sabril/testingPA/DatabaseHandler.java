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
	private static final String TABLE_CATEGORY = "kategori";
	private static final String INFO_KATEGORY = "info_kategori";
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
	public List<Puskesmas> getAllPuskesmas(String category) {
		List<Puskesmas> puskesmasList = new ArrayList<Puskesmas>();
		String selectQuery;
		if (category.length() == 0){
			selectQuery = "SELECT * FROM " + TABLE_PUSKESMAS;
		}
		else{
			selectQuery = "SELECT p._id, p.namapuskesmas, p.latitude, p.longnitude, p.alamat, p.telephone," +
					" ik.poli_umum, ik.poli_KIA, ik.poli_TB, ik.rawat_inap," +
					" ik.PONED, ik.UGD, ik.persalinan, ik.pojok_gizi, ik.konsultasi_kesehatan, ik.laboratorium,ik.poli_THT FROM " +TABLE_PUSKESMAS +" p INNER JOIN "+ INFO_KATEGORY +" ik ON p._id = ik.id_puskesmas" + " WHERE ik."+category+"='true'";
		}
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				Puskesmas puskesmas = new Puskesmas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
				puskesmasList.add(puskesmas);
			}while(cursor.moveToNext());
		}
		return puskesmasList;
	}
	
	//get all kategori
		public List<Kategori> getAllKategori() {
			List<Kategori> kategoriList = new ArrayList<Kategori>();
			String selectQuery = "SELECT * FROM " + TABLE_CATEGORY ;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			if(cursor.moveToFirst()){
				do{
					Kategori kategori = new Kategori(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
					kategoriList.add(kategori);
				}while(cursor.moveToNext());
			}
			return kategoriList;
		}

		//get single kategori
		Kategori getKategori(String name){
			SQLiteDatabase db = this.getReadableDatabase();
			String query = "SELECT * FROM " + TABLE_CATEGORY + " WHERE nama_kategori='"+ name +"';";
			Cursor cursor = db.rawQuery(query, null);
			if(cursor != null){
				cursor.moveToFirst();
			}
			Kategori kategori = new Kategori(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
			return kategori;
			
		}
		
	//get single puskesmas
	Puskesmas getPuskesmas(String value){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT p._id, p.namapuskesmas, p.latitude, p.longnitude, p.alamat, p.telephone," +
				" ik.poli_umum, ik.poli_KIA, ik.poli_TB, ik.rawat_inap," +
				" ik.PONED, ik.UGD, ik.persalinan, ik.pojok_gizi, ik.konsultasi_kesehatan, ik.laboratorium,ik.poli_THT FROM " +TABLE_PUSKESMAS +" p INNER JOIN "+ INFO_KATEGORY +" ik ON p._id = ik.id_puskesmas" + " WHERE p.namapuskesmas='"+ value +"';";
		Cursor cursor = db.rawQuery(query, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		Puskesmas puskesmas = new Puskesmas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)
				,cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13)
				,cursor.getString(14),cursor.getString(15),cursor.getString(16));
		return puskesmas;
		
	}
	

	//get_search_puskesmas
	Puskesmas searchPuskesmas(String search){
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM " + TABLE_PUSKESMAS + " WHERE namapuskesmas LIKE '%"+ search +"%' LIMIT 1;";
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()){
			Puskesmas puskesmas = new Puskesmas(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
			return puskesmas;
		}
		else{
			Puskesmas puskesmas = new Puskesmas(0, null, null, null, null,null);
			return puskesmas;
		}
	}
	
}
