package com.example.test2_var1;

import android.os.AsyncTask;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractXML extends AsyncTask<URL, Void, InputStream> {

    InputStream ist =null;
    public List<Customer> customerListCSV = new ArrayList<>();

    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection conexiune = null;

        try {
            conexiune = (HttpURLConnection) urls[0].openConnection();
            conexiune.setRequestMethod("GET");
            ist = conexiune.getInputStream();

            parsareCSV(ist);
            return ist;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void parsareCSV(InputStream ist)
    {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(ist));
            String linie;
            boolean isHeader = true;

            while ((linie = reader.readLine()) != null)
            {
                if(isHeader)
                {
                    isHeader=false;
                    continue;
                }
                String[] valori = linie.split(",");

                //verifica cate coloane sunt
                if (valori.length == 5)
                {
                    String nume = valori[0].trim();
                    Date dataContract = new Date(valori[1].trim());
                    String tipAbonament = valori[2].trim();
                    float pret = Float.parseFloat(valori[3].trim());
                    boolean extraOptiuni = Boolean.parseBoolean(valori[4].trim());

                    Customer customer = new Customer(nume, dataContract, tipAbonament,pret,extraOptiuni);

                    customerListCSV.add(customer);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
