package com.man.hotplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
    private Spinner spnAtt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);

        btrefresh = (Button) findViewById(R.id.btrefresh);
        btnBack = (Button) findViewById(R.id.btnBack);
        listView = (ListView) findViewById(R.id.lv_place);
        spnAtt = (Spinner) findViewById(R.id.spn_attribute);
        data_place = new Data_Place(this);
        listPlace = data_place.getPlace(spnAtt.getSelectedItem().toString());
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

        spnAtt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(getApplicationContext(),"Da chon",Toast.LENGTH_SHORT).show();

                listPlace.clear();
                listPlace.addAll(data_place.getPlace(spnAtt.getSelectedItem().toString()));
                setAdapter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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


