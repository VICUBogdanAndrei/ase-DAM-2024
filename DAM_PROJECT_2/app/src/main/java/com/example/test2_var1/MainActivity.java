package com.example.test2_var1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Customer> listaCostumer = new ArrayList<>();
    public List<Customer> CSVlistaCustomer = new ArrayList<>();
    public List<Customer> JSONlistaCustomer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CustomersDB customersDB = CustomersDB.getInstance(getApplicationContext());
        customersDB.getCustomersDao().deleteAll();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }


    //https://pastebin.com/raw/e6JxUgsT
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.incarcaFisierJSON)
        {
            //extragere JSON in meniu
            ExtractJSON extractJSON = new ExtractJSON()
            {
                @Override
                protected void onPostExecute(String s) {
                    listaCostumer.addAll(customerListJSON);
                    JSONlistaCustomer.addAll(customerListJSON);

                    int nr = customerListJSON.size();

                    Toast.makeText(getApplicationContext(),
                            "Au fost incarcati " + nr + " clienti.", Toast.LENGTH_LONG).show();

                }
            };

            try {
                extractJSON.execute(new URL("https://cristianciurea.ase.ro/upload/customers.txt"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            return true;
        }
        else if(item.getItemId() == R.id.incarcaDateJSON)
        {
            //TO DO
            CustomersDB customersDB = CustomersDB.getInstance(getApplicationContext());
            customersDB.getCustomersDao().insert(JSONlistaCustomer);

            int nr = customersDB.getCustomersDao().count();
            Toast.makeText(getApplicationContext(),
                    "Au fost adaugati in BD " + nr + " clienti.", Toast.LENGTH_LONG).show();


            return true;
        }
        else if(item.getItemId() == R.id.despre)
        {
            Intent intent = new Intent(getApplicationContext(), Despre.class);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.salveazaDate)
        {
            CustomersDB customersDB = CustomersDB.getInstance(getApplicationContext());
            List<Customer> listaDB = new ArrayList<>();

            listaDB = customersDB.getCustomersDao().getCustomerOrdonat();

            TextView tw = findViewById(R.id.textView);
            tw.setText(listaDB.toString());

            return true;
        }
        else if(item.getItemId()==R.id.incarcaFisierXML)
        {
            ExtractXML extractXML = new ExtractXML(){
                @Override
                protected void onPostExecute(InputStream inputStream) {
                    listaCostumer.addAll(this.customerListCSV);
                    CSVlistaCustomer.addAll(this.customerListCSV);

                    int nr = customerListCSV.size();

                    Toast.makeText(getApplicationContext(),
                            "Au fost incarcati " + nr + " clienti.", Toast.LENGTH_LONG).show();

                }
            };

            try {
                extractXML.execute(new URL("https://pastebin.com/raw/e6JxUgsT"));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }


            return true;
        }
        else if(item.getItemId()==R.id.incarcaDateXML)
        {
            CustomersDB customersDB = CustomersDB.getInstance(getApplicationContext());
            customersDB.getCustomersDao().insert(CSVlistaCustomer);

            int nr = CSVlistaCustomer.size();
            Toast.makeText(getApplicationContext(),
                    "Au fost adaugati in BD " + nr + " clienti.", Toast.LENGTH_LONG).show();

            return true;
        }

        return false;
    }
}