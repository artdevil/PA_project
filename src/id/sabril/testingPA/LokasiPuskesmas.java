package id.sabril.testingPA;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class LokasiPuskesmas {
	public Context mContext;
	public GeoPoint point;
	public OverlayItem overlayItem;
	Context myContext;
	public LokasiPuskesmas(Context context){
		this.mContext = context;
	}
	
	public void setPosisiPuskesmas(MapView mapView){
		DatabaseHandler db = new DatabaseHandler(mContext);
		List<Puskesmas> puskesmas = db.getAllPuskesmas();
		Drawable marker = mContext.getResources().getDrawable(R.drawable.marker_puskesmas);
		int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);
		mapPuskesmasOverlay mapPuskesmas = new mapPuskesmasOverlay(marker, mapView);
		mapView.getOverlays().add(mapPuskesmas);
		for (Puskesmas cn : puskesmas){
			String coordinates[] = {cn.getLatitude(),cn.getLongnitude()};
        	double latitude = Double.parseDouble(coordinates[0]);
        	double longnitude = Double.parseDouble(coordinates[1]);
        	point = new GeoPoint(
        			(int) (latitude * 1E6),
        			(int) (longnitude * 1E6)
        			);
        	overlayItem = new OverlayItem(point, cn.getNamaPuskesmas(), cn.getAlamat());
        	mapPuskesmas.addItem(overlayItem);
		}
	}
	
}
