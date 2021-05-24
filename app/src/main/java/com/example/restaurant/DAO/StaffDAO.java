package com.example.restaurant.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;


public class StaffDAO {
    SQLiteDatabase database ;
    public StaffDAO(Context context) {

        CreateDatabase createDatabase = new CreateDatabase( context);
        database = createDatabase.open();
    }
    public int getRoleId(int staffId){
        int roleId =0;
        String q = "SELECT * FROM " +CreateDatabase.TB_STAFF +" WHERE "+CreateDatabase.TB_STAFF_ID + " = " + staffId;
        Cursor  cursor = database.rawQuery(q,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            roleId = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ROLE_ID));
            cursor.moveToNext();
        }
        return roleId;
    }
    public long addStuff(StaffDTO stuff){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_STAFF_USERNAME, stuff.getUsername());
        contentValues.put(CreateDatabase.TB_STAFF_PASSWD, stuff.getPassword());
        contentValues.put(CreateDatabase.TB_STAFF_SEX, stuff.getSex());
        contentValues.put(CreateDatabase.TB_STAFF_ROLE_ID,2);
        contentValues.put(CreateDatabase.TB_STAFF_BIRTH, stuff.getDateOfBirth());
        contentValues.put(CreateDatabase.TB_STAFF_IDEN, stuff.getIden());

        long check = database.insert(CreateDatabase.TB_STAFF, null, contentValues);
        System.out.println(check);
        return check;

    }
    public StaffDTO getStaff(int id){
        StaffDTO staff = new StaffDTO();
        String q = "SELECT * FROM " + CreateDatabase.TB_STAFF + " WHERE "
                + CreateDatabase.TB_STAFF_ID+" = " + id;
        Cursor cursor = database.rawQuery(q, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int i = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ID));
            String username = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_PASSWD));
            String sex= cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_SEX));
            String dateOfBirth =cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_BIRTH));
            String iden =cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_IDEN));
            String number= cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_NUMBER));
            String fullName = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_FULLNAME));
            String avatar = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_AVATAR));
            int roleId = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ROLE_ID));
            staff = new StaffDTO(i,roleId,username,password, sex,dateOfBirth,iden,number,fullName,avatar);
            cursor.moveToNext();
        }
        return staff;
    }
    public List<StaffDTO> getAllStaff(){
        List<StaffDTO> list = new ArrayList<StaffDTO>();

        String q = "SELECT * FROM " + CreateDatabase.TB_STAFF;
        Cursor cursor = database.rawQuery(q, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ID));
            String username = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_PASSWD));
            String sex= cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_SEX));
            String dateOfBirth =cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_BIRTH));
            String iden =cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_IDEN));
            String number= cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_NUMBER));
            String fullName = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_FULLNAME));
            String avatar = cursor.getString(cursor.getColumnIndex(CreateDatabase.TB_STAFF_AVATAR));
            StaffDTO staff = new StaffDTO(id,username,password, sex,dateOfBirth,iden,number,fullName,avatar);
            list.add(staff);
            cursor.moveToNext();
        }
        return list;
    }
    public boolean update(StaffDTO staff){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TB_STAFF_USERNAME, staff.getUsername());
        contentValues.put(CreateDatabase.TB_STAFF_PASSWD, staff.getPassword());
        contentValues.put(CreateDatabase.TB_STAFF_FULLNAME, staff.getFullName());
        contentValues.put(CreateDatabase.TB_STAFF_SEX, staff.getSex());
        contentValues.put(CreateDatabase.TB_STAFF_BIRTH, staff.getDateOfBirth());
        contentValues.put(CreateDatabase.TB_STAFF_NUMBER, staff.getNumber());
        contentValues.put(CreateDatabase.TB_STAFF_IDEN, staff.getIden());
        contentValues.put(CreateDatabase.TB_STAFF_AVATAR, staff.getAvatar());

        long check = database.update(CreateDatabase.TB_STAFF,contentValues,CreateDatabase.TB_STAFF_ID +" = " +staff.getId(),null);
        if(check>0) return true;
        return false;
    }
    public boolean deleteStaff(int id){
        StaffDTO staff = getStaff(id);
        long check =0;
        if(staff.getRole()!=1){
            check = database.delete(CreateDatabase.TB_STAFF,CreateDatabase.TB_STAFF_ID+" = "+id,null);
        }
        if(check>0)
            return true;
        return false;
    }
    public boolean hasStuff(){
        String query = "SELECT * FROM "+ CreateDatabase.TB_STAFF;
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() !=0)
            return true;
        else return false;
    }
    public int checkStuff(String u, String p){
        int staffId = 0;
        String query = " SELECT * FROM "+ CreateDatabase.TB_STAFF+" WHERE "+ CreateDatabase.TB_STAFF_USERNAME+" = '"+ u +"' AND " + CreateDatabase.TB_STAFF_PASSWD + " = '"+ p+"'";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            staffId = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TB_STAFF_ID));
            cursor.moveToNext();
        }
        return staffId;
    }

}
