package id.sabril.testingPA;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends Activity implements OnClickListener{
	public ImageView maps;
	private ImageView catalog;
	private ImageView about;
	private ImageView close;
	public void onCreate(Bundle instanceBundle) {
		super.onCreate(instanceBundle);
		setContentView(R.layout.menu);
		setupDatabase();
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/comicbd.ttf");
		TextView header = (TextView) findViewById(R.id.header);
		header.setTypeface(tf);
		maps = (ImageView) findViewById(R.id.maps);
		maps.setOnClickListener(this);
		catalog = (ImageView) findViewById(R.id.catalog);
		catalog.setOnClickListener(this);
		about = (ImageView) findViewById(R.id.about);
		about.setOnClickListener(this);
		close = (ImageView) findViewById(R.id.close);
		close.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		if(view == maps){
			Intent intent = new Intent(this,TestingPAActivity.class);
			intent.putExtra("ID", 1);
			startActivity(intent); 
		}
		
		else if (view == catalog){
			Intent intent = new Intent(this,ListMenuCatalog.class);
			startActivity(intent);
		}
		else if(view == about){
			Intent intent = new Intent(this,About.class);
			startActivity(intent);
		}
		else if(view == close){
			close();
		}
	}
	
	public void close() {
		AlertDialog alert = new AlertDialog.Builder(this).create();
		alert.setTitle("Close Application");
		alert.setMessage("apakah anda yakin ?");
		alert.setButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
				
			}
		});
		alert.setButton2("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		alert.show();
	}
	
	public void setupDatabase(){
    	DatabaseHandler myDatatabase = new DatabaseHandler(null);
    	myDatatabase = new DatabaseHandler(this);
    	try {
			myDatatabase.createDatabase();
		} catch (IOException e) {
			throw new Error("unable to create database");
		}
    	
    	try {
			myDatatabase.openDatabase();
		} catch (SQLiteException sqle) {
			throw sqle;
		}
    }
	
	
}
