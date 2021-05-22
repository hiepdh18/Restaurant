package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.OrderDetailDTO;
import com.example.restaurant.Database.CreateDatabase;

public class OrderDitailDAO {
    SQLiteDatabase database;

    public OrderDitailDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }
    public boolean checkExistDish(int orderId, int dishId){
        String query = "SELECt * FROM "+CreateDatabase.TB_ORDER_DETAIL+" WHERE "
                + CreateDatabase.TB_ORDER_DETAIL_ORDER+" = "+orderId+" AND "
                + CreateDatabase.TB_ORDER_DETAIL_DISH+" = "+dishId;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
    public int getAmountByOrderId(int orderId, int dishId){
        int amount=0;
        String query = "SELECT * FROM "+CreateDatabase.TB_ORDER_DETAIL+" WHERE "+CreateDatabase.TB_ORDER_DETAIL_ORDER+" = "+orderId+" AND "+CreateDatabase.TB_ORDER_DETAIL_DISH+" = "+dishId;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            amount = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_ORDER_DETAIL_AMOUNT));
            cursor.moveToNext();
        }
        return amount;
    }
    public  boolean updateAmount(OrderDetailDTO orderDetail){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_ORDER_DETAIL_AMOUNT,orderDetail.getAmount());
        long check = database.update(CreateDatabase.TB_ORDER_DETAIL,contentValues,CreateDatabase.TB_ORDER_DETAIL_ORDER+ " = "+orderDetail.getOrderId()
                + " AND " + CreateDatabase.TB_ORDER_DETAIL_DISH+" = "+orderDetail.getDishId(),null);
        if(check>0)
            return  true;
        return  false;

    }
    public boolean addOrderDetail(OrderDetailDTO orderDetail){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_ORDER_DETAIL_AMOUNT, orderDetail.getAmount());
        contentValues.put(CreateDatabase.TB_ORDER_DETAIL_DISH, orderDetail.getDishId());
        contentValues.put(CreateDatabase.TB_ORDER_DETAIL_ORDER, orderDetail.getOrderId());
        long check =database.insert(CreateDatabase.TB_ORDER_DETAIL,null,contentValues);
        if(check>0)
            return  true;
        return  false;
    }
}
