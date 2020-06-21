package com.example.covid_19;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class VerificationPhone extends AppCompatActivity {
EditText phoneno;
Button continue_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_phone);
        phoneno=findViewById(R.id.phone_number);
        continue_btn=findViewById(R.id.continue_btn);





        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneno.getText().toString();
                if(number.isEmpty() || number.length()<10||number.length()>10){
                    phoneno.setError("Valid number is required..");
                    phoneno.requestFocus();
                    return;
                }
                String phoneNumber = "+91"+number;
                Intent intent = new Intent(VerificationPhone.this,VerificationCode.class);
                intent.putExtra("phoneNumber",phoneNumber);
                startActivity(intent);
            }
        });




    }
   @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

            Intent intent = new Intent(VerificationPhone.this,Covid19Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
