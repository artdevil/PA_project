package id.sabril.testingPA;



import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListMenuCatalog extends Activity{
	public ListMenuPuskesmasAdapter adapter;
	@Override
	public void onCreate(Bundle instanceBundle) {
		super.onCreate(instanceBundle);
		setContentView(R.layout.listmenucatalog);
		Typeface tf = Typeface.createFromAsset(getAssets(),"font/comicbd.ttf");
		TextView header = (TextView) findViewById(R.id.header);
		header.setTypeface(tf);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" }; 
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setTextFilterEnabled(true);
		adapter = new ListMenuPuskesmasAdapter(this, values);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				String selectedValue = (String) adapter.getItem(position);
				Toast.makeText(getBaseContext(), selectedValue, Toast.LENGTH_SHORT).show();
				
			}
		});
		//setContentView(R.layout.listmenucatalog);

        

		//String[] items = {"red", "blue","green"};



		//ListView listView = (ListView) findViewById(R.id.listView);

		//listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
		

	}
}
