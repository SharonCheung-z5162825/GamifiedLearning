package au.edu.unsw.infs3634.gamifiedlearning.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import au.edu.unsw.infs3634.gamifiedlearning.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Help extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //map view - find the relevant id in the xml file
        mMapView = findViewById(R.id.mapView);
        //set an instance of the callback on the MapView object
        mMapView.onCreate(savedInstanceState);
        //register the callback on the mapview for the object to be triggered when the mapview is ready to use
        mMapView.getMapAsync(this);

        //Initialise and assign bottom navigation bar
        BottomNavigationView bottomNaviView = findViewById(R.id.bottom_navibar);
        //set Help selected button
        bottomNaviView.setSelectedItemId(R.id.menuHelp);
        //set action on the home button
        bottomNaviView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch  (item.getItemId()){
                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuProgress:
                        startActivity(new Intent(getApplicationContext(),ProgressBar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menuHelp:
                        return true;
                }
                return false;
            }
        });
    }
    //In order to apply Mapview, the below activity lifecycle methods to the corresponding methods
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng UNSWHealthCentre = new LatLng(-33.917055, 151.231717);
        mMap.addMarker(new MarkerOptions().position(UNSWHealthCentre).title("UNSW Health Centre"));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UNSWHealthCentre,20));
    }
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}