package com.gmail.agusafriyanto14;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class DaftarSmartphone extends AppCompatActivity {

    GridView gridView;
    ArrayList<Smartphone> list;
    SmartPhoneListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_smartphone_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new SmartPhoneListAdapter(this, R.layout.daftar_smartphone, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM hp");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Smartphone(name, price, image, id));
        }
        adapter.notifyDataSetChanged();

    }
}