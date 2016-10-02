package com.example.alexwalker.backendlessquery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.backendless.BackendlessCollection;

import java.util.ArrayList;

public class CustomAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);


        BackendlessCollection image_details = getBEdata();
        final ListView listView = (ListView)findViewById(R.id.customListView);
        listView.setAdapter(new BackendlessCollectionAdapter(CustomAdapterActivity.this, image_details));

    }

    private BackendlessCollection getBEdata(){
        BackendlessCollection <Sorting> results = new BackendlessCollection<Sorting>();
        Sorting sorting  = new Sorting();
        sorting.getStreet();
        sorting.getApartmentType();
        sorting.getPrice();
        sorting.getFloorCount();
        sorting.getRoomsCount();
        return results;
    }
}
