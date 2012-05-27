package id.sabril.testingPA;


import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class puskesmasInformation extends Activity {
	private String dataResult;
	public Drawable d;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.puskesmasinformation);  
        Puskesmas puskesmas = db.getPuskesmas(this.getIntent().getExtras().getString("ID"));
        Typeface tf = Typeface.createFromAsset(getAssets(),"font/comicbd.ttf");
        TextView nama_puskesmas = (TextView) findViewById(R.id.puskesmas);
        nama_puskesmas.setTypeface(tf);
        getViewPuskesmas(puskesmas.getNamaPuskesmas());
        nama_puskesmas.setText(puskesmas.getNamaPuskesmas());
        TextView alamat = (TextView) findViewById(R.id.alamat);
        alamat.setText(puskesmas.getAlamat());
        TextView telephone = (TextView) findViewById(R.id.telephone);
        telephone.setText(puskesmas.getTelephone());
        TextView latitude = (TextView) findViewById(R.id.latitude);
        latitude.setText(puskesmas.getLatitude());
        TextView longnitude = (TextView) findViewById(R.id.longnitude);
        longnitude.setText(puskesmas.getLongnitude());
        
        /* image */
        
        ImageView poliumum = (ImageView) findViewById(R.id.poli_umum);
        if(puskesmas.getPoli_umum().equals("true")){
        	poliumum.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getPoli_umum().equals("false")){
        	poliumum.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView poliKIA = (ImageView) findViewById(R.id.poli_KIA_KB);
        if(puskesmas.getPoli_KIA_KB().equals("true")){
        	poliKIA.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getPoli_KIA_KB().equals("false")){
        	poliKIA.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView poliTBC = (ImageView) findViewById(R.id.poli_TBC);
        if(puskesmas.getPoli_TBC().equals("true")){
        	poliTBC.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getPoli_TBC().equals("false")){
        	poliTBC.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView rawatInap = (ImageView) findViewById(R.id.rawat_inap);
        if(puskesmas.getRawatInap().equals("true")){
        	rawatInap.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getRawatInap().equals("false")){
        	rawatInap.setImageResource(R.drawable.cancel_48);
        }
        
        
        ImageView poned = (ImageView) findViewById(R.id.poned);
        if(puskesmas.getPoned().equals("true")){
        	poned.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getPoned().equals("false")){
        	poned.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView UGD = (ImageView) findViewById(R.id.ugd);
        if(puskesmas.getUGD().equals("true")){
        	UGD.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getUGD().equals("false")){
        	UGD.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView persalinan = (ImageView) findViewById(R.id.persalinan);
        if(puskesmas.getPersalinan().equals("true")){
        	persalinan.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getPersalinan().equals("false")){
        	persalinan.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView konsultasiGizi = (ImageView) findViewById(R.id.konsultasi_pojok_gizi);
        if(puskesmas.getKonsultasi_pojok_gizi().equals("true")){
        	konsultasiGizi.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getKonsultasi_pojok_gizi().equals("false")){
        	konsultasiGizi.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView konsultasiKesehatan = (ImageView) findViewById(R.id.konsultasi_kesehatan);
        if(puskesmas.getKonsultasi_kesehatan().equals("true")){
        	konsultasiKesehatan.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getKonsultasi_kesehatan().equals("false")){
        	konsultasiKesehatan.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView laboratorium = (ImageView) findViewById(R.id.laboratorium);
        if(puskesmas.getLaboratorium().equals("true")){
        	laboratorium.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getLaboratorium().equals("false")){
        	laboratorium.setImageResource(R.drawable.cancel_48);
        }
        
        ImageView poli_tht = (ImageView) findViewById(R.id.poli_tht);
        if(puskesmas.getPoli_THT().equals("true")){
        	poli_tht.setImageResource(R.drawable.accepted_48);
        }
        else if(puskesmas.getPoli_THT().equals("false")){
        	poli_tht.setImageResource(R.drawable.cancel_48);
        }
        
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
		ImageView image= (ImageView) findViewById(R.id.imagePuskesmas);
        try {
			InputStream ims = getAssets().open("image/"+namaPuskesmas+".jpg");
			
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
