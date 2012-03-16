package id.sabril.testingPA;

import java.util.ArrayList;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class UserOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

	public UserOverlay(Drawable defaultMarker){
		super(boundCenterBottom(defaultMarker));
	}
	
	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}
	
	public void removeItem(int i){
        mOverlays.remove(i);
        populate();
    }
	
	@Override
	protected OverlayItem createItem(int i){
		return mOverlays.get(i);
	}
	
	@Override
	public int size(){
		return mOverlays.size();
	}
}
