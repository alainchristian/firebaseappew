package com.example.firebaseappd;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import firebaseappday.R;


public class VH extends RecyclerView.ViewHolder {
    TextView tvName,tvPrice,tvCart;
    ImageView prodImage;
    public VH(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tvName);
        tvPrice=itemView.findViewById(R.id.tvPrice);
        prodImage=itemView.findViewById(R.id.prodImage);
        tvCart=itemView.findViewById(R.id.tvCart);
    }
}
