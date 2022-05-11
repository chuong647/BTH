package com.example.bai_5;

import androidx.appcompat.app.AppCompatActivity;

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
    EditText editHoten, editSdt, editEmail;
    Button btnXoatrang, btnCapnhap, btnLuu, btnTimkiem;
    ListView listView;
    ArrayAdapter<Contact> adapter;
    ArrayList<Contact> listSoll;
    int vt = 0;
    ArrayList<Contact> list2;
    ArrayAdapter<Contact> ad2;

    public void anhxa()
    {
        editHoten = (EditText) findViewById(R.id.editHoten);
        editSdt = (EditText) findViewById(R.id.editSdt);
        editEmail = (EditText) findViewById(R.id.editEmail);
        btnXoatrang = (Button) findViewById(R.id.btnXoatrang);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnCapnhap = (Button) findViewById(R.id.btnCapnhap);
        btnTimkiem = (Button) findViewById(R.id.btnTimkiem);
        listView = (ListView) findViewById(R.id.list);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        listSoll = new ArrayList<Contact>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSoll);
        list2 = new ArrayList<Contact>();
        ad2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list2);
        listView.setAdapter(adapter);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String show = editHoten.getText() + "-" + editSdt.getText();
                Contact contact = new Contact(editHoten.getText().toString(), editSdt.getText().toString(), editEmail.getText().toString());
                if(listSoll.contains(contact))
                {
                    Toast toast = Toast.makeText(MainActivity.this, "Đã tồn tại số diện thoại này", Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    listSoll.add(contact);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnXoatrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            editHoten.setText("");
            editSdt.setText("");
            editEmail.setText("");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Contact contact = (Contact) adapterView.getItemAtPosition(i);
                editHoten.setText(contact.getHoTen());
                editSdt.setText(contact.getSđt());
                editEmail.setText(contact.getEmail());
                vt = i;
            }
        });
        btnCapnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact(editHoten.getText().toString(), editSdt.getText().toString(), editEmail.getText().toString());
                listSoll.set(vt, contact);
                adapter.notifyDataSetChanged();
            }
        });
        btnTimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list2.clear();
                String hoTen = editHoten.getText().toString();
                if(!hoTen.equals("")){
                    for(Contact contact:listSoll){
                        if(contact.getHoTen().toString().contains(hoTen))
                        {
                            list2.add(contact);
                        }
                    }
                    listView.setAdapter(ad2);
                }
                else{
                    Toast to = Toast.makeText(MainActivity.this,"Bạn vui lòng nhập họ tên cần tìm", Toast.LENGTH_SHORT);
                    to.show();
                }
            }
        });
    }
}