 package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.StaffDAO;


 public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
     Button btnLogin, btnRegist;
     EditText edUsername, edPassword;
     StaffDAO staffDAO;

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.layout_login);
 //        ActionBar actionBar = getSupportActionBar();
 //        actionBar.hide();
         staffDAO = new StaffDAO(this);
         edUsername = (EditText) findViewById(R.id.edUsername);
         edPassword = (EditText) findViewById(R.id.edPassword);
         btnLogin = (Button) findViewById(R.id.btnLogin);
         btnRegist = (Button) findViewById(R.id.btnRegist);
         displayButton();
         btnLogin.setOnClickListener(this);
         btnRegist.setOnClickListener(this);

     }

     @Override
     protected void onResume() {
         super.onResume();
         displayButton();
     }

     private  void displayButton(){
         boolean check = staffDAO.hasStuff();
         if (check){
             btnRegist.setVisibility(View.VISIBLE);
             btnLogin.setVisibility(View.VISIBLE);
         }
         else{
             btnRegist.setVisibility(View.VISIBLE);
             btnLogin.setVisibility(View.GONE);
         }
     }

     @Override
     public void onClick(View v) {
         int id = v.getId();
         switch (id){
             case R.id.btnLogin:
                 login();
                 break;
             case R.id.btnRegist:
                 regist();
                 break;
         }
     }
     private void login(){
         String username = edUsername.getText().toString();
         String passwd = edPassword.getText().toString();
         boolean check = staffDAO.checkStuff(username, passwd);
         if(check){
             Intent iHome = new Intent(this, HomeActivity.class );
             iHome.putExtra("username", edUsername.getText().toString());
             startActivity(iHome);
         }else
             Toast.makeText(this, "That bai", Toast.LENGTH_SHORT).show();
     }
     private void regist(){
         Intent iRegist = new Intent(this, RegistActivity.class);
         startActivity(iRegist);

     }
 }
