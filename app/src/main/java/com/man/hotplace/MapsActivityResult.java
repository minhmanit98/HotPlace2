package com.man.hotplace;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivityResult extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String TAG = "MapActivityResult";
    double lo=0;
    double la=0;

    private LatLng latLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_result);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = this.getIntent();
        String loo= intent.getStringExtra("longitude");

        String laa= intent.getStringExtra("latitude");
        lo=Double.valueOf(loo);
        la=Double.valueOf(laa);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng vt = new LatLng(la, lo);
        mMap.addMarker(new MarkerOptions().position(vt).title("Vị trí"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vt));
        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
    }


}