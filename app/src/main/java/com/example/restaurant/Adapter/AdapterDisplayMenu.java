package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.restaurant.DTO.CategoryDTO;

import java.util.List;

public class AdapterDisplayMenu extends BaseAdapter {
    Context context;
    int layout;
    List<CategoryDTO> listCat;

    public AdapterDisplayMenu(Context context, int layout, List<CategoryDTO> listCat) {
        this.context = context;
        this.layout = layout;
        this.listCat = listCat;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
