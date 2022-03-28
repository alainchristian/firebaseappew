package com.example.firebaseappd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.firebaseappday.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Product> productList;
    ProductRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.myRV);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        productList =new ArrayList<>();
        productList=fillTheList();
        rvAdapter = new ProductRVAdapter(productList,this);
        recyclerView.setAdapter(rvAdapter);
    }

    private List<Product> fillTheList() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Apple",R.drawable.apple,340,"red and green apple from sout africa keep it in dry and cool place"));
        productList.add(new Product("Banana",R.drawable.banana,120,"available as row or as ready to consume"));
        productList.add(new Product("Cherry",R.drawable.cherry,200,"red chery at very low price"));
        productList.add(new Product("Grape",R.drawable.grape,300,"available as row or as ready to consume"));
        productList.add(new Product("Mango",R.drawable.mango,100,"available as row or as ready to consume"));
        productList.add(new Product("Orange",R.drawable.orange,200,"available as row or as ready to consume"));
        productList.add(new Product("Pear",R.drawable.pear,300,"available as row or as ready to consume"));
        productList.add(new Product("Strawberry",R.drawable.strawberry,200,"available as row or as ready to consume"));
        productList.add(new Product("Watermelon",R.drawable.watermelon,300,"available as row or as ready to consume"));
        return productList;
    }
}