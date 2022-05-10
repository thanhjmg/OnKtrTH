package com.example.onktrth.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.onktrth.R;
import com.example.onktrth.activity.MainActivity_List;
import com.example.onktrth.model.ChiTieu;
import com.google.firebase.database.core.Context;

import java.util.List;

public class ChiTieuAdapter extends BaseAdapter {

    private MainActivity_List context;
    private int idLayout;
    private List<ChiTieu> list;

    public ChiTieuAdapter(MainActivity_List context, int idLayout, List<ChiTieu> list) {
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        }
        TextView txtTenChiTieu = view.findViewById(R.id.txtTenChiTieu);
        TextView txtSoTien = view.findViewById(R.id.txtSoTien);
        Button btnXoa = view.findViewById(R.id.btnXoa);

        ChiTieu chiTieu = list.get(i);
        txtTenChiTieu.setText(chiTieu.getTenChiTieu());
        txtSoTien.setText(chiTieu.getSoTien());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_List.class);
                Bundle bundle = new Bundle();

                bundle.putString("idChiTieu", chiTieu.getId() + "");
                bundle.putString("tenChiTieu", chiTieu.getTenChiTieu());
                bundle.putString("soTien", chiTieu.getSoTien());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity_List.class);
                Bundle bundle = new Bundle();

                bundle.putString("idChiTieu", chiTieu.getId() + "");
                bundle.putString("del", "del");

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
