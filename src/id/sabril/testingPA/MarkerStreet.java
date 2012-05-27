package id.sabril.testingPA;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class MarkerStreet {
	public MapView mapView;
	public String lokasiPuskesmas;
	public String lokasiUser;
	public String awal; 
	public String akhir;
	
	public MarkerStreet(String gp1,String gp2,MapView mapView) {
		this.mapView = mapView;
		this.lokasiUser = gp1;
		this.lokasiPuskesmas = gp2;
	}
	
	public void directionLocation(){
    	this.awal = lokasiUser;
    	this.akhir = lokasiPuskesmas;
    	String pairs[] = getDirectionData(awal, akhir);
        String[] lngLat = pairs[0].split(",");

        // STARTING POINT
        GeoPoint startGP = new GeoPoint(
                (int) (Double.parseDouble(lngLat[1]) * 1E6), (int) (Double
                        .parseDouble(lngLat[0]) * 1E6));

        mapView.getOverlays().add(new DirectionPathOverlay(startGP, startGP));

        // NAVIGATE THE PATH
        GeoPoint gp1;
        GeoPoint gp2 = startGP;

        for (int i = 1; i < pairs.length; i++) {
            lngLat = pairs[i].split(",");
            gp1 = gp2;
            // watch out! For GeoPoint, first:latitude, second:longitude
            gp2 = new GeoPoint((int) (Double.parseDouble(lngLat[1]) * 1E6),
                    (int) (Double.parseDouble(lngLat[0]) * 1E6));
            mapView.getOverlays().add(new DirectionPathOverlay(gp1, gp2));
            //Log.d("xxx", "pair:" + pairs[i]);
        }

        // END POINT
        mapView.getOverlays().add(new DirectionPathOverlay(gp2, gp2));	
    }
    
    private String[] getDirectionData(String srcPlace, String destPlace) {
        String urlString = "http://maps.google.com/maps?f=d&hl=en&saddr="
                + srcPlace + "&daddr=" + destPlace
                + "&ie=UTF8&0&om=0&output=kml";
        //Log.d("URL", urlString);
        Document doc = null;
        HttpURLConnection urlConnection = null;
        URL url = null;
        String pathConent = "";
        try {

            url = new URL(urlString.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.connect();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(urlConnection.getInputStream());

        } catch (Exception e) {
        }

        NodeList nl = doc.getElementsByTagName("LineString");
        for (int s = 0; s < nl.getLength(); s++) {
            Node rootNode = nl.item(s);
            NodeList configItems = rootNode.getChildNodes();
            for (int x = 0; x < configItems.getLength(); x++) {
                Node lineStringNode = configItems.item(x);
                NodeList path = lineStringNode.getChildNodes();
                pathConent = path.item(0).getNodeValue();
            }
        }
        String[] tempContent = pathConent.split(" ");
        return tempContent;
    }
    
}
