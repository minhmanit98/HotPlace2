package com.man.hotplace.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Fragments.ListPlaceFragment;
import com.man.hotplace.MapsActivityResult;
import com.man.hotplace.Model.Place;
import com.man.hotplace.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Place> {

    private Context context;
    private int resource;
    private List<Place> listPlace;

    private Data_Place dbPlace;
    private String getId;
    private int pos;



    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Place> objects) {


        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listPlace = objects;
        dbPlace = new Data_Place(context);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_place,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tvTenDiaDiem = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvSdt = (TextView) convertView.findViewById(R.id.tv_phone);
            viewHolder.tvMoTa = (TextView) convertView.findViewById(R.id.tv_noted);
            viewHolder.btgoi = (FloatingActionButton) convertView.findViewById(R.id.fb_call);
            viewHolder.btremove = (FloatingActionButton) convertView.findViewById(R.id.fb_remove);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        Place place = listPlace.get(position);
        viewHolder.tvId.setText(String.valueOf(place.getId()));
        viewHolder.tvMoTa.setText(place.getMoTa());
        viewHolder.tvSdt.setText(place.getSdt());
        viewHolder.tvTenDiaDiem.setText(place.getTenDiaDiem());

        viewHolder.btgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = listPlace.get(position);

                String ssdt= place.getSdt();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ssdt));
                context.startActivity(callIntent);
            }
        });

        viewHolder.tvId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = listPlace.get(position);

                Intent intent=new Intent(getContext(), MapsActivityResult.class);
                intent.putExtra("longitude",place.getLongitude());
                intent.putExtra("latitude", place.getLatitude());
                Toast.makeText(getContext(), "Clicked" ,Toast.LENGTH_SHORT).show();

                getContext().startActivity(intent);
            }
        });

        viewHolder.btremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Place place = listPlace.get(position);
                getId = (place.getId());

                try{
                    ListPlaceFragment.data_place.removeId(getId);
                    ListPlaceFragment.listPlace.clear(); // du lieu la 0 khong phai null
                    ListPlaceFragment.listPlace.addAll(ListPlaceFragment.data_place.getPlace());
                    setAdapter();
                }catch(Exception e1){
                    Toast.makeText(context, "lỗi = " + e1, Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getContext(),
                        "Đã xóa " + place.getId(), Toast.LENGTH_SHORT)
                        .show();
            }
        });


        return convertView;
    }

    // Tranh truong hop item list view qua nhieu se bi lag nen dung cai duoi
    // khai bao nhung view se su dung o item nay

    public void setAdapter(){
//        if (customAdapter==null){
//            customAdapter = new CustomAdapter(getContext(),R.layout.item_list,listPlace);
//        }
//        lvPlace.setAdapter(customAdapter);
//        lvPlace.setSelection(customAdapter.getCount()-1);
        if (ListPlaceFragment.customAdapter==null){
            ListPlaceFragment.customAdapter = new CustomAdapter(getContext(),R.layout.item_place,listPlace);
            ListPlaceFragment.listView.setAdapter(ListPlaceFragment.customAdapter);
        }else {
            ListPlaceFragment.customAdapter.notifyDataSetChanged();
            ListPlaceFragment.listView.setSelection(ListPlaceFragment.customAdapter.getCount()-1);
        }
    }
    public class ViewHolder{
        private TextView tvId;
        private TextView tvTenDiaDiem;
        private TextView tvSdt;
        private TextView tvMoTa;

        private FloatingActionButton btgoi;
        private FloatingActionButton btremove;


    }




}



