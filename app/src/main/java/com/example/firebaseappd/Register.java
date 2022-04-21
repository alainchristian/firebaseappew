package com.example.firebaseappd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import firebaseappday.R;


public class Register extends AppCompatActivity {
    ImageButton btnClose;
    ImageView usrImage;
    Uri imageUri;
    EditText usrFirstName, usrLastName, usrPhone,
            usrCountry, usrProvince, usrDistrict,
            usrFullAddress, usrEmail, usrPassword, usrConfirmPW;
    Button btnRegister;
    private final static int GALLERY_REQUEST_CODE=100;
    Coms coms;
    FirebaseAuth auth;
    StorageReference stRef;
    DatabaseReference dbRef;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usrFirstName = findViewById(R.id.usrFirstName);
        usrLastName = findViewById(R.id.usrLastName);
        usrPhone = findViewById(R.id.usrPhone);
        usrCountry = findViewById(R.id.usrCountry);
        usrProvince = findViewById(R.id.usrProvince);
        usrDistrict = findViewById(R.id.usrDistrict);
        usrFullAddress = findViewById(R.id.usrFullAddress);
        usrEmail = findViewById(R.id.usrEmail);
        usrPassword = findViewById(R.id.usrPassword);
        usrConfirmPW = findViewById(R.id.usrConfirmPW);
        btnRegister = findViewById(R.id.btnRegister);
        btnClose = findViewById(R.id.btnClose);
        coms = new Coms(this);
        progressDialog = new ProgressDialog(this);

        progressDialog.setCanceledOnTouchOutside(false);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValidation();
            }
        });

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
        if(imageUri == null) {
            coms.message("Image","Image is required");
            return;
        }

        if(TextUtils.isEmpty(usrFirstName.getText().toString())){
            usrFirstName.setError("First name is require");
            usrFirstName.requestFocus();
        }

        if(TextUtils.isEmpty(usrLastName.getText().toString())){
            usrLastName.setError("First name is require");
            usrLastName.requestFocus();
        }

        if(TextUtils.isEmpty(usrPhone.getText().toString())){
            usrPhone.setError("First name is require");
            usrPhone.requestFocus();
        }

        if(TextUtils.isEmpty(usrCountry.getText().toString())){
            usrCountry.setError("Country is require");
            usrCountry.requestFocus();
        }

        if(TextUtils.isEmpty(usrProvince.getText().toString())){
            usrProvince.setError("province is require");
            usrProvince.requestFocus();
        }

        if(TextUtils.isEmpty(usrDistrict.getText().toString())){
            usrDistrict.setError("District is require");
            usrDistrict.requestFocus();
        }

        if(TextUtils.isEmpty(usrFullAddress.getText().toString())){
            usrFullAddress.setError("Full address is require");
            usrFullAddress.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(usrEmail.getText().toString()).matches()){
            usrEmail.setError("Invalid email");
            usrEmail.requestFocus();
            return;
        }

        if(usrPassword.length() < 6){
            usrPassword.setError("Password should be more 6 characters");
            usrPassword.requestFocus();
        }

        if (!usrPassword.getText().toString().equals(usrConfirmPW.getText().toString())){
            usrConfirmPW.setError("Password mismatch");
            usrConfirmPW.requestFocus();
        }

        createAccount();
    }

    private void createAccount() {
        progressDialog.setTitle("creating account");
        progressDialog.show();
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(usrEmail.getText().toString(),usrPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //
                        saveToFirebase();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        coms.message("Error in creating user", e.getMessage());
                    }
                });
    }

    private void saveToFirebase() {
        progressDialog.setTitle("Store image");
        progressDialog.show();
        String fileName = "clientImages/" + "user" + auth.getUid();
        stRef = FirebaseStorage.getInstance().getReference(fileName);
        stRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                        task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                sendAllData(uri);
                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        coms.message("Saving image error", e.getMessage());
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        coms.message("Saving image error", e.getMessage());
                    }
                });
    }

    private void sendAllData(Uri uri) {
        progressDialog.setTitle("Sending all data");
        progressDialog.show();
        dbRef = FirebaseDatabase.getInstance().getReference("ClientData");

        User user = new User(uri.toString(),
                usrFirstName.getText().toString(),
                usrLastName.getText().toString(),
                usrPhone.getText().toString(),
                usrCountry.getText().toString(),
                usrProvince.getText().toString(),
                usrDistrict.getText().toString(),
                usrFullAddress.getText().toString(),
                usrEmail.getText().toString());
//        String key = null;
//        if(auth.getUid() != null) {
//            key = auth.getUid();
//        }
        dbRef.child(auth.getUid()).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        startActivity(new Intent(Register.this, Login.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        coms.message("Database error", e.getMessage());
                    }
                });
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

