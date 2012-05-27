package id.sabril.testingPA;

import id.sabril.testingPA.MyLocation.LocationResult;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Maps extends MapActivity{
    public MapView mapView;
    public MapController mapController;
    public String lokasiUser;
    public String lokasiPuskesmas;
    public List<Overlay> mapOverlays;
    public Drawable drawable;
    public OverlayItem overlayItem;
	public UserOverlay userOverlay;
	public Boolean status_track;
	public Boolean status_nearbly;
	public GeoPoint pointLocation;
	public ProgressDialog loading;
	LokasiPuskesmas puskesmas;
	protected Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        setupDatabase();
        mapSetup();
        posisiPuskesmas();
        status_nearbly = new Boolean(false);
        if(this.getIntent().getExtras().getInt("ID") == 1){
        	detectionUser();
        }
        Button btn = (Button) findViewById(R.id.search_button);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText searching = (EditText) findViewById(R.id.searching);
				String search_result = searching.getText().toString() ;
				if(search_result.length() > 0){
					DatabaseHandler db = new DatabaseHandler(getBaseContext());
					Puskesmas puskesmas = db.searchPuskesmas(search_result);
					if(puskesmas.getLatitude() == null || puskesmas.getLongnitude() == null || puskesmas.getNamaPuskesmas() == null){
						Toast.makeText(getBaseContext(),"puskesmas tidak ditemukan", Toast.LENGTH_LONG).show();
					}
					else{
						GeoPoint p = new GeoPoint(
								(int) (Double.parseDouble(puskesmas.getLatitude()) * 1E6),
			        			(int) (Double.parseDouble(puskesmas.getLongnitude()) * 1E6)
			        			);
						mapController = mapView.getController();
						mapController.animateTo(p);         //set lokasi utama user
						mapController.setZoom(16);
						Toast.makeText(getBaseContext(), "puskesmas yang dipilih "+puskesmas.getNamaPuskesmas(), Toast.LENGTH_LONG).show();
					}
				}
			}
		});
    }
    
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu_down, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()) {
		case R.id.menu_search_location:
			detectionUser();
			return true;
		case R.id.menu_search_category:
			Intent intent = new Intent(this, ListCategory.class);
			startActivityForResult(intent, 2);
			return true;
		case R.id.nearbly:
			status_nearbly = new Boolean(true);
			detectionUser();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
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
    
    public void posisiPuskesmas(){
    	puskesmas = new LokasiPuskesmas(this);
    	puskesmas.setPosisiPuskesmas(mapView,"",null,null);
    }
    
    public void mapSetup() {                             //setting map
    	mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);           //mengaktifkan tombol zooming
        mapView.setTraffic(true);
        mapView.setSatellite(false);
        mapView.setStreetView(false);
        mapView.invalidate();
	}
    
    public void onActivityResult(int requestCode,int resultCode,Intent data){
    	if(requestCode == 1){
    		if(resultCode == RESULT_OK){
    			lokasiPuskesmas = data.getExtras().getString("lokasi");
    			loading = new ProgressDialog(this);
    	    	loading.setMessage("deteksi lokasi user");
    	    	loading.show();
    			MyLocation myLocation = new MyLocation();
    			status_track = new Boolean(true);
    			myLocation.getLocation(this, locationResult);
        	}
    	}
    	if(requestCode == 2){
    		if(resultCode == RESULT_OK){
    			puskesmas.removePuskesmas(mapView);
    			puskesmas.setPosisiPuskesmas(mapView, data.getExtras().getString("category"),null,null);
    			Toast.makeText(getBaseContext(), data.getExtras().getString("category"), Toast.LENGTH_SHORT).show();
    		}
    	}
    }
    
    protected void detectionUser(){
    	loading = new ProgressDialog(this);
    	loading.setMessage("deteksi lokasi user");
    	loading.show();
    	MyLocation myLocation = new MyLocation();
		status_track = new Boolean(false);
		myLocation.getLocation(this, locationResult);
    }
    
    public void markerUser(){
    	mapOverlays = mapView.getOverlays();
    	drawable = this.getResources().getDrawable(R.drawable.user);
    	userOverlay = new UserOverlay(drawable);
    	overlayItem = new OverlayItem(pointLocation, "hello", "hello");
    	userOverlay.addOverlay(overlayItem);
    	mapOverlays.add(userOverlay);
        mapController = mapView.getController();
		mapController.animateTo(pointLocation);         //set lokasi utama user
		mapController.setZoom(16);
    }
    
    public LocationResult locationResult = new LocationResult(){
        @Override
        public void gotLocation(final Location location){
        	if(pointLocation != null){
        		userOverlay.removeUser();
        	}
        	lokasiUser = location.getLatitude()+ "," +location.getLongitude();
        	pointLocation = new GeoPoint(
        			(int) (location.getLatitude() * 1E6),
        			(int) (location.getLongitude() * 1E6)
        			);
        	
        	
        	Toast.makeText(getBaseContext(), "lokasi ditemukan", Toast.LENGTH_SHORT).show();
    		if(status_track == true){
    			MarkerStreet markerStreet = new MarkerStreet(lokasiUser, lokasiPuskesmas, mapView);
                markerStreet.directionLocation();
                status_track = new Boolean(false);
    		}
    		if(status_nearbly == true){
    			mapView.getOverlays().clear();
    			puskesmas.setPosisiPuskesmas(mapView,"",location.getLatitude(),location.getLongitude());
    			MapCircleOverlay circle = new MapCircleOverlay(pointLocation, 2222);
    			mapView.getOverlays().add(circle);
    			status_nearbly = new Boolean(false);
    		}
    		markerUser();
    		loading.hide();
        }
    };
    
	@Override
	protected boolean isRouteDisplayed() { //insialisasi google maps
		return false;			
	}
	
	
}