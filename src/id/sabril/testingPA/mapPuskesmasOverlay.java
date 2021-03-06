package id.sabril.testingPA;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

public class mapPuskesmasOverlay extends BalloonItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
	private Context mContext;
	int request_code = 1;
	Context myContext;
	public mapPuskesmasOverlay(Drawable marker,MapView mapView) {
		super(boundCenterBottom(marker), mapView);
		populate();
		mContext = mapView.getContext();
	}
	
	public void addItem(OverlayItem newItem) {
		overlayItemList.add(newItem);
		populate();
	}
	
	@Override 
	protected OverlayItem createItem(int i){
		return overlayItemList.get(i);
	}
	
	@Override
	public int size(){
		return overlayItemList.size();
	}
	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item){
		String value = item.getTitle();
		Intent myIntent = new Intent(mContext,puskesmasInformation.class);
		myIntent.putExtra("ID", value);
		((Activity) mContext).startActivityForResult(myIntent,1);
		return true;
	}
	
	public void remove() {
		overlayItemList.removeAll(overlayItemList);
	}
}
