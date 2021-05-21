package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.example.restaurant.DAO.DeskDAO;
import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.HomeActivity;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayDesk  extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<DeskDTO> listDesk;

    DeskDAO deskDAO;
    ViewHolder viewHolder;
    FragmentManager fragmentManager;

    public AdapterDisplayDesk(Context context, int layout, List<DeskDTO> listDesk) {
        this.context = context;
        this.layout = layout;
        this.listDesk = listDesk;
        deskDAO = new DeskDAO(context);
    }
    @Override
    public int getCount() {
        return listDesk.size();
    }
    @Override
    public Object getItem(int position) {
        return listDesk.get(position);
    }
    @Override
    public long getItemId(int position) {
        return listDesk.get(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater =LayoutInflater.from(context);
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder.txtDeskName = convertView.findViewById(R.id.txt_desk_name);
            viewHolder.imgDesk = convertView.findViewById(R.id.img_desk);
            viewHolder.imgOrder = convertView.findViewById(R.id.img_order);
            viewHolder.imgPay = convertView.findViewById(R.id.img_pay);
            viewHolder.imgRemove = convertView.findViewById(R.id.img_remove);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(listDesk.get(position).isSelected()){
            loadButton();
        } else {
            hideButton();
        }
        DeskDTO desk = listDesk.get(position);
        viewHolder.txtDeskName.setText(desk.getName());
        viewHolder.imgDesk.setTag(position);

        viewHolder.imgDesk.setOnClickListener(this);
        viewHolder.imgOrder.setOnClickListener(this);
        viewHolder.imgPay.setOnClickListener(this);
        viewHolder.imgRemove.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View view) {
        viewHolder = (ViewHolder) ((View)view.getParent()).getTag();
        int id = view.getId();
        switch (id) {
            case R.id.img_desk:
//                String deskNAme = viewHolder.txtDeskName.getText().toString();
                int pos =(int) view.getTag();
                Toast.makeText(context,"vi tri " +pos,Toast.LENGTH_SHORT).show();
                listDesk.get(pos).setSelected(true);
                loadButton();
                break;
        }
    }
    private void loadButton(){
        viewHolder.imgOrder.setVisibility(View.VISIBLE);
        viewHolder.imgPay.setVisibility(View.VISIBLE);
        viewHolder.imgRemove.setVisibility(View.VISIBLE);
    }
    private void hideButton(){
        viewHolder.imgOrder.setVisibility(View.INVISIBLE);
        viewHolder.imgPay.setVisibility(View.INVISIBLE);
        viewHolder.imgRemove.setVisibility(View.INVISIBLE);
    }


    public class ViewHolder {
        TextView txtDeskName;
        ImageView imgDesk,imgOrder, imgPay, imgRemove;
    }


}
