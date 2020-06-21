package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.covid_19.Fragment.DashboardFragment;
import com.example.covid_19.Fragment.StateFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Covid19Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    AlertDialog.Builder name_dialog;
    RelativeLayout container_fragment;
    public static  String username;
    TextView textView_user;
    FirebaseAuth firebaseAuth;
    Button logout;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid19_dashboard);

        loadFragment(new DashboardFragment());
        initView();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initView() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        container_fragment=findViewById(R.id.container_fragment);
        textView_user=findViewById(R.id.username);
        logout=findViewById(R.id.logout);
        toolbar=findViewById(R.id.toolbar);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Covid19Activity.this,VerificationPhone.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        Activity activity = null;

        switch (item.getItemId()) {
            case R.id.dashboard:
                fragment = new DashboardFragment();
                break;

            case R.id.state:

                fragment = new StateFragment();
                break;

        }
        return loadFragment(fragment);
    }

    public void talk_to_us(View view) {

    }
}
