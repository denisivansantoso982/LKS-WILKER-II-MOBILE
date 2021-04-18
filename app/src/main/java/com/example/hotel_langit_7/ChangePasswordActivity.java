package com.example.hotel_langit_7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText passLama, passBaru, passKonfirmasi;
    private Button btnUbah;
    private Session s;
    private TextView lama, baru, konfirmasi;
    private RequestQueue queue;
    private RelativeLayout loadingPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        getSupportActionBar().setTitle("Ganti Password");

        passLama = findViewById(R.id.password);
        passBaru = findViewById(R.id.newPassword);
        passKonfirmasi = findViewById(R.id.confirmPassword);
        btnUbah = findViewById(R.id.btn_ubah);
        lama = findViewById(R.id.hint_password);
        baru = findViewById(R.id.hint_new);
        konfirmasi = findViewById(R.id.hint_confirmation);
        loadingPanel = findViewById(R.id.loadingPanel);
        queue = Volley.newRequestQueue(getApplicationContext());

        s = new Session(getApplicationContext());

        passLama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                Log.d("Password", "afterTextChanged: " + password);

                if (password.length() < 8){
                    lama.setVisibility(View.VISIBLE);
                    btnUbah.setEnabled(false);
                }
                else if (password.length() >= 8){
                    lama.setVisibility(View.INVISIBLE);
                    btnUbah.setEnabled(true);
                }
            }
        });

        passBaru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = passBaru.getText().toString();

                if (password.length() < 8) {
                    baru.setVisibility(View.VISIBLE);
                    btnUbah.setEnabled(false);
                } else {
                    baru.setVisibility(View.INVISIBLE);
                    btnUbah.setEnabled(true);
                }
            }
        });

        passKonfirmasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = passKonfirmasi.getText().toString();

                if (password.equals(passBaru.getText().toString())) {
                    konfirmasi.setVisibility(View.INVISIBLE);
                    btnUbah.setEnabled(true);
                } else {
                    konfirmasi.setVisibility(View.VISIBLE);
                    btnUbah.setEnabled(false);
                }
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPanel.setVisibility(View.VISIBLE);
                if (passLama.getText().toString().equalsIgnoreCase("") || passBaru.getText().toString().equalsIgnoreCase("") || passKonfirmasi.getText().toString().equalsIgnoreCase("")) {
                    loadingPanel.setVisibility(View.GONE);
                    AlertDialog dialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                    dialog.setTitle("Perhatian!");
                    dialog.setMessage("Form pengisian tidak boleh kosong!");
                    dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    StringRequest request = new StringRequest(Request.Method.POST, ApiRequest.changePasword, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", "onResponse: " + response);
                            try {
                                if (response.equals("\"Success\"")) {
                                    loadingPanel.setVisibility(View.GONE);
                                    AlertDialog dialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                                    dialog.setTitle("Berhasil");
                                    dialog.setMessage("Password anda berhasil diubah!");
                                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                } else {
                                    loadingPanel.setVisibility(View.GONE);
                                    AlertDialog dialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                                    dialog.setTitle("Gagal");
                                    dialog.setMessage("Password anda salah!");
                                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                    passLama.requestFocus();
                                }
                            } catch (Exception e) {
                                loadingPanel.setVisibility(View.GONE);
                                AlertDialog dialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                                dialog.setTitle("Error");
                                dialog.setMessage(e.getMessage());
                                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
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
                            Log.d("Error Message", "onErrorResponse: " + error.getMessage());
                            loadingPanel.setVisibility(View.GONE);
                            AlertDialog dialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
                            dialog.setTitle("Error");
                            dialog.setMessage("Terjadi kesalahan");
                            dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", s.getEmail());
                            params.put("password", passLama.getText().toString());
                            params.put("confirm", passKonfirmasi.getText().toString());

                            return params;
                        }
                    };

                    queue.add(request);
                }
            }
        });
    }
}