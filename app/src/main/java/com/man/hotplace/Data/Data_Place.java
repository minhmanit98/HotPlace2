package com.man.hotplace.Data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.man.hotplace.Model.Place;

import java.util.ArrayList;
import java.util.List;


public class Data_Place extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="database_place";
    private static final String TABLE_NAME="table_place";
    private static final String ID="id";
    private static final String TENDIADIEM="tenDiaDiem";
    private static final String MOTA="moTa";
    private static final String SDT="sdt";
    private static final String LONGITUDE="longitude";
    private static final String LATITUDE="latitude";
    private final String TAG = "DB";

    private static final int VERSION=1;
    String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " TEXT PRIMARY KEY, " +
            TENDIADIEM + " TEXT, " +
            MOTA + " TEXT, " +
            SDT + " TEXT, " +
            LONGITUDE + " TEXT, " +
            LATITUDE + " TEXT)";




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
    public void addPlace(Place place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, place.getId());
        values.put(TENDIADIEM, place.getTenDiaDiem());
        values.put(MOTA, place.getMoTa());
        values.put(SDT, place.getSdt());
        values.put(LONGITUDE, place.getLongitude());
        values.put(LATITUDE, place.getLatitude());
        db.insert(TABLE_NAME,null,values);
        Log.d("TT","Them thanh cong");
        db.close();
    }
    public List<Place> getPlace(){
        String getPlace = "SELECT * FROM " + TABLE_NAME + "";
        List<Place> listPlace =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getPlace, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Place sv = new Place(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
            listPlace.add(sv);
            cursor.moveToNext();
        }
        db.close();
        return listPlace;
    }
    public void removeId(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where ID='" + ID + "'");
        db.close();
        Log.d(TAG,"RemoveID Successfuly");
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
}
