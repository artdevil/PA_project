package id.sabril.testingPA;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoCatalog extends Activity{
	private Drawable d;

	@Override
	public void onCreate(Bundle instanceBundle) {
		super.onCreate(instanceBundle);
		setContentView(R.layout.infocatalog);
		DatabaseHandler db = new DatabaseHandler(this);
		Kategori kategori = db.getKategori(this.getIntent().getExtras().getString("catalogName"));
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/comicbd.ttf");
		TextView header= (TextView) findViewById(R.id.header);
		header.setTypeface(tf);
		header.setText(kategori._namakategori);
		TextView content = (TextView) findViewById(R.id.textinfo);
		content.setText(kategori._keterangan);
		getViewPuskesmas(kategori._namakategori);
	}
	
	private void getViewPuskesmas(String namaKategori){
		ImageView image= (ImageView) findViewById(R.id.imagePuskesmas);
        try {
			InputStream ims = getAssets().open("kategori/"+namaKategori+".jpg");
			
			byte[] buffer = new byte[1024];
			  if(ims.read(buffer) > 0){
				d = Drawable.createFromStream(ims, null);
				image.setImageDrawable(d);
			} 
		} catch (IOException e) {
			return;
		}
	}
}
