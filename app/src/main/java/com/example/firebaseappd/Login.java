package com.example.firebaseappd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import firebaseappday.R;


public class Login extends AppCompatActivity {
    TextView noAccountTV;
    EditText emailEt,passwordEt;
    Button loginBtn;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    Coms coms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        coms=new Coms(this);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Loggin in....");
        progressDialog.setCanceledOnTouchOutside(false);

        emailEt=findViewById(R.id.emailEt);
        passwordEt=findViewById(R.id.passwordEt);
        loginBtn=findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        noAccountTV=findViewById(R.id.noAccountTV);
        noAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
                finish();
            }
        });

    }

    private void loginUser() {
        String email=emailEt.getText().toString();
        String pass=passwordEt.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEt.setError("Invalid Email");
            emailEt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass)){
            passwordEt.setError("Password is required");
            passwordEt.requestFocus();
            return;
        }
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //what to do if login is successfull
                        progressDialog.dismiss();
                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //show dialogbox show error reason
                        progressDialog.dismiss();
                        coms.message("Error",e.getMessage());
                    }
                });


    }


}