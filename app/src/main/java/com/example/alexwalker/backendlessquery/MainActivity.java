package com.example.alexwalker.backendlessquery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

public class MainActivity extends AppCompatActivity {


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
        final Button clearButton = (Button) findViewById(R.id.clearButton);
        final TextView resultsTextView = (TextView) findViewById(R.id.resultsTextView);


        showResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userStreet = street.getText().toString();
                String userApartmentType = apartmentType.getText().toString();
                String userPrice = price.getText().toString();
                String userFloorCount = floorCount.getText().toString();
                String userRoomsCount = roomsCount.getText().toString();

                /*String streetArg = "street LIKE";
                String apartmentTypeArg = "apartmentType LIKE";
                String priceArg = "price <=";
                String floorCountArg = "floorCount = ";
                String roomsCountArg = "roomsCount = ";
                String wc;
                String op = "\'%\" ";
                String cl = "\"%\'";

                if (userStreet.equals("")) {
                } else wc = "\"" + streetArg + op + userStreet + cl + "\"";
                if (userApartmentType.equals("")) {
                } else  wc = "\"" + op + userStreet + cl + apartmentTypeArg + "\"";*/


                String whereClause = "street LIKE '%" + userStreet + "%' OR apartmentType LIKE '%" + userApartmentType + "%' OR price <= " + userPrice + " OR floorCount = " + userFloorCount + " OR roomsCount = " + userRoomsCount;
                resultsTextView.setText(whereClause + "\n");
                BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                dataQuery.setWhereClause(whereClause);


                Backendless.Persistence.of(Sorting.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Sorting>>() {
                    @Override
                    public void handleResponse(final BackendlessCollection<Sorting> sortingBackendlessCollection) {

                        int size = sortingBackendlessCollection.getData().size();
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

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsTextView.setText(" ");
            }
        });


    }
}
