package com.example.ktgk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editMSV, editHoTen, editSoTC;
    RadioButton radio101, radio102, radio103;
    int vt,vt2;
    RadioGroup rg;
    Button btnThem, btnXoa, btnTim;
    ListView listview;
    ListView listview1;
    ArrayList<Contact> list1;
    ArrayAdapter<Contact> adapter1;
    ArrayList<Contact> list2;
    ArrayAdapter<Contact> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        registerForContextMenu(listview);
        registerForContextMenu(listview1);
        //editSoTC.setOnClickListener(new View.OnClickListener() {
            //@Override
           //public void onClick(View view) {(SoTC());}
        //});
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ht = editHoTen.getText().toString();
                String msv = editMSV.getText().toString();
                String stc = editSoTC.getText().toString();
                if (radio101.isChecked()) {
                    String gt = radio101.getText().toString();
                    Contact ct1 = new Contact(ht, msv,stc);

                    if (list1.contains(ct1)) {
                        Toast t = Toast.makeText(MainActivity.this, " Trùng dữ liệu", Toast.LENGTH_LONG);
                        t.show();
                    } else {
                        list1.add(ct1);
                    }

                } else if (radio102.isChecked()) {
                    String gt = radio102.getText().toString();
                    Contact ct1 = new Contact(ht, msv, stc);
                    if (list1.contains(ct1)) {
                        Toast t = Toast.makeText(MainActivity.this, " Trùng dữ liệu", Toast.LENGTH_LONG);
                        t.show();
                    } else {
                        list1.add(ct1);
                    }
                } else if (radio103.isChecked()){
                    String gt = radio103.getText().toString();
                    Contact ct1 = new Contact(ht, msv, stc);
                    if (list1.contains(ct1)) {
                        Toast t = Toast.makeText(MainActivity.this, " Trùng dữ liệu", Toast.LENGTH_LONG);
                        t.show();
                    } else {
                        list1.add(ct1);
                    }
                }
                adapter1.notifyDataSetChanged();
                xoaTrang();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Contact ct = (Contact) parent.getItemAtPosition(i);
                vt = i;
                editHoTen.setText(ct.getHoTen());
                editMSV.setText((ct.getMSV()));
                editSoTC.setText(ct.getSoTC());
                if (ct.getSoTC().equals("101")) {
                    radio101.setChecked(true);
                }
                if (ct.getSoTC().equals("102")) {
                    radio102.setChecked(true);
                }
                if (ct.getSoTC().equals("103")) {
                    radio103.setChecked(true);
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list1.remove(vt);
                adapter1.notifyDataSetChanged();

                list2.remove(vt2);
                adapter2.notifyDataSetChanged();
                xoaTrang();

            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.setVisibility(View.GONE);
                listview1.setVisibility(View.VISIBLE);
                for (int i=0; i < list1.size(); i++) {
                    Contact ct=list1.get(i);
                    if(editHoTen.getText().toString().equals(ct.getHoTen()))
                    {

                        vt2=i;
                        if(list2.contains(ct))
                        {
                            Toast t = Toast.makeText(MainActivity.this, " Dữ liệu đã tìm kiếm", Toast.LENGTH_LONG);
                            t.show();
                        }
                        else {
                            list2.clear();
                            list2.add(ct);
                        }

                    }

                }
                adapter2.notifyDataSetChanged();
            }
        });

    }

    private void anhxa() {
        editHoTen = (EditText) findViewById(R.id.HoTen);
        editMSV = (EditText) findViewById(R.id.MSV);
        editSoTC = (EditText) findViewById(R.id.SoTC);
        radio101 = (RadioButton) findViewById(R.id.tc101);
        radio102 = (RadioButton) findViewById(R.id.tc102);
        radio103 = (RadioButton) findViewById(R.id.tc103);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnTim = (Button) findViewById(R.id.btnTim);
        listview = (ListView) findViewById((R.id.listview));
        listview1 = (ListView) findViewById((R.id.listview1));
        list1 = new ArrayList<Contact>();
        adapter1 = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, list1);
        listview.setAdapter(adapter1);
        list2 = new ArrayList<Contact>();
        adapter2 = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, list2);
        listview.setAdapter(adapter1);
        listview1.setAdapter(adapter2);
        rg = (RadioGroup) findViewById(R.id.RadioG);
    }

    private void xoaTrang() {
        editHoTen.setText("");
        editMSV.setText("");
        editSoTC.setText("");
    }

}