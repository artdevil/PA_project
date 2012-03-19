package id.sabril.testingPA;


import java.io.IOException;
import java.io.InputStream;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class puskesmasInformation extends TestingPAActivity {
	private String dataResult;
	public Drawable d;
	private InputStream ims;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.puskesmasinformation);  
        Puskesmas puskesmas = db.getPuskesmas(this.getIntent().getExtras().getInt("ID"));
        TextView nama_puskesmas = (TextView) findViewById(R.id.puskesmas);
        getViewPuskesmas(puskesmas.getNamaPuskesmas());
        nama_puskesmas.setText(puskesmas.getNamaPuskesmas());
        TextView alamat = (TextView) findViewById(R.id.alamat);
        alamat.setText(puskesmas.getAlamat());
        TextView latitude = (TextView) findViewById(R.id.latitude);
        latitude.setText(puskesmas.getLatitude());
        TextView longnitude = (TextView) findViewById(R.id.longnitude);
        longnitude.setText(puskesmas.getLongnitude());
        dataResult = puskesmas.getLatitude() + "," + puskesmas.getLongnitude();
        Button btn = (Button) findViewById(R.id.button_result);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view){
				Intent dataPus = new Intent();
				dataPus.putExtra("lokasi",dataResult);
				setResult(RESULT_OK,dataPus);
				finish(); 
			}
		});
    }
	
	private void getViewPuskesmas(String namaPuskesmas){
		ImageView image= (ImageView) findViewById(R.id.imageView1);
        try {
			ims = getAssets().open("image/"+namaPuskesmas+".jpg");
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
