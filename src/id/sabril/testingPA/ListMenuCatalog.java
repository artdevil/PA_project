package id.sabril.testingPA;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ListMenuCatalog extends Activity{
	public ListMenuPuskesmasAdapter adapter;
	@Override
	public void onCreate(Bundle instanceBundle) {
		super.onCreate(instanceBundle);
		setContentView(R.layout.listmenucatalog);
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/comicbd.ttf");
		TextView header = (TextView) findViewById(R.id.header);
		header.setTypeface(tf);
		DatabaseHandler db = new DatabaseHandler(this);
		List<Kategori> kategori = db.getAllKategori();
		ArrayList strings = new ArrayList();
		for (Kategori cn : kategori){
			strings.add(cn.getNamaKategori());
		};
		String[] va = (String[]) strings.toArray(new String[strings.size()]);
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setTextFilterEnabled(true);
		adapter = new ListMenuPuskesmasAdapter(this, va);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				String selectedValue = (String) adapter.getItem(position);
				Intent intent = new Intent(getBaseContext(), InfoCatalog.class);
				intent.putExtra("catalogName", selectedValue);
				startActivity(intent);
			}
		});
	}
}
