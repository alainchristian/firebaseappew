package com.example.firebaseappd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import firebaseappday.R;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Product> productList;
    ProductRVAdapter rvAdapter;
    FirebaseAuth mAuth;
    FirebaseUser fbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        fbUser=mAuth.getCurrentUser();
        recyclerView=findViewById(R.id.myRV);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        productList =new ArrayList<>();
        productList=fillTheList();
        rvAdapter = new ProductRVAdapter(productList,this);
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        MenuItem mnLogin = menu.findItem(R.id.mnuLogin);
        MenuItem mnLogout = menu.findItem(R.id.mnuLogout);
        //write logic: if some one logs in, login menu disapears and we show logout
        if (fbUser!=null){
            //hide login show logout
            mnLogin.setVisible(false);
            mnLogout.setVisible(true);
        }
        else{
            //hide logout show login
            mnLogin.setVisible(true);
            mnLogout.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuLogin:
               startActivity(new Intent(MainActivity.this,Login.class));
                break;
            case R.id.mnuLogout:
                //logout
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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