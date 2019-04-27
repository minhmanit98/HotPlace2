package com.man.hotplace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.man.hotplace.Adapter.CustomAdapter;
import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Model.Place;

import java.util.List;

public class ListPlace extends AppCompatActivity {
    private ListView listView;
    List<Place> listPlace;
    Data_Place data_place;
    private CustomAdapter customAdapter;
    private Button btrefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_place);
        listView = (ListView) findViewById(R.id.lv_place);
        btrefresh = (Button) findViewById(R.id.btrefresh);

        data_place = new Data_Place(this);
        listPlace = data_place.getPlace();
        setAdapter();

        btrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPlace.clear();
                listPlace.addAll(data_place.getPlace());
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

//    public static void createTable() {
//        customAdapter.notifyDataSetChanged();
//    }

}


