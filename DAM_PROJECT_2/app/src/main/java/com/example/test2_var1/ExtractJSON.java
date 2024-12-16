package com.example.test2_var1;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractJSON extends AsyncTask<URL,Void,String> {

    public List<Customer> customerListJSON = new ArrayList<>();

    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection conexiune = null;

        try {
            conexiune = (HttpURLConnection) urls[0].openConnection();
            conexiune.setRequestMethod("GET");
            InputStream ist = conexiune.getInputStream();

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = null;
            String rezultat = "";
            while((linie=br.readLine())!=null)
                rezultat+=linie;

            //parsareJSONN
            parsareJSON(rezultat);

            return rezultat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parsareJSON(String jsonStr) {
        if(jsonStr!=null)
        {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                JSONArray customers = jsonObject.getJSONArray("clienti");

                for(int i=0;i<customers.length();i++)
                {
                    JSONObject obj = customers.getJSONObject(i);

                    String nume = obj.getString("Nume");
                    Date dataContract = new Date(obj.getString("DataContract"));
                    String tipAbonament = obj.getString("TipAbonament");
                    float pret = Float.parseFloat(obj.getString("Pret"));
                    boolean extraOptiuni = Boolean.parseBoolean(obj.getString("Extraoptiuni"));

                    Customer customer = new Customer(nume,dataContract,tipAbonament,pret,extraOptiuni);
                    customerListJSON.add(customer);

                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
