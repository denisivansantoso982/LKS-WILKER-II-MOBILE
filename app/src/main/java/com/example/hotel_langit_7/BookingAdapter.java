package com.example.hotel_langit_7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    Context context;
    ArrayList<Booking> dataList;

    public BookingAdapter(Context context, ArrayList<Booking> data) {
        this.context = context;
        this.dataList = data;
    }


    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        return new BookingViewHolder(layoutInflater.inflate(R.layout.booking_template_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        holder.jenis.setText(dataList.get(position).getJenis());
        holder.checkIn.setText(dataList.get(position).getTglCheckIn());
        holder.nomor.setText(String.valueOf(dataList.get(position).getNomor()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Booking booking = dataList.get(position);

                Intent i = new Intent(context, DetailBookingActivity.class);
                i.putExtra("id", booking.getId());
                i.putExtra("jenis", booking.getJenis());
                i.putExtra("nomor", booking.getNomor());
                i.putExtra("tglBooking", booking.getTglBooking());
                i.putExtra("tglCheckIn", booking.getTglCheckIn());
                i.putExtra("tglCheckOut", booking.getTglCheckOut());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView jenis, checkIn, nomor;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            jenis = itemView.findViewById(R.id.jenis);
            checkIn = itemView.findViewById(R.id.tgl_check_in);
            nomor = itemView.findViewById(R.id.nomor);
        }

    }
}
