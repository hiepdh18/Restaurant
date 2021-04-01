package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayDesk  extends BaseAdapter implements View.OnClickListener {
    Context context;
    int layout;
    List<DeskDTO> listDesk;
    ViewHolder viewHolder;

    public AdapterDisplayDesk(Context context, int layout, List<DeskDTO> listDesk) {
        this.context = context;
        this.layout = layout;
        this.listDesk = listDesk;

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
        View view = convertView;
        if(view ==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            view = inflater.inflate(layout, parent, false);
            viewHolder.txtDeskName = view.findViewById(R.id.txtDeskName);
            viewHolder.imgdesk = view.findViewById(R.id.imgDesk);
            viewHolder.imgOrder = view.findViewById(R.id.imgOrder);
            viewHolder.imgPay = view.findViewById(R.id.imgPay);
            viewHolder.imgRemove = view.findViewById(R.id.imgRemove);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DeskDTO desk = listDesk.get(position);
        viewHolder.txtDeskName.setText(desk.getName());
        viewHolder.imgdesk.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolder = (ViewHolder) ((View)v.getParent()).getTag();
        switch (id) {
            case R.id.imgDesk:
                viewHolder.imgOrder.setVisibility(View.VISIBLE);
                viewHolder.imgPay.setVisibility(View.VISIBLE);
                viewHolder.imgRemove.setVisibility(View.VISIBLE);
                break;
        }

    }

    public class ViewHolder {
        TextView txtDeskName;
        ImageView imgdesk,imgOrder, imgPay, imgRemove;
    }
}
