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
import com.man.hotplace.MapsActivityResult;
import com.man.hotplace.Model.Place;
import com.man.hotplace.Model.PlaceInfo;
import com.man.hotplace.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<PlaceInfo> {

    private Context context;
    private int resource;
    private List<PlaceInfo> listPlace;

    private Data_Place dbPlace;
    private String getName;
    private int pos;



    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<PlaceInfo> objects) {


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
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.tvPhoneNumber = (TextView) convertView.findViewById(R.id.tv_phone);
            viewHolder.tvWebsiteUri = (TextView) convertView.findViewById(R.id.tv_web);
            viewHolder.tvRating = (TextView) convertView.findViewById(R.id.tv_rating);
            viewHolder.tvAttributions = (TextView) convertView.findViewById(R.id.tv_att);
            viewHolder.btgoi = (FloatingActionButton) convertView.findViewById(R.id.fb_call);
            viewHolder.btremove = (FloatingActionButton) convertView.findViewById(R.id.fb_remove);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        PlaceInfo place = listPlace.get(position);
        viewHolder.tvId.setText(String.valueOf(place.getId()));
        viewHolder.tvName.setText(place.getName());
        viewHolder.tvAddress.setText(place.getAddress());
        viewHolder.tvPhoneNumber.setText(place.getPhoneNumber().toString());
        viewHolder.tvWebsiteUri.setText(place.getName().toString());
        viewHolder.tvRating.setText(String.valueOf(place.getRating()));
        viewHolder.tvAttributions.setText(place.getAttributions());

        viewHolder.btgoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceInfo place = listPlace.get(position);

                String ssdt= place.getPhoneNumber();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ssdt));
                context.startActivity(callIntent);
            }
        });

        viewHolder.tvId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceInfo place = listPlace.get(position);


                Intent intent=new Intent(getContext(), MapsActivityResult.class);
                intent.putExtra("longitude",place.getLatlng().longitude);
                intent.putExtra("latitude", place.getLatlng().latitude);
//                intent.putExtra("latlng", place.getLatlng());
                Toast.makeText(getContext(), "Clicked" ,Toast.LENGTH_SHORT).show();

                getContext().startActivity(intent);
            }
        });

        viewHolder.btremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaceInfo place = listPlace.get(position);
                pos=position;
                getName = place.getName();

                try{
                    dbPlace.removeNAME(getName);
                    listPlace.remove(pos);
                    notifyDataSetChanged();
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

    public class ViewHolder{
        private TextView tvId;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPhoneNumber;
        private TextView tvWebsiteUri;
        private TextView tvRating;
        private TextView tvAttributions;

        private FloatingActionButton btgoi;
        private FloatingActionButton btremove;


    }




}



