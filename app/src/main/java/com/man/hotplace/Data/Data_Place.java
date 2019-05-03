package com.man.hotplace.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.man.hotplace.Model.Place;
import com.man.hotplace.Model.PlaceInfo;

import java.util.ArrayList;
import java.util.List;


public class Data_Place extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="database_place";
    private static final String TABLE_NAME="table_place";
//    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONENUMBER = "phoneNumber";
    private static final String WEBSITEURI = "websiteUri";
    private static final String LATLNG = "latlng";
    private static final String RATING = "rating";
    private static final String ATTRIBUTIONS = "attributions";
    private final String TAG = "DB";

    private static final int VERSION=1;
    String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
//            ID + " TEXT PRIMARY KEY, " +
            NAME + " TEXT PRIMARY KEY, " +
            ADDRESS + " TEXT, " +
            PHONENUMBER + " TEXT, " +
            WEBSITEURI + " TEXT, " +
            LATLNG + " TEXT, " +
            RATING + " TEXT, " +
            ATTRIBUTIONS + " TEXT)";




    public Data_Place(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addPlaceInfo(PlaceInfo place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(ID, place.getId());
        values.put(NAME, place.getName());
        values.put(ADDRESS, place.getAddress());
        values.put(PHONENUMBER, place.getPhoneNumber());
        values.put(WEBSITEURI, place.getWebsiteUri().toString());
        values.put(LATLNG, place.getLatlng().toString());
//        Log.d(TAG,"Toa dodd: " + place.getLatlng().toString());
        values.put(RATING, String.valueOf(place.getRating()));
        values.put(ATTRIBUTIONS, place.getAttributions());
        db.insert(TABLE_NAME,null,values);
        Log.d("TT","Them thanh cong");
        db.close();
    }
    public List<PlaceInfo> getPlace(String attribution){
        String getPlace = "";
        if (attribution.equals("All")){
            getPlace = "SELECT * FROM " + TABLE_NAME ;
        }else {
            getPlace = "SELECT * FROM " + TABLE_NAME + " WHERE ATTRIBUTIONS='" + attribution + "'";
        }
        List<PlaceInfo> listPlace =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getPlace, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            String temp = cursor.getString(4).replace("lat/lng: (","");
            temp = temp.replace(")","");
            String[] latlong =  temp.split(",");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);
            LatLng latlng = new LatLng(latitude,longitude);
            PlaceInfo sv = new PlaceInfo(cursor.getString(0), cursor.getString(1), cursor.getString(2), Uri.parse(cursor.getString(3)),latlng,Float.parseFloat(cursor.getString(5)),cursor.getString(6));
            sv.setId(String.valueOf(listPlace.size()+1));
            listPlace.add(sv);
            cursor.moveToNext();
        }
        db.close();
        return listPlace;
    }
    public void removeLatlng(String LATLNG) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where LATLNG='" + LATLNG + "'");
        db.close();
        Log.d(TAG,"RemoveNAME Successfuly");
    }

    public String getSdt(String id){
        String sdt;
        String getPlace = "SELECT SDT FROM " + TABLE_NAME + " WHERE ID="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getPlace, null);
        cursor.moveToFirst();
        sdt= cursor.getString(0);
        db.close();
        return sdt;
    }

    //Truy van co ket qua: SELECT
    public Cursor getData (){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + "";
        return database.rawQuery(sql,null);
    }
}
