package com.man.hotplace;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String TAG = "MapActivity";

    private Button btnAddplace;
    private Button btnListPlace;
    private LatLng latLng;

//    private GoogleApiClient mGoogleApiClient;
    private int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnAddplace = (Button) findViewById(R.id.btnAddplace);
        btnListPlace = (Button) findViewById(R.id.btnListPlace);


//        mGoogleApiClient = new GoogleApiClient
//                .Builder(this)
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(this, this)
//                .build();

        btnAddplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MapsActivity.this, AddPlaceActivity.class);
//                Criteria criteria = new Criteria();
//                LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                String bestProvider = String.valueOf(lm.getBestProvider(criteria, true)).toString();
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//
//
//
//                //You can still do this if you like, you might get lucky:
//                Location location = lm.getLastKnownLocation(bestProvider);
//                double latitude = 0;
//                double longitude = 0;
//                if (location != null) {
//                    Log.e("TAG", "GPS is on");
//                    longitude = location.getLongitude();
//                    latitude = location.getLatitude();
//                    intent.putExtra("longitude", String.valueOf(longitude));
//                    intent.putExtra("latitude", String.valueOf(latitude));
//
//                    Toast.makeText(getApplicationContext(), String.valueOf(latitude) + String.valueOf(longitude), Toast.LENGTH_SHORT).show();
//                    startActivity(intent);
//                }
//                else{
//                    //This is what you need:
//                    lm.requestLocationUpdates(bestProvider, 5000, 0, (LocationListener) MapsActivity.this);
//
//
//                }

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(MapsActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }



        });

        btnListPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, ListPlace.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    protected void onStop() {
//        mGoogleApiClient.disconnect();
//        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
//        super.onStop();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stBuilder = new StringBuilder();
                String id = String.format("%s", place.getId());
                String placename = String.format("%s", place.getName());
                String mota = "day la mo ta";
                String phonenumber = String.format("%s",place.getPhoneNumber());
                String longitude = String.valueOf(place.getLatLng().longitude);
                String latitude = String.valueOf(place.getLatLng().latitude);
//                stBuilder.append("Id: ");
//                stBuilder.append(id);
//                stBuilder.append("\n");
//                stBuilder.append("Name: ");
//                stBuilder.append(placename);
//                stBuilder.append("\n");
//                stBuilder.append("Latitude: ");
//                stBuilder.append(latitude);
//                stBuilder.append("\n");
//                stBuilder.append("Logitude: ");
//                stBuilder.append(longitude);
//                stBuilder.append("\n");
//                stBuilder.append("Phone number: ");
//                stBuilder.append(phonenumber);
//                Toast.makeText(this, stBuilder.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MapsActivity.this, InputInformation_Activity.class);
                // lấy giá trị vĩ độ và kinh độ truyền đi
                intent.putExtra("id_key", id);
                intent.putExtra("placename_key",placename);
                intent.putExtra("mota_key",mota);
                intent.putExtra("phonenumber_key",phonenumber);
                intent.putExtra("longitude_key",longitude);
                intent.putExtra("latitude_key",latitude);
                startActivity(intent);
            }
        }
    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Toast.makeText(getApplicationContext(), connectionResult.getErrorMessage() + "", Toast.LENGTH_SHORT).show();
//    }
}