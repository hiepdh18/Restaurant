package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.OrderDTO;
import com.example.restaurant.Database.CreateDatabase;

public class OrderDAO {
    SQLiteDatabase database;

    public OrderDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }
    public int addOrder(OrderDTO order){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_ORDER_STAFF,order.getStaffId());
        contentValues.put(CreateDatabase.TB_ORDER_TABLE,order.getDeskId());
        contentValues.put(CreateDatabase.TB_ORDER_STATUS,order.getStatus());
        contentValues.put(CreateDatabase.TB_ORDER_DATE,order.getDate());
        long check = database.insert(CreateDatabase.TB_ORDER,null, contentValues);
        if(check > 0){
            return (int) check;
        } else
            return 0;
    }

}
