package com.man.hotplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Model.Place;
import com.man.hotplace.Model.PlaceInfo;

public class AddPlaceActivity extends AppCompatActivity {
    private Button btnShow;
    private Button btnSave;
    EditText edtName,edtAddress,edtPhone,edtWeb,edtRating,edtAtt;
    PlaceInfo place;
    Data_Place data_place;
    String longitude ;
    String latitude ;
    private static boolean added;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplace);
        setControls();
        data_place = new Data_Place(this);
        Intent intent = this.getIntent();
        added = false;


        longitude= intent.getStringExtra("longitude");

        latitude= intent.getStringExtra("latitude");
        final LatLng latLng = new LatLng(Double.valueOf(longitude),Double.valueOf(latitude));


        Toast.makeText(getApplicationContext(), String.valueOf(latitude ) +String.valueOf(longitude ) ,Toast.LENGTH_SHORT).show();
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AddPlaceActivity.this,MapsActivity.class);
//                startActivity(intent);
                finish();
            }

        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place =new PlaceInfo(edtName.getText().toString(),edtAddress.getText().toString(),edtPhone.getText().toString(), Uri.parse(edtWeb.getText().toString()), latLng, Float.parseFloat(edtRating.getText().toString()),edtAtt.getText().toString());
                data_place.addPlaceInfo(place);
                added = true;
                Toast.makeText(getApplicationContext(),"Thêm "+edtName.getText().toString()+" thành công",Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void setControls(){
        btnShow = (Button) findViewById(R.id.btn_show);
        btnSave = (Button) findViewById(R.id.btn_save);
        edtName =(EditText) findViewById(R.id.edit_name);
        edtAddress =(EditText) findViewById(R.id.edit_address);
        edtPhone=(EditText) findViewById(R.id.edit_phone);
        edtWeb=(EditText) findViewById(R.id.edit_web);
        edtRating=(EditText) findViewById(R.id.edit_rating);
        edtAtt=(EditText) findViewById(R.id.edit_att);

    }

    public static boolean getAdded(){
        return added;
    }
}
