package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.RegexValidator;

import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.Database.CreateDatabase;


public class StaffDAO {
    SQLiteDatabase database ;
    public StaffDAO(Context context) {

        CreateDatabase createDatabase = new CreateDatabase( context);
        database = createDatabase.open();
    }
    public long addStuff(StaffDTO stuff){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_STAFF_USERNAME, stuff.getUsername());
        contentValues.put(CreateDatabase.TB_STAFF_PASSWD, stuff.getPassword());
        contentValues.put(CreateDatabase.TB_STAFF_SEX, stuff.getSex());
        contentValues.put(CreateDatabase.TB_STAFF_BIRTH, stuff.getDateOfBirth());
        contentValues.put(CreateDatabase.TB_STAFF_IDEN, stuff.getIden());

        long check = database.insert(CreateDatabase.TB_STAFF, null, contentValues);
        return check;

    }
    public boolean hasStuff(){
        String query = "SELECT * FROM "+ CreateDatabase.TB_STAFF;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() !=0)
            return true;
        else return false;
    }
    public boolean checkStuff(String u, String p){
        String query = " SELECT * FROM "+ CreateDatabase.TB_STAFF+" WHERE "+ CreateDatabase.TB_STAFF_USERNAME+" = '"+ u +"' AND " + CreateDatabase.TB_STAFF_PASSWD + " = '"+ p+"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() !=0)
            return true;
        else return false;
    }

}
