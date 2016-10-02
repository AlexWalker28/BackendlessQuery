package com.example.alexwalker.backendlessquery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String appVersion = "v1";
        Backendless.initApp(this, "84699694-079F-E281-FFC6-0A251E47C500", "2E0782F0-954C-C2F3-FF8E-0622827A1700", appVersion);

        final EditText street = (EditText) findViewById(R.id.editText);
        final EditText apartmentType = (EditText) findViewById(R.id.editText2);
        final EditText price = (EditText) findViewById(R.id.editText3);
        final EditText floorCount = (EditText) findViewById(R.id.editText4);
        final EditText roomsCount = (EditText) findViewById(R.id.editText5);
        final Button showResultsButton = (Button) findViewById(R.id.button);
        final Button saveButton = (Button) findViewById(R.id.saveButton);
        final Button customButton = (Button) findViewById(R.id.customButton);
        final TextView resultsTextView = (TextView) findViewById(R.id.resultsTextView);
        listView = (ListView) findViewById(R.id.listView2);


        showResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userStreet = street.getText().toString();
                String userApartmentType = apartmentType.getText().toString();
                String userPrice = price.getText().toString();
                String userFloorCount = floorCount.getText().toString();
                String userRoomsCount = roomsCount.getText().toString();

                String streetArg = "street LIKE \'%\" + userStreet + \"%\'";
                String apartmentTypeArgOR = "OR apartmentType LIKE \'%\" + userApartmentType + \"%\'";
                String apartmentTypeArg = "apartmentType LIKE \'%\" + userApartmentType + \"%\'";
                String priceArgOR = "OR price = \" + userPrice + \"";
                String priceArg = "price = \" + userPrice + \"";
                String floorCountArgOR = "OR floorCount = \" + userFloorCount + \"";
                String floorCountArg = "floorCount = \" + userFloorCount + \"";
                String roomsCountArgOR = "OR roomsCount = \" + userRoomsCount";
                String roomsCountArg = "roomsCount = \" + userRoomsCount";
                StringBuilder wc = null;
                String op = "\'%\" ";
                String cl = "\"%\'";

                /*if (userStreet.equals("")) {
                } else wc.append(streetArg);
                if (userApartmentType.equals("")) {
                } else wc.append(apartmentTypeArg);
                if (userPrice.equals("")) {
                } else wc.append(priceArg);
                if (userFloorCount.equals("")) {
                } else wc.append(floorCountArg);
                if(userRoomsCount.equals("")){}else wc.append(roomsCountArg);*/


                String whereClause = /*wc.toString();*/ "street LIKE '%" + userStreet + "%' OR apartmentType LIKE '%" + userApartmentType + "%' OR price = " + userPrice + " OR floorCount = " + userFloorCount + " OR roomsCount = " + userRoomsCount;
                resultsTextView.setText(whereClause + "\n");
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);


                Backendless.Persistence.of(Sorting.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Sorting>>() {
                    @Override
                    public void handleResponse(final BackendlessCollection<Sorting> sortingBackendlessCollection) {

                        int size = sortingBackendlessCollection.getData().size();

                        String[] array = new String[size];
                        for (int j = 0; j < size; j++) {
                            Sorting content = sortingBackendlessCollection.getData().get(j);
                            array[j] = content.getStreet() + " " + content.getRoomsCount() + " " + content.getApartmentType() + " " +
                                    content.getFloorCount() + " " + content.getPrice();
                        }
                        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array);
                        listView.setAdapter(arrayAdapter);


                        for (int i = 0; i < size; i++) {
                            Sorting content = sortingBackendlessCollection.getData().get(i);
                            resultsTextView.append(content.getStreet() + " " + content.getApartmentType() + " " + content.getPrice() + " " + content.getFloorCount() + " " + content.getRoomsCount() + "\n");


                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        Toast.makeText(getApplicationContext(), "Didn't work", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Backendless.Persistence.save(new Sorting(street.getText().toString(), apartmentType.getText().toString(), price.getText().toString(), floorCount.getText().toString(), roomsCount.getText().toString()), new BackendlessCallback<Sorting>() {
                    @Override
                    public void handleResponse(Sorting sorting) {
                        Toast.makeText(getApplicationContext(), "Saved successfully", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomAdapterActivity.class);
                startActivity(intent);
            }
        });


    }
}
