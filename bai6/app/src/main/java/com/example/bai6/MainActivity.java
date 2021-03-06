package com.example.bai6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
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

    EditText editHoTen, editSDT, editNgaySinh;
    RadioButton radioNam, radioNu;
    int vt,vt2;
    RadioGroup rg;
    Button btnLuu, btnXoa,btnLoad;
    Button btnCapNhat, btnTim;
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
        editNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngaySinh();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ht = editHoTen.getText().toString();
                String sdt = editSDT.getText().toString();
                String ns = editNgaySinh.getText().toString();
                if (radioNam.isChecked()) {
                    String gt = radioNam.getText().toString();
                    Contact ct1 = new Contact(ht, sdt, ns, gt);

                    if (list1.contains(ct1)) {
                        Toast t = Toast.makeText(MainActivity.this, " Tr??ng d??? li???u", Toast.LENGTH_LONG);
                        t.show();
                    } else {
                        list1.add(ct1);
                    }

                } else if (radioNu.isChecked()) {
                    String gt = radioNu.getText().toString();
                    Contact ct1 = new Contact(ht, sdt, ns, gt);
                    if (list1.contains(ct1)) {
                        Toast t = Toast.makeText(MainActivity.this, " Tr??ng d??? li???u", Toast.LENGTH_LONG);
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
                editSDT.setText((ct.getSDT()));
                editNgaySinh.setText(ct.getNgaySinh());
                if (ct.getGioiTinh().equals("Nam")) {
                    radioNam.setChecked(true);
                }
                if (ct.getGioiTinh().equals("N???")) {
                    radioNu.setChecked(true);
                }
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioNam.isChecked()) {
                    Contact ctnew = new Contact(editHoTen.getText().toString(), editSDT.getText().toString(), editNgaySinh.getText().toString(), radioNam.getText().toString());
                    list1.set(vt, ctnew);


                } else if (radioNu.isChecked()) {
                    Contact ctnew = new Contact(editHoTen.getText().toString(), editSDT.getText().toString(), editNgaySinh.getText().toString(), radioNu.getText().toString());
                    list1.set(vt, ctnew);

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
                            Toast t = Toast.makeText(MainActivity.this, " D??? li???u ???? t??m ki???m", Toast.LENGTH_LONG);
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
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listview.setVisibility(View.VISIBLE);
                listview1.setVisibility(View.GONE);
                list2.clear();

            }
        });
    }

    private void anhxa() {
        editHoTen = (EditText) findViewById(R.id.HoTen);
        editSDT = (EditText) findViewById(R.id.SDT);
        editNgaySinh = (EditText) findViewById(R.id.NgaySinh);
        radioNam = (RadioButton) findViewById(R.id.radio_Nam);
        radioNu = (RadioButton) findViewById(R.id.radio_Nu);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnTim=(Button)findViewById(R.id.btnTim);
        listview = (ListView) findViewById((R.id.listview));
        listview1 = (ListView) findViewById((R.id.listview1));
        list1 = new ArrayList<Contact>();
        adapter1 = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, list1);
        listview.setAdapter(adapter1);
        list2 = new ArrayList<Contact>();
        adapter2 = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, list2);
        listview.setAdapter(adapter1);
        listview1.setAdapter(adapter2);
        btnLoad=(Button)findViewById(R.id.btnreset);
        rg = (RadioGroup) findViewById(R.id.RadioG);
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

    private void xoaTrang() {
        editHoTen.setText("");
        editSDT.setText("");
        editNgaySinh.setText("");

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuXoa:
                list1.remove(vt);
                adapter1.notifyDataSetChanged();
                list2.remove(vt2);
                adapter2.notifyDataSetChanged();
                xoaTrang();
                break;
            case R.id.menuXemChiTiet:
                Contact ct = list1.get(vt);
                String ht = ct.getHoTen().toString();
                String sdt = ct.getSDT().toString();
                String ns = ct.getNgaySinh().toString();
                String gt = ct.getGioiTinh().toString();
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Th??ng tin chi ti???t");
                b.setMessage(" H??? v?? t??n: " + ht + ",\n S??T: " + sdt + "\n Ng??y sinh: " + ns + "\n Gi???i t??nh: " + gt + "");
                AlertDialog al = b.create();
                al.show();
                break;
            case R.id.menuCall:
                break;
        }
        return super.onContextItemSelected(item);
    }



}