package id.sabril.testingPA;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListCategory extends Activity{
	public ListCategoryAdapter adapter;
	@Override
	public void onCreate(Bundle instanceBundle) {
		super.onCreate(instanceBundle);
		setContentView(R.layout.listcategory);
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/comicbd.ttf");
		TextView header = (TextView) findViewById(R.id.header);
		header.setTypeface(tf);
		
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setTextFilterEnabled(true);
		String[] va = new String[]{ "poli_umum","poli_KIA","poli_TB","rawat_inap","PONED","UGD","persalinan","laboratorium","konsultasi_kesehatan","pojok_gizi","poli_THT" };
		adapter = new ListCategoryAdapter(this, va);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				String selectedValue = (String) adapter.getItem(position);
				Intent dataPus = new Intent();
				dataPus.putExtra("category",selectedValue);
				setResult(RESULT_OK,dataPus);
				finish();
			}
		});
	}
}
