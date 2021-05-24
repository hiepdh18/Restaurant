package com.example.restaurant.FragmentApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurant.DAO.OrderDetailDAO;
import com.example.restaurant.DTO.OrderDetailDTO;
import com.example.restaurant.HomeActivity;
import com.example.restaurant.R;


public class DisplayAnalysFragment extends Fragment {

    EditText edFrom, edTo;
    Button btnOk;
    TextView txtDoanhThu, txtDes;
    OrderDetailDAO orderDetailDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_analys,container,false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Thống kê");

        orderDetailDAO = new OrderDetailDAO(getActivity());

        edFrom = view.findViewById(R.id.date_from);
        edTo = view.findViewById(R.id.date_to);
        btnOk = view.findViewById(R.id.btn_analys);
        txtDes = view.findViewById(R.id.txtdes);
        txtDoanhThu = view.findViewById(R.id.txtdoanhthu);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = edFrom.getText().toString();
                String to = edTo.getText().toString();
                if(from== null || to ==null ||from.equals("")||to.equals("")){

                }else {
                    int dt = orderDetailDAO.getSales(from, to);
                    txtDes.setText("Doanh thu từ "+from+ " đến " + to);
                    txtDoanhThu.setText(String.valueOf(dt));
                }
            }
        });

        return view;
    }
}
