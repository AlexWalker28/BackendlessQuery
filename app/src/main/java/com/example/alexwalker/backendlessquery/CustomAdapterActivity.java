package com.example.alexwalker.backendlessquery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;

public class CustomAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);


        Backendless.Persistence.of(Sorting.class).find(new AsyncCallback<BackendlessCollection<Sorting>>() {
            @Override
            public void handleResponse(BackendlessCollection<Sorting> sortingBackendlessCollection) {
                final ListView listView = (ListView)findViewById(R.id.customListView);
                listView.setAdapter(new BackendlessCollectionAdapter(CustomAdapterActivity.this, sortingBackendlessCollection));
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Toast.makeText(getApplicationContext(), "Didn't work (CustomAdapterActivity)", Toast.LENGTH_LONG).show();
            }
        });

        /*BackendlessCollection image_details = getBEdata();*/


    }

    /*private BackendlessCollection getBEdata(){
        BackendlessCollection <Sorting> results = new BackendlessCollection<Sorting>();
        Sorting sorting  = new Sorting();
        sorting.getStreet();
        sorting.getApartmentType();
        sorting.getPrice();
        sorting.getFloorCount();
        sorting.getRoomsCount();
        return results;
    }*/
}
