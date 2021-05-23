package com.example.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.StaffDAO;
import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.FragmentApp.DatePickerFragment;
import com.example.restaurant.FragmentApp.DatePickerFragmentMod;

public class ModifyStaffActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    public int REQUEST_CODE_MODIFY_IMG = 10001;
    TextView username,password,fullname,number, iden, date;
    RadioGroup rdgroup;
    Button btnOk, btnCancel;
    ImageView avatar;
    StaffDAO staffDAO;
    StaffDTO staff;
    String img;
    int staffId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modify_staff);

        staffDAO = new StaffDAO(this);
        img ="";

        username = findViewById(R.id.username_staff_modify);
        password = findViewById(R.id.password_staff_modify);
        fullname = findViewById(R.id.fullname_staff_modify);
        number = findViewById(R.id.number_staff_modify);
        iden = findViewById(R.id.identification_staff_modify);
        date = findViewById(R.id.date_staff_modify);
        rdgroup = findViewById(R.id.sex_staff_modify);
        btnOk = findViewById(R.id.bnt_ok_staff_modify);
        btnCancel = findViewById(R.id.btn_cancel_staff_modify);
        avatar = findViewById(R.id.img_staff_modify);

        staffId = getIntent().getIntExtra("staff_id",0);
        if (staffId >0){
            staff = staffDAO.getStaff(staffId);
            username.setText(staff.getUsername());
            password.setText(staff.getPassword());
            fullname.setText(staff.getFullName());
            number.setText(staff.getNumber());
            iden.setText(staff.getIden());
            date.setText(staff.getDateOfBirth());
            if (staff.getAvatar()!=null) {
                avatar.setImageURI(Uri.parse(staff.getAvatar()));
                img = staff.getAvatar();
            }
        }
        avatar.setOnClickListener(this);
        date.setOnFocusChangeListener(this);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel_staff_modify:
                finish();
                break;
            case R.id.bnt_ok_staff_modify:
                String strSex="";
                switch (rdgroup.getCheckedRadioButtonId()){
                    case R.id.male:
                        strSex = "Nam";break;
                    case R.id.female:
                        strSex = "Nữ";break;
                }
                staff = new StaffDTO(staffId,username.getText().toString(),
                        password.getText().toString(),
                        strSex,date.getText().toString(),
                        iden.getText().toString(),
                        number.getText().toString(),
                        fullname.getText().toString(),
                        img);
                if(staffDAO.update(staff)){
                    setResult(Activity.RESULT_OK);
                    finish();
                }
                break;
            case R.id.img_staff_modify:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(intent,REQUEST_CODE_MODIFY_IMG);
                break;

        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if(view.getId()==R.id.date_staff_modify){
            if(b){
                DatePickerFragmentMod datePickerFragment = new DatePickerFragmentMod();
                datePickerFragment.show(getSupportFragmentManager(),"Date of birth");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_MODIFY_IMG && resultCode == Activity.RESULT_OK){
            img = data.getData().toString();
            avatar.setImageURI(data.getData());
        }
    }
}
