package com.example.hotel_langit_7;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences preferences;

    public Session(Context context) {preferences = PreferenceManager.getDefaultSharedPreferences(context);}

    public void setTamu(int id, String nik, String nama, String email, String nohp, String alamat) {
        preferences.edit().putInt("id", id).commit();
        preferences.edit().putString("nik", nik).commit();
        preferences.edit().putString("nama", nama).commit();
        preferences.edit().putString("email", email).commit();
        preferences.edit().putString("nohp", nohp).commit();
        preferences.edit().putString("alamat", alamat).commit();
    }

    public int getId(){
        return preferences.getInt("id", 0);
    }

    public String getNik(){
        return preferences.getString("nik", "");
    }

    public String getNama(){
        return preferences.getString("nama", "");
    }

    public String getEmail(){
        return preferences.getString("email", "");
    }

    public String getNohp(){
        return preferences.getString("nohp", "");
    }

    public String getAlamat(){
        return preferences.getString("alamat", "");
    }
}
