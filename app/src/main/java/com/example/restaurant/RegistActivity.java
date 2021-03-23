package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.StaffDAO;
import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.FragmentApp.DatePickerFragment;


public class RegistActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText editTextUsername, editTextPasswd, editTextDateOfBirth, editTextIdentification;
    Button btnSubmit, btnCancel;
    RadioGroup radioGroupSex;
    StaffDAO staffDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_regist);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        editTextUsername = findViewById(R.id.username);
        editTextPasswd = findViewById(R.id.passwd);
        radioGroupSex = findViewById(R.id.sex);
        editTextDateOfBirth =findViewById(R.id.dateOfBirth);
        editTextIdentification =findViewById(R.id.identification);
        btnSubmit = findViewById(R.id.bntSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        editTextDateOfBirth.setOnFocusChangeListener(this);

        staffDAO = new StaffDAO(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bntSubmit:
                String strUsername = editTextUsername.getText().toString();
                String strPasswd = editTextPasswd.getText().toString();
                String strSex="";
                switch (radioGroupSex.getCheckedRadioButtonId()){
                    case R.id.male:
                        strSex = "Nam";break;
                    case R.id.female:
                        strSex = "Nữ";break;
                }
                String strDateOfBirth = editTextDateOfBirth.getText().toString();
                String strIdentification= editTextIdentification.getText().toString();
                if (strUsername == null || strUsername.equals("")){
                    Toast.makeText(this, R.string.alert_username, Toast.LENGTH_SHORT).show();
                }else if (strPasswd == null || strPasswd.equals("")){
                    Toast.makeText(this, R.string.alert_password, Toast.LENGTH_SHORT).show();
                } else {
                    StaffDTO staffDTO = new StaffDTO(strUsername,strPasswd,strSex,strDateOfBirth,strIdentification);
                    long check = staffDAO.addStuff(staffDTO);
                    if(check != 0 ){
                        Intent iLogin  = new Intent(this,LoginActivity.class);
                        startActivity(iLogin);
                    }else {
                        Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.dateOfBirth:
                if(hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Date of birth");
                }
                break;
        }

    }
}