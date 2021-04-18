package com.example.hotel_langit_7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailBookingActivity extends AppCompatActivity {
    private TextView jenis, nomor, booking, checkIn, checkOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);

        getSupportActionBar().setTitle("Detail Booking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        jenis = findViewById(R.id.jenis_kamar);
        nomor = findViewById(R.id.nomor_kamar);
        booking = findViewById(R.id.detail_tgl_booking);
        checkIn = findViewById(R.id.detail_tanggal_check_in);
        checkOut = findViewById(R.id.detail_tgl_check_out);

        jenis.setText(getIntent().getStringExtra("jenis"));
        nomor.setText(String.valueOf(getIntent().getIntExtra("nomor", 0)));
        booking.setText(getIntent().getStringExtra("tglBooking"));
        checkIn.setText(getIntent().getStringExtra("tglCheckIn"));
        checkOut.setText(getIntent().getStringExtra("tglCheckOut"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();

        return true;
    }
}