package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.chatapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    EditText userid,email,pass,cpass;
    Button signup;
    private FirebaseAuth mAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Registering all the views
        userid=findViewById(R.id.userid);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        cpass=findViewById(R.id.cpass);
        signup=findViewById(R.id.signup_btn);

        Toolbar toolbar=findViewById(R.id.toolbar);
       try{ setSupportActionBar(toolbar);}
       catch (Exception e){
           System.out.println(e.getCause());
       }
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Firebase Authentication object
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extracting Text
                String struserid,stremail,strpass,strcpass;
                struserid=userid.getText().toString();
                stremail=email.getText().toString();
                strpass=pass.getText().toString();
                strcpass=cpass.getText().toString();



                if(TextUtils.isEmpty(struserid)){
                    userid.setError("Mandatory");
                }
                if(TextUtils.isEmpty(stremail)){
                    email.setError("Mandatory");
                }
                if(TextUtils.isEmpty(strpass)){
                    pass.setError("Mandatory");
                }
                if(TextUtils.isEmpty(strcpass)){
                    cpass.setError("Mandatory");
                }
                if (!(strpass.equals(strcpass))){
                    Toast.makeText(Signup.this, "Doesnt Matches", Toast.LENGTH_SHORT).show();
                }



                //Calling a Register function
                register(struserid,stremail,strpass);

            }
        });





    }
    private void register(final String userid, String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
                            User user1=new User(uid,userid,"default");
                            reference.setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Signup.this, login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }


                    }
                });

    }
}
