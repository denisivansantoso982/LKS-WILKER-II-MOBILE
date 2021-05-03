package com.example.hotel_langit_7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private EditText email, pass;
    private Button button;
    private RequestQueue queue;
    private RelativeLayout loadingPanel;
    private Session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        getSupportActionBar().hide();

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        button = findViewById(R.id.btn);
        queue = Volley.newRequestQueue(context);
        loadingPanel = findViewById(R.id.loadingPanel);
        s = new Session(context);

        if (s.checkSession()) {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn){
            loadingPanel.setVisibility(View.VISIBLE);
            Log.d("Email", String.valueOf(email.getText()));
            Log.d("Password", String.valueOf(pass.getText()));

            if (String.valueOf(email.getText()) != "" || String.valueOf(pass.getText()) != ""){
                StringRequest request = new StringRequest(Request.Method.POST, ApiRequest.login, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", "onResponse: " + response);
                        if (response != null) {
                            try {
                                JSONObject object = new JSONObject(response);
                                Log.d("object JSON", "object JSON: " + object);

                                s.setTamu(object.getInt("id"), object.getString("nik"), object.getString("nama"), object.getString("email"), object.getString("nohp"), object.getString("alamat"));

                                Intent intent = new Intent(LoginActivity.this, LandingPageActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (JSONException e) {
                                loadingPanel.setVisibility(View.GONE);
                                AlertDialog dialog = new AlertDialog.Builder(context).create();
                                dialog.setTitle("Perhatian");
                                dialog.setMessage("Data user tidak ditemukan!");
                                dialog.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();
                            }
                        } else {
                            loadingPanel.setVisibility(View.GONE);
                            AlertDialog dialog = new AlertDialog.Builder(context).create();
                            dialog.setTitle("Perhatian");
                            dialog.setMessage("Data user tidak ditemukan!");
                            dialog.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                }
                            });

                            dialog.show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingPanel.setVisibility(View.GONE);
                        AlertDialog dialog = new AlertDialog.Builder(context).create();
                        dialog.setTitle("Error");
                        dialog.setMessage(error.getMessage());
                        dialog.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", String.valueOf(email.getText()));
                        params.put("password", String.valueOf(pass.getText()));

                        return params;
                    }
                };

                queue.add(request);

            } else {
                loadingPanel.setVisibility(View.GONE);
                AlertDialog dialog = new AlertDialog.Builder(context).create();
                dialog.setTitle("Perhatian");
                dialog.setMessage("Semua form wajib diisi!");
                dialog.setButton(Dialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
            }
        }
    }
}