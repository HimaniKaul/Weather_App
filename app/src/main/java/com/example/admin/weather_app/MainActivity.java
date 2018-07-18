package com.example.admin.weather_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    int day,min,night,eve,morn,id;
    String main,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        mTextViewResult=(TextView) findViewById(R.id.textView);
        String url = "https://andfun-weather.udacity.com/weather";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject list = jsonArray.getJSONObject(i);

                                JSONArray weather=list.getJSONArray("weather");
                                for (int i1 = 0; i1 < weather.length(); i1++) {
                                    JSONObject list1 = weather.getJSONObject(i1);
                                     id = list1.getInt("id");
                                     main=list1.getString("main");
                                     description=list1.getString("description");}

                                     int dt = list.getInt("dt");
                                     JSONObject temp=list.getJSONObject("temp");
                                     day = temp.getInt("day");
                                     min = temp.getInt("min");
                                     night= temp.getInt("night");
                                     eve = temp.getInt("eve");
                                     morn = temp.getInt("morn");

                                mTextViewResult.append(String.valueOf(id)+ ", " + main +","+description+" , \n" +String.valueOf(day)+ ", "+String.valueOf(min)+ ", "+String.valueOf(night)+ ", " +String.valueOf(eve)+ ", "+ String.valueOf(morn)+ ",\n "+ String.valueOf(dt)+"\n\n" );
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}








