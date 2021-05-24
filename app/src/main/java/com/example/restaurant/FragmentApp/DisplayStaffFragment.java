package com.example.restaurant.FragmentApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurant.Adapter.AdapterDisplayStaff;
import com.example.restaurant.DAO.StaffDAO;
import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.HomeActivity;
import com.example.restaurant.ModifyStaffActivity;
import com.example.restaurant.R;
import com.example.restaurant.RegistActivity;

import java.util.List;

public class DisplayStaffFragment extends Fragment {
    public static int REQUEST_CODE_MOD = 20001;
    public static int REQUEST_CODE_ADD = 20002;
    ListView listView;
    StaffDAO staffDAO;
    List<StaffDTO> listsStaff;
    int roleId;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_staff, container,false);
        setHasOptionsMenu(true);
        roleId = getActivity().getSharedPreferences("role", Context.MODE_PRIVATE).getInt("role_id",0);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Nhân viên");
        listView = view.findViewById(R.id.lv_display_staff);
        staffDAO = new StaffDAO(getActivity());
        loadlistview();
        registerForContextMenu(listView);

        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull  ContextMenu menu, @NonNull  View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(roleId ==1 )
            getActivity().getMenuInflater().inflate(R.menu.menu_edit_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull  MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int staffId = listsStaff.get(menuInfo.position).getId();
        if(item.getItemId() == R.id.menu_modify){
            Intent intent = new Intent(getActivity(), ModifyStaffActivity.class);
            intent.putExtra("staff_id",staffId);
            startActivityForResult(intent, REQUEST_CODE_MOD);
        }
        if(item.getItemId() == R.id.menu_delete){
            boolean check = staffDAO.deleteStaff(staffId);
            
            if (check)  loadlistview();
            else Toast.makeText(getActivity(), "That bai", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if(roleId ==1 ){
            MenuItem itemAddDesk = menu.add(1,R.id.item_add_staff,1,R.string.add_staff);
            itemAddDesk.setIcon(R.drawable.waiter);
            itemAddDesk.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        Intent a = new Intent(getActivity(), RegistActivity.class);
        startActivityForResult(a,REQUEST_CODE_ADD );
        return super.onOptionsItemSelected(item);
    }

    private void loadlistview(){
        listsStaff = staffDAO.getAllStaff();
        AdapterDisplayStaff adapterDisplayStaff = new AdapterDisplayStaff(getActivity(),R.layout.custom_layout_staff, listsStaff);
        listView.setAdapter(adapterDisplayStaff);
        adapterDisplayStaff.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_MOD && resultCode == Activity.RESULT_OK){
            loadlistview();
        }
        if(requestCode == REQUEST_CODE_ADD && resultCode == Activity.RESULT_OK){
            loadlistview();
        }
    }
}
