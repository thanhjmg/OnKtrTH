package com.example.onktrth.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onktrth.R;
import com.example.onktrth.adapter.ChiTieuAdapter;
import com.example.onktrth.model.ChiTieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_List extends AppCompatActivity {

    private ListView listView;
    private Button btnadd;
    private Button btnUpdate;
    private TextView tvTenChiTieu;
    private TextView tvSoTien;
    private DatabaseReference mDatabase;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        tvTenChiTieu = findViewById(R.id.tvTenChiTieu);
        tvSoTien = findViewById(R.id.tvSoTien);
        btnadd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        listView = findViewById(R.id.lvwView);

        getData();

        Intent intent = getIntent();
        if(intent != null){
            String id = intent.getStringExtra("idChiTieu");
            String name = intent.getStringExtra("tenChiTieu");
            String money = intent.getStringExtra("soTien");

            tvTenChiTieu.setText(name);
            tvSoTien.setText(money);
            btnUpdate.setEnabled(true);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String _name = tvTenChiTieu.getText().toString().trim();
                    String _money = tvSoTien.getText().toString().trim();

                    writeNewUser(Integer.parseInt(id), _name, _money);
                    getData();
                }
            });
            if(intent.getStringExtra("del") != null){
                mDatabase.child("chiTieu").child(id).removeValue();
                getData();
            }
        }
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenChiTieu = tvTenChiTieu.getText().toString().trim();
                String soTien = tvSoTien.getText().toString().trim();

                writeNewUser(i,tenChiTieu,soTien);
                getData();
                ++i;
            }
        });
    }


    private void getData(){
        List<ChiTieu> list = new ArrayList<>();
        list.clear();

        mDatabase.child("chiTieu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for(DataSnapshot sn : snapshot.getChildren()){
                     ChiTieu ct = sn.getValue(ChiTieu.class);
                     list.add(ct);
                 }

                 i = list.size() +1;
                 loadDataToListView(list);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadDataToListView(List<ChiTieu> list) {
        tvTenChiTieu.setText("");
        tvSoTien.setText("");
        ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(this,R.layout.custom_item,list);
        listView.setAdapter(chiTieuAdapter);
    }
    private void writeNewUser(int id, String ten, String tien) {
        ChiTieu chiTieu = new ChiTieu(id,ten,tien);
        mDatabase.child("chiTieu").child(chiTieu.getId()+"").setValue(chiTieu);
    }
}