package id.sabril.testingPA;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class InfoCatalog extends Activity{
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
	}
}
