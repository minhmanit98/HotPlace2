package com.man.hotplace;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.man.hotplace.directionhelpers.FetchURL;
import com.man.hotplace.directionhelpers.TaskLoadedCallback;


public class MapsActivityResult extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Polyline currentPolyline;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String TAG = "MapActivityResult";
    double lo, la;
    String name, add;
    private Marker marker;

    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_result);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = this.getIntent();
        lo = intent.getDoubleExtra("longitude", 0);
        la = intent.getDoubleExtra("latitude", 0);
        name = intent.getStringExtra("name");
        add = intent.getStringExtra("address");
//        latLng = intent.getParcelableExtra("latlng");
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(bestProvider);
        double latitude = 0;
        double longitude = 0;
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Toast.makeText(getApplicationContext(), "Vĩ độ: " + latitude + " Kinh độ: " + longitude, Toast.LENGTH_SHORT).show();
        } else {
            //điều cần làm nếu location null
            locationManager.requestLocationUpdates(bestProvider, 5000, 0, (LocationListener) MapsActivityResult.this);
        }
        MarkerOptions place1 = new MarkerOptions().position(new LatLng(latitude, longitude));

        try {
            new FetchURL(MapsActivityResult.this).execute(getUrl(place1.getPosition(), new LatLng(la, lo), "driving"), "driving");

        } catch (NullPointerException e) {
            Log.e(TAG, "onClick: NullPointerException: " + e.getMessage());
        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        latLng = new LatLng(la, lo);
        marker = mMap.addMarker(new MarkerOptions().position(latLng).title(name + " : " + add));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=AIzaSyCuZCfoPPUV1upJT10kJbCbV71LUqwhFCM";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


}