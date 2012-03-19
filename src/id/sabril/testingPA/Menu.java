package id.sabril.testingPA;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Menu extends Activity implements OnClickListener{
	public ImageView maps;
	private ImageView catalog;
	private ImageView about;
	private ImageView close;
	public void onCreate(Bundle instanceBundle) {
		super.onCreate(instanceBundle);
		setContentView(R.layout.menu);
		maps = (ImageView) findViewById(R.id.maps);
		maps.setOnClickListener(this);
		catalog = (ImageView) findViewById(R.id.catalog);
		about = (ImageView) findViewById(R.id.about);
		close = (ImageView) findViewById(R.id.close);
		close.setOnClickListener(this);
	}
	
	public void onClick(View view) {
		if(view == maps){
			Intent intent = new Intent(this,TestingPAActivity.class);
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
}
