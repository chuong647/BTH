package com.example.bai6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editHoTen,editSDT,editNgaySinh;
    RadioButton radioNam,radioNu;
    int vt;
    RadioGroup rg;
    Button btnLuu,btnXoa;
    Button btnCapNhat,btnTim;
    ListView listview;
    ListView listview1;
    ArrayList<Contact>list1;
    ArrayAdapter<Contact>adapter1;
    ArrayList<Contact>list2;
    ArrayAdapter<Contact>adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        registerForContextMenu(listview);
        editNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngaySinh();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ht=editHoTen.getText().toString();
                String sdt=editSDT.getText().toString();
                String ns=editNgaySinh.getText().toString();
                if(radioNam.isChecked())
                {
                    String gt=radioNam.getText().toString();
                    Contact ct1=new Contact(ht,sdt,ns,gt);

                    if(list1.contains(ct1))
                    {
                        Toast t=Toast.makeText(MainActivity.this," Trùng dữ liệu",Toast.LENGTH_LONG);
                        t.show();
                    }
                    else
                    {
                        list1.add(ct1);
                    }

                }
                else if(radioNu.isChecked())
                {
                    String gt=radioNu.getText().toString();
                    Contact ct1=new Contact(ht,sdt,ns,gt);
                    if(list1.contains(ct1))
                    {
                        Toast t=Toast.makeText(MainActivity.this," Trùng dữ liệu",Toast.LENGTH_LONG);
                        t.show();
                    }
                    else
                    {
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
                Contact ct=(Contact) parent.getItemAtPosition(i);
                vt=i;
                editHoTen.setText(ct.getHoTen());
                editSDT.setText((ct.getSDT()));
                editNgaySinh.setText(ct.getNgaySinh());
                if(ct.getGioiTinh().equals("Nam"))
                {
                    radioNam.setChecked(true);
                }
                if(ct.getGioiTinh().equals("Nữ"))
                {
                    radioNu.setChecked(true);
                }
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioNam.isChecked())
                {
                    Contact ctnew=new Contact(editHoTen.getText().toString(),editSDT.getText().toString(),editNgaySinh.getText().toString(),radioNam.getText().toString());
                    list1.set(vt,ctnew);


                }
                else if(radioNu.isChecked())
                {
                    Contact ctnew=new Contact(editHoTen.getText().toString(),editSDT.getText().toString(),editNgaySinh.getText().toString(),radioNu.getText().toString());
                    list1.set(vt,ctnew);

                }
                adapter1.notifyDataSetChanged();
                xoaTrang();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    list1.remove(vt);
                adapter1.notifyDataSetChanged();
                xoaTrang();
            }
        });

    }
    private void anhxa()
    {
        editHoTen=(EditText) findViewById(R.id.HoTen);
        editSDT=(EditText) findViewById(R.id.SDT);
        editNgaySinh=(EditText) findViewById(R.id.NgaySinh);
       radioNam=(RadioButton) findViewById(R.id.radio_Nam);
        radioNu=(RadioButton) findViewById(R.id.radio_Nu);
        btnCapNhat=(Button) findViewById(R.id.btnCapNhat);
       btnLuu=(Button) findViewById(R.id.btnLuu);
        btnXoa=(Button) findViewById(R.id.btnXoa);
        listview=(ListView) findViewById((R.id.listview));
        listview1=(ListView) findViewById((R.id.listview1));
        list1=new ArrayList<Contact>();
        adapter1=new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1,list1);
        listview.setAdapter(adapter1);
        list2=new ArrayList<Contact>();
        adapter2=new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1,list2);
        listview1.setAdapter(adapter2);
        rg=(RadioGroup) findViewById(R.id.RadioG);
    }
    void ngaySinh() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog dp = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);

                        SimpleDateFormat sdf =
                                new SimpleDateFormat("dd/MM/yyyy");

                        editNgaySinh.setText(sdf.format(calendar.getTime()));
                    }
                }, nam, thang, ngay);

        dp.show();
    }
    private void xoaTrang()
    {
        editHoTen.setText("");
        editSDT.setText("");
        editNgaySinh.setText("");

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuXoa:
                list1.remove(vt);
                adapter1.notifyDataSetChanged();
                xoaTrang();
                break;
            case R.id.menuXemChiTiet:
               Contact ct=list1.get(vt);
               String ht = ct.getHoTen().toString();
               String sdt= ct.getSDT().toString();
               String ns=ct.getNgaySinh().toString();
               String gt=ct.getGioiTinh().toString();
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Thông tin chi tiết");
                b.setMessage(" Họ và tên: "+ht+",\n SĐT: "+sdt+"\n Ngày sinh: "+ns+"\n Giới tính: "+gt+"");
                AlertDialog al = b.create();
                al.show();
                break;
            case R.id.menuCall:
                break;
        }
        return super.onContextItemSelected(item);
    }
}