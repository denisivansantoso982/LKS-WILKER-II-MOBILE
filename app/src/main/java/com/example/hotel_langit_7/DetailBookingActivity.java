package com.example.hotel_langit_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalTime;
import java.util.Locale;

public class DetailBookingActivity extends AppCompatActivity {
    private TextView jenis, nomor, booking, checkIn, checkOut;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aa");
    Date date1 = null, date2 = null, date3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        getSupportActionBar().setTitle("Detail Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        try {
            date1 = dateFormat.parse(getIntent().getStringExtra("tglBooking"));
            date2 = dateFormat.parse(getIntent().getStringExtra("tglCheckIn"));
            date3 = dateFormat.parse(getIntent().getStringExtra("tglCheckOut"));
        } catch (ParseException e) {
            Log.d("Error", "onCreate: " + e.getMessage());
        }

        jenis = findViewById(R.id.jenis_kamar);
        nomor = findViewById(R.id.nomor_kamar);
        booking = findViewById(R.id.detail_tgl_booking);
        checkIn = findViewById(R.id.detail_tanggal_check_in);
        checkOut = findViewById(R.id.detail_tgl_check_out);

        jenis.setText(getIntent().getStringExtra("jenis"));
        nomor.setText(String.valueOf(getIntent().getIntExtra("nomor", 0)));
        booking.setText(new SimpleDateFormat("E, dd MMM yyyy").format(date1));
        checkIn.setText(new SimpleDateFormat("E, dd MMM yyyy").format(date2));
        checkOut.setText(new SimpleDateFormat("E, dd MMM yyyy").format(date3));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return true;
    }
}