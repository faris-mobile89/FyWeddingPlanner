package app.com.fyweddingplanner;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;


public class MapActivity extends FragmentActivity {

	GoogleMap map;
	ArrayList<LatLng> markerPoints;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		// Initializing 
		markerPoints = new ArrayList<>();
		// Getting reference to SupportMapFragment of the activity_main
		SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
		// Getting Map for the SupportMapFragment
		map = fm.getMap();
        getTrackingData();
   }

    private void getTrackingData(){

        ArrayList<LatLng> points = new ArrayList<>();

        // Fetching all the points in i-th route
        /*for (int i = 0; i< locations.size();i++){
            double lat = locations.get(i).getNumber("Latitude").doubleValue();
            double lng = locations.get(i).getNumber("Longitude").doubleValue();
            LatLng position = new LatLng(lat, lng);
            points.add(position);
        }*/
        addLines(points);
    }

    private void addLines(ArrayList<LatLng> points) {

     try {
         map.addMarker(new MarkerOptions()
                         .position(points.get(0))
                         .title("T").
                                 snippet("Location:" + points.get(0))
         );

         map.moveCamera(CameraUpdateFactory.newLatLngZoom(points.get(0),17));
     }catch (Exception e){
         e.printStackTrace();
     }
    }

}