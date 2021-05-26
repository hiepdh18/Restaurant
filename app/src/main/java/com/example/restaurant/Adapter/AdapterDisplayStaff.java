package com.example.restaurant.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.restaurant.DAO.StaffDAO;
import com.example.restaurant.DTO.DishDTO;
import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayStaff  extends BaseAdapter {
    private Context context;
    private int layout ;
    private List<StaffDTO> list;
    StaffDAO staffDAO;
    private ViewHolder viewHolder;

    public AdapterDisplayStaff(Context context, int layout, List<StaffDTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        staffDAO = new StaffDAO(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if( view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout,viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.avatar = view.findViewById(R.id.avatar_custom);
            viewHolder.txtName = view.findViewById(R.id.staff_name_custome);
            viewHolder.txtNumber = view.findViewById(R.id.sdt_custom);
            viewHolder.txtUsername = view.findViewById(R.id.username);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        StaffDTO staff = list.get(i);
        if(staff.getAvatar()!=null) viewHolder.avatar.setImageURI(Uri.parse(staff.getAvatar()));
        viewHolder.txtName.setText(staff.getFullName());
        viewHolder.txtNumber.setText(staff.getNumber());
        viewHolder.txtUsername.setText(staff.getUsername());

        return view;
    }
    class ViewHolder{
        ImageView avatar;
        TextView txtName;
        TextView txtNumber;
        TextView txtUsername;

    }
}
