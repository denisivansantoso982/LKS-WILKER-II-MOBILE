package com.example.hotel_langit_7;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private int id, total, nomor;
    private String jenis, tglBooking, tglCheckIn, tglCheckOut;

    public Booking(int id, int total, int nomor, String tglBooking, String tglCheckIn, String tglCheckOut, String jenis) {
        this.id = id;
        this.total = total;
        this.nomor = nomor;
        this.jenis = jenis;
        this.tglBooking = tglBooking;
        this.tglCheckIn = tglCheckIn;
        this.tglCheckOut = tglCheckOut;
    }

    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public int getNomor() {
        return nomor;
    }

    public String getTglBooking() {
        return tglBooking;
    }

    public String getTglCheckIn() {
        return tglCheckIn;
    }

    public String getTglCheckOut() {
        return tglCheckOut;
    }

    public String getJenis() {
        return jenis;
    }
}
