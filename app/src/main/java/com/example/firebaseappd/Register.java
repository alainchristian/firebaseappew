package com.example.firebaseappd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import firebaseappday.R;

public class Register extends AppCompatActivity {
    ImageButton btnClose;
    ImageView usrImage;
    Uri imageUri;
    EditText usrFirstName,usrLastName,usrPhone,
            usrCountry,usrProvince,usrDistrict,
            usrFullAddress,usrEmail,usrPassword,usrConfirmPW;
    Button btnRegister;
    private final static int GALLERY_REQUEST_CODE=100;
    Coms coms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usrFirstName=findViewById(R.id.usrFirstName);
        usrLastName=findViewById(R.id.usrLastName);
        usrPhone=findViewById(R.id.usrPhone);
        usrCountry=findViewById(R.id.usrCountry);
        usrProvince=findViewById(R.id.usrProvince);
        usrDistrict=findViewById(R.id.usrDistrict);
        usrFullAddress=findViewById(R.id.usrFullAddress);
        usrEmail=findViewById(R.id.usrEmail);
        usrPassword=findViewById(R.id.usrPassword);
        usrConfirmPW=findViewById(R.id.usrConfirmPW);
        btnRegister=findViewById(R.id.btnRegister);
        coms =new Coms(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValidation();
            }
        });


        btnClose=findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(Register.this,MainActivity.class));
              finish();
            }
        });
        usrImage=findViewById(R.id.usrImage);
        usrImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });
    }

    private void formValidation() {
        if (imageUri==null){
            coms.message("Image","Image is require please select image...");
            return;
        }
        if (TextUtils.isEmpty(usrFirstName.getText().toString())){
            usrFirstName.setError("First is required");
            usrFirstName.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(usrLastName.getText().toString())){
            usrLastName.setError("Last is required");
            usrLastName.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(usrEmail.getText().toString()).matches()){
            usrEmail.setError("Invalid email");
            usrEmail.requestFocus();
            return;
        }
        if (!usrPassword.getText().toString().equals(usrConfirmPW.getText().toString())){
            usrConfirmPW.setError("Password mismatch");
            usrConfirmPW.requestFocus();
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERY_REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            imageUri=data.getData();
            usrImage.setImageURI(imageUri);
        }
    }
}