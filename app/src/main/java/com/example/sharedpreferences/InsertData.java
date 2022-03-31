package com.example.sharedpreferences;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertData extends AsyncTask<String, Void, String> {
    private static final String TAG = "InsertData";
    Context context;

    //

    //URL url;
    String nameStr,emailStr;
    AlertDialog dialog;
    BufferedReader reader = null;
    HttpURLConnection connection = null;


    InsertData(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String url_String = "https://homeshoppingcartapp.000webhostapp.com/test/getData.php";
        String type = params[0];
        nameStr = params[1];
        emailStr = params[2];

        if (type.equals("login")) {
            try {
                URL url = new URL(url_String);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(nameStr, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(emailStr, "UTF-8");

                bufferWriter.write(post_data);
                bufferWriter.flush();
                bufferWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));


                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    result += line;

                }


                //JSONObject jsonobj = new JSONObject(result.toString());
                //JSONArray jsonArray = result.getJSONArray("name");
                //String res = String.valueOf(jsonobj);
                bufferWriter.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d(TAG, "doInBackground: result  " + result);
                //Log.d(TAG, "doInBackground: jsonobj  "+jsonobj);
                //Log.d(TAG, "doInBackground: array  "+name);
                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }






    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        dialog.setMessage(result);

        try {
            JSONObject jsonData = new JSONObject(result);
            JSONArray itemsArray = jsonData.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
