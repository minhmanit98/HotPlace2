package com.man.hotplace.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.man.hotplace.Adapter.CustomAdapter;
import com.man.hotplace.AddPlaceActivity;
import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Model.Place;
import com.man.hotplace.R;

import java.util.List;

public class ListPlaceFragment extends Fragment {
    public static ListView listView;
    public static List<Place> listPlace;
    public static Data_Place data_place;
    public static CustomAdapter customAdapter;
    private View mRootView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_listplace, container, false);

        listView = (ListView) mRootView.findViewById(R.id.lv_place);
        data_place = new Data_Place(getContext());
        listPlace = data_place.getPlace();

        setAdapter();
        return mRootView;

    }

    private void setAdapter() {
        if (customAdapter==null){
            customAdapter = new CustomAdapter(getContext(),R.layout.item_place,listPlace);
            listView.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();
            listView.setSelection(customAdapter.getCount()-1);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // cập nhật lại danh sách sau khi lưu
        boolean added = AddPlaceActivity.getAdded();
        if (added) { // nếu vừa mới thêm vào thì cập nhật lại danh sách
            listPlace.clear();
            listPlace.addAll(data_place.getPlace());
            setAdapter();
        }
//        Toast.makeText(getContext(),"start SECOND",Toast.LENGTH_SHORT).show();
    }


}
