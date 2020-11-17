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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Help extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng UNSWHealthCentre = new LatLng(-33.917055, 151.231717);
        mMap.addMarker(new MarkerOptions().position(UNSWHealthCentre).title("UNSW Health Centre"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(UNSWHealthCentre));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }
}