package com.man.hotplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.man.hotplace.Data.Data_Place;
import com.man.hotplace.Model.Place;

public class InputInformation_Activity extends AppCompatActivity {
    private TextView tvId;
    private EditText edtName;
    private EditText edtMoTa;
    private EditText edtPhoneNumber;
    private TextView tvLatitude;
    private TextView tvLongitude;
    private Button btSave;
    private Button btCancel;
    private Data_Place data_place;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_information_activity);

        tvId = (TextView) findViewById(R.id.tv_id);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtMoTa = (EditText) findViewById(R.id.edt_mota);
        edtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
        btSave = (Button) findViewById(R.id.bt_save);
        btCancel = (Button) findViewById(R.id.bt_cancel);
        tvLatitude = (TextView) findViewById(R.id.tv_latitude);
        tvLongitude = (TextView) findViewById(R.id.tv_longitude);

        data_place = new Data_Place(this);

        // lay du lieu duoc gui qua
        Intent intent = getIntent();
        try{
            tvId.setText(intent.getStringExtra("id_key"));
            edtName.setText(intent.getStringExtra("placename_key"));
            edtMoTa.setText(intent.getStringExtra("mota_key"));
            edtPhoneNumber.setText(intent.getStringExtra("phonenumber_key"));
            tvLongitude.setText(intent.getStringExtra("longitude_key"));
            tvLatitude.setText(intent.getStringExtra("latitude_key"));
        }catch (Exception ex){
            Toast.makeText(InputInformation_Activity.this,"Lỗi: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy thông tin từ các ô nhập liệu
                String id = tvId.getText().toString();
                String name = edtName.getText().toString();
                String mota = edtMoTa.getText().toString();
                String phone_number = edtPhoneNumber.getText().toString();

                String latitude = tvLatitude.getText().toString();
                String longitude = tvLongitude.getText().toString();
                Place place = new Place(id,name,mota,phone_number,longitude,latitude);

                if (name.equals("") || mota.equals("") || phone_number.equals("")){
                    Toast.makeText(InputInformation_Activity.this, "Các hàng không được để trống!!", Toast.LENGTH_LONG).show();
                }else{
                    if (place!=null){
                        data_place.addPlace(place);
                        Toast.makeText(InputInformation_Activity.this,"Đã lưu",Toast.LENGTH_SHORT).show();

                        finish(); // đóng 1 activity sau khi nhập và lưu thông tin
                    }else{
                        Toast.makeText(InputInformation_Activity.this,"Lưu thất bại do null",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // hủy bỏ nhập thông tin, quay lại bản đồ
            }
        });
    }
}
