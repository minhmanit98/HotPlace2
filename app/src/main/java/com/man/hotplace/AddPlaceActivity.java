package com.man.hotplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Model.Place;
import com.man.hotplace.Model.PlaceInfo;

public class AddPlaceActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtAddress;
    private Spinner spnAtt;
    private EditText edtPhoneNumber, edtWeb, edtRating;
    private Button btnBack;
    private Button btnSave;
    PlaceInfo place;
    String longitude ;
    String latitude ;
    LatLng latLng;
    private Data_Place data_place;
    private static boolean added;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplace);


        edtName = (EditText) findViewById(R.id.edit_name);
        edtAddress = (EditText) findViewById(R.id.edit_address);
        spnAtt = (Spinner) findViewById(R.id.spn_att);
        edtPhoneNumber = (EditText) findViewById(R.id.edit_phone);
        edtWeb = (EditText) findViewById(R.id.edit_web);
        edtRating = (EditText) findViewById(R.id.edit_rating);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnSave = (Button) findViewById(R.id.btn_save);


        data_place = new Data_Place(this);
        // lay du lieu duoc gui qua
        Intent intent = this.getIntent();

        added = false;
        longitude= intent.getStringExtra("long_key");

        latitude= intent.getStringExtra("lat_key");

        latLng = new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
        try{
            edtName.setText(intent.getStringExtra("placename_key"));
            edtAddress.setText(intent.getStringExtra("add_key"));
            edtPhoneNumber.setText(intent.getStringExtra("phonenumber_key"));
            edtWeb.setText(intent.getStringExtra("weburi_key"));
            edtRating.setText(intent.getStringExtra("rating_key"));
        }catch (Exception ex){
            Toast.makeText(AddPlaceActivity.this,"Lỗi: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
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
                place =new PlaceInfo(edtName.getText().toString(),edtAddress.getText().toString(),edtPhoneNumber.getText().toString(), Uri.parse(edtWeb.getText().toString()), latLng, Float.parseFloat(edtRating.getText().toString()),spnAtt.getSelectedItem().toString());
                data_place.addPlaceInfo(place);
                added = true;
                Toast.makeText(getApplicationContext(),"Thêm "+edtName.getText().toString()+" thành công",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
