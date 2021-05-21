package com.example.restaurant.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurant.Adapter.AdapterDisplayMenu;
import com.example.restaurant.AddDishActivity;
import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DTO.CategoryDTO;
import com.example.restaurant.HomeActivity;
import com.example.restaurant.R;

import java.util.List;

public class DisplayMenuFragment extends Fragment {
    public static int REQUEST_CODE_ADD_DISH = 20001;
    GridView gridView;
    CategoryDAO categoryDAO;
    List<CategoryDTO> listCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_dish,container, false);
        setHasOptionsMenu(true);
        categoryDAO = new CategoryDAO(getActivity());
        gridView = view.findViewById(R.id.gv_display_category);
        listCategory = categoryDAO.getAllCategory();
        AdapterDisplayMenu adapterDisplayMenu = new AdapterDisplayMenu(getActivity(),R.layout.layout_display_catgory, listCategory);
//        gridView.setAdapter(adapterDisplayMenu);
//        adapterDisplayMenu.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull  Menu menu, @NonNull  MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemAddDish = menu.add(1,R.id.item_add_dish,1,R.string.add_dish);
        itemAddDish.setIcon(R.drawable.logout);
        itemAddDish.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_add_dish){
            Intent intent = new Intent(getActivity(), AddDishActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_DISH);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_DISH  && resultCode == Activity.RESULT_OK){

        }
    }
}
