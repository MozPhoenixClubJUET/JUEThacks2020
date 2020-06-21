package com.example.covid_19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class talk_to_us extends AppCompatActivity {
    int symptoms_counter = 0;
    Boolean fever = false, tiredness = false, dry_cough = false;
    TextView symptom_q;
    CheckBox sym1, sym2, sym3;
    AlertDialog.Builder info_or_Alert;
    String Covid1, Covid2, Covid3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_to_us);
        symptom_q = findViewById(R.id.symptom_question);
        sym1 = findViewById(R.id.symptom1);
        Covid1 = "It's seem that you have very light risk of getting affected by Covid19\nWe recommend home quarentien of 3 days !\nDo you want to look for hospitals?";
        Covid2 = "It's seem that you are  affected by Covid19\nWe recommend home quarentien of 7 days !\nDo you want to look for hospitals?";
        Covid3 = "It's seem that you have very high risk of getting affected by Covid19\nWe recommend home quarentien of 14 days !\nDo you want to look for hospitals?";
        sym2 = findViewById(R.id.symptom2);
        sym3 = findViewById(R.id.symptom3);
        info_or_Alert = new AlertDialog.Builder(this);

    }

    public void setInfo_or_Alert(String msg) {
        sym1.setChecked(false);
        sym2.setChecked(false);
        sym3.setChecked(false);


        //Setting message manually and performing action on button click
        info_or_Alert.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Take me Hospital", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(mapIntent);
                        }
                    }
                })
                .setNegativeButton("Stay home", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button

                        dialog.cancel();
                        finish();
                        Toast.makeText(getApplicationContext(), "Stay Home ,Stay Safe ",
                                Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alert = info_or_Alert.create();
        //Setting the title manually
        alert.setTitle("To-Do");
        alert.show();
    }


    public void suggest_btn(View view) {

        if (sym1.isChecked() && sym2.isChecked() && sym3.isChecked()) {
            setInfo_or_Alert(Covid3);


        } else {




            if (sym2.isChecked() && sym1.isChecked()) {
                setInfo_or_Alert(Covid2);

            }
            if (sym2.isChecked() && sym3.isChecked()) {
                setInfo_or_Alert(Covid2);

            }
            if (sym3.isChecked() && sym1.isChecked()) {
                setInfo_or_Alert(Covid2);

            }

            if (sym1.isChecked()) {
                setInfo_or_Alert(Covid1);

            } else if (sym2.isChecked()) {
                setInfo_or_Alert(Covid1);

            } else if (sym3.isChecked()) {
                setInfo_or_Alert(Covid1);
            }
        }


    }
}
