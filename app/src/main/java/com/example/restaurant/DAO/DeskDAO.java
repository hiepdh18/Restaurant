package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class DeskDAO {
    SQLiteDatabase database;
    public DeskDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean AddDesk(String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_TABLE_NAME,name);
        contentValues.put(CreateDatabase.TB_TABLE_STATUS,"false");
        long check = database.insert(CreateDatabase.TB_TABLE,null, contentValues);
        if(check>0) {
            return true;
        }
        else {
            return false;
        }
    }
    public List<DeskDTO> getAllDesk(){
        List<DeskDTO> listDesk = new ArrayList<DeskDTO>();
        String sql = "SELECT * FROM "+CreateDatabase.TB_TABLE;
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DeskDTO desk = new DeskDTO();
            desk.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_TABLE_ID)));
            desk.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TABLE_NAME)));
//            desk.setSelected(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_TABLE_STATUS)));
            listDesk.add(desk);
            cursor.moveToNext();
        }
        return listDesk;
    }
}
