package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.sharedpreferences.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    EditText name, email;
    Button SaveButton, button2;
    SharedPreferences sp;
    String nameStr, emailStr;
    private String sendUrl = "https://homeshoppingcartapp.000webhostapp.com/test/getData.php";
    RequestQueue requestQueue;
    JSONObject jsonObject = null;
    private static final String TAG = MainActivity.class.getSimpleName();
    int sucess;
    private String TAG_SUCESS = "sucess";
    private String TAG_MESSAGE = "message";
    private String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = findViewById(R.id.EtName);
        email = findViewById(R.id.EtEmail);
        SaveButton = findViewById(R.id.Save_Btn);
        button2 = findViewById(R.id.button2);
        requestQueue = Volley.newRequestQueue(this);



        //SaveButton.setOnClickListener();

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SaveButtonPressed(v);
                parseJson();

//                SharedPreferences.Editor editor =sp.edit();
//                editor.putString("email",emailStr);
//                editor.putString("name",nameStr);
//                editor.commit();


                //Toast.makeText(MainActivity.this, saved, Toast.LENGTH_SHORT).show();


            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OtherPage.class);
                startActivity(intent);


            }
        });

    }

    void parseJson(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, sendUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "onResponse: " + response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {


                        jsonObject = response.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");

                        Log.i(TAG, "onResponse: name: "+name);
                        Log.i(TAG, "onResponse: email: "+ email);
                        Log.i(TAG, "onResponse:------------------------------- ");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    public void SaveButtonPressed(View view) {
        Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
        nameStr = name.getText().toString();
        emailStr = email.getText().toString();
        String type = "login";
        InsertData insertData = new InsertData(this);
        insertData.execute(type, nameStr, emailStr);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}