package com.man.hotplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.man.hotplace.Adapter.CustomAdapter;
import com.man.hotplace.Data.Data_Place;

import com.man.hotplace.Model.Place;
import com.man.hotplace.Model.PlaceInfo;

import java.util.List;

public class ListPlace extends AppCompatActivity {

    private ListView listView;
    private List<PlaceInfo> listPlace;
    private Data_Place data_place;
    private CustomAdapter customAdapter;
    private Button btrefresh, btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);

        btrefresh = (Button) findViewById(R.id.btrefresh);
        btnBack = (Button) findViewById(R.id.btnBack);
        listView = (ListView) findViewById(R.id.lv_place);
        data_place = new Data_Place(this);
        listPlace = data_place.getPlace();



        setAdapter();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ListPlace.this,MapsActivity.class);
//                startActivity(intent);
                finish();
            }

        });

        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdapter();
            }
        });

    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.item_place, listPlace);
            listView.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            listView.setSelection(customAdapter.getCount() - 1);
        }
    }

}


