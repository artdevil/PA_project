package id.sabril.testingPA;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class puskesmasInformation extends TestingPAActivity {
	private String dataResult;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.puskesmasinformation);  
        Puskesmas puskesmas = db.getPuskesmas(this.getIntent().getExtras().getInt("ID"));
        TextView nama_puskesmas = (TextView) findViewById(R.id.puskesmas);
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
}
