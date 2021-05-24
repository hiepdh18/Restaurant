package com.example.restaurant.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.RoleDTO;
import com.example.restaurant.Database.CreateDatabase;

public class RoleDAO {
    SQLiteDatabase database ;

    public RoleDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase( context);
        database = createDatabase.open();
    }
//    public RoleDTO getRole(int id){
//        String
//    }
}
