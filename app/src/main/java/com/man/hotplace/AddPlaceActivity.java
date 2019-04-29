package com.man.hotplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Model.Place;

public class AddPlaceActivity extends AppCompatActivity {
    private Button btnShow;
    private Button btnSave;
    EditText edtId,edtTenDiaDiem,edtMota,edtSdt,edtLongitude,edtLatitude;
    Place place;
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
                 place =new Place(edtId.getText().toString(),edtTenDiaDiem.getText().toString(),edtMota.getText().toString(),edtSdt.getText().toString(),longitude,latitude);
                data_place.addPlace(place);
                added = true;
                Toast.makeText(getApplicationContext(),"Thêm "+edtTenDiaDiem.getText().toString()+" thành công",Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void setControls(){
        btnShow = (Button) findViewById(R.id.btn_show);
        btnSave = (Button) findViewById(R.id.btn_save);
        edtId =(EditText) findViewById(R.id.edtId);
        edtTenDiaDiem =(EditText) findViewById(R.id.edit_name);
        edtMota =(EditText) findViewById(R.id.edit_note);
        edtSdt=(EditText) findViewById(R.id.edit_phone);

    }

    public static boolean getAdded(){
        return added;
    }
}
