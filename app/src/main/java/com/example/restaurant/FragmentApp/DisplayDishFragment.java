package com.example.restaurant.FragmentApp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.restaurant.Adapter.AdapterDisplayDish;
import com.example.restaurant.DAO.DishDAO;
import com.example.restaurant.DTO.DishDTO;
import com.example.restaurant.R;

import java.util.List;

public class DisplayDishFragment extends Fragment {
    GridView gridView;
    TextView textView;
    DishDAO dishDAO;
    List<DishDTO> listDish;
    int catId;
    @Nullable

    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_dish,container,false);
        gridView = view.findViewById(R.id.gv_display_dish);
        textView = view.findViewById(R.id.txt_cat_title);


        dishDAO = new DishDAO(getActivity());

        catId = getArguments().getInt("cat_id");
        String catName = getArguments().getString("cat_name");
        textView.setText(catName);

        loadDish();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN)
                    getFragmentManager().popBackStack("return", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                return true;
            }
        });

        return view;
    }

    private void loadDish(){
        listDish = dishDAO.getDishByCat(catId);
        AdapterDisplayDish adapterDisplayDish = new AdapterDisplayDish(getActivity(), R.layout.custom_layout_dish, listDish);
        gridView.setAdapter(adapterDisplayDish);
        adapterDisplayDish.notifyDataSetChanged();
    }
}
