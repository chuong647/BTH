package com.example.bth52;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn_xoatrong, btn_luu, btn_capnhat, btn_timkiem;
    EditText ed_hoten, ed_sdt, ed_email;
    ListView listView;
    ArrayAdapter<ThongTin> adapter;
    ArrayList<ThongTin> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ht = ed_hoten.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String email = ed_email.getText().toString();
                ThongTin thongTin = new ThongTin(ht,sdt,email);
                if(arrayList.contains(thongTin))
                {
                    Toast.makeText(MainActivity.this,"Trùng dữ liệu",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    arrayList.add(thongTin);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ht = ed_hoten.getText().toString();
                String sdt = ed_sdt.getText().toString();
                String email = ed_email.getText().toString();
                ThongTin thongTin = new ThongTin(ht,sdt,email);
            }
        });
        btn_xoatrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_hoten.setText(null);
                ed_sdt.setText(null);
                ed_email.setText(null);
            }
        });
    }
    public void AnhXa()
    {
        ed_hoten = (EditText) findViewById(R.id.ed_hoten);
        ed_sdt = (EditText) findViewById(R.id.ed_sdt);
        ed_email = (EditText) findViewById(R.id.ed_email);
        listView = (ListView) findViewById(R.id.lv_hienthi);
        btn_luu = (Button) findViewById(R.id.btn_luu);
        btn_xoatrong = (Button) findViewById(R.id.btn_xoa);
        btn_capnhat = (Button) findViewById(R.id.btn_capnhat);
        btn_capnhat = (Button) findViewById(R.id.btn_capnhat);
    }
}