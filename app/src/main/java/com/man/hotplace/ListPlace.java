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
import com.man.hotplace.Fragments.ListPlaceFragment;
import com.man.hotplace.Model.Place;

import java.util.List;

public class ListPlace extends AppCompatActivity {

    private Button btrefresh, btnBack;
    ListPlaceFragment listPlaceFragment = new ListPlaceFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);

        btrefresh = (Button) findViewById(R.id.btrefresh);
        btnBack = (Button) findViewById(R.id.btnBack);
        RunFragment(listPlaceFragment);


//        setAdapter();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ListPlace.this,MapsActivity.class);
//                startActivity(intent);
//                finish();
            }

        });

        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunFragment(listPlaceFragment);
            }
        });

    }

//    private void setAdapter() {
//        if (customAdapter == null) {
//            customAdapter = new CustomAdapter(this, R.layout.item_place, listPlace);
//            listView.setAdapter(customAdapter);
//        } else {
//            customAdapter.notifyDataSetChanged();
//            listView.setSelection(customAdapter.getCount() - 1);
//        }
//    }
    public void RunFragment(Fragment fragment) {

        FragmentManager fmgr = getSupportFragmentManager();

        FragmentTransaction ft = fmgr.beginTransaction();

        ft.replace(R.id.container_body, fragment);

        ft.commit();



    }
}


