package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.CategoryDTO;
import com.example.restaurant.DTO.DishDTO;
import com.example.restaurant.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    SQLiteDatabase sqLiteDatabase;

    public CategoryDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        sqLiteDatabase = createDatabase.open();
    }
    public boolean addCatgory(CategoryDTO cat){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_CATEGORY_NAME, cat.getName());
        long check = sqLiteDatabase.insert(CreateDatabase.TB_CATEGORY,null, contentValues);
        if(check>0) return true;
        return false;
    }
    public List<CategoryDTO> getAllCategory(){
        List<CategoryDTO> listCat = new ArrayList<CategoryDTO>();
        String sql = "SELECT  * from "+ CreateDatabase.TB_CATEGORY;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            CategoryDTO cat = new CategoryDTO();
            cat.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_ID)));
           cat.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_NAME)));
            listCat.add(cat);
            cursor.moveToNext();
        }
        return listCat;
    }
    public CategoryDTO getCategoryById(int id){
        String query = "SELECT * FROM "+ CreateDatabase.TB_CATEGORY + " WHERE "+ CreateDatabase.TB_CATEGORY_ID +" = " +id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        CategoryDTO cat = new CategoryDTO();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            cat.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_ID)));
            cat.setName(cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_CATEGORY_NAME)));
            cursor.moveToNext();
        }
        return cat;

    }

}
