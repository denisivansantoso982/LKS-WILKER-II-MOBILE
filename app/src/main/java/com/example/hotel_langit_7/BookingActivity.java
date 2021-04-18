package com.example.hotel_langit_7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {
    Context context;
    RequestQueue queue;
    RecyclerView recyclerView;
    ArrayList<Booking> data;
    BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getSupportActionBar().setTitle("Check Booking");

        context = BookingActivity.this;
        queue = Volley.newRequestQueue(context);
        recyclerView = findViewById(R.id.recycler_booking);
        data = new ArrayList<>();
        data.clear();
        Session s = new Session(context);

        StringRequest request = new StringRequest(Request.Method.POST, ApiRequest.check, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray arr = new JSONArray(response);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject object = arr.getJSONObject(i);
                        Log.d("response", "onResponse: " + object.toString());
                        data.add(new Booking(object.getInt("id"), object.getInt("total"), object.getInt("nomor"), object.getString("tgl_booking"), object.getString("tgl_check_in"), object.getString("tgl_check_out"), object.getString("jenis")));
                    }

                    Log.d("DATA", "data: " + data.toString());

                    adapter = new BookingAdapter(context, data);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "onErrorResponse: " + error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", s.getEmail());
                params.put("nik", s.getNik());

                return params;
            }
        };

        queue.add(request);
    }
}