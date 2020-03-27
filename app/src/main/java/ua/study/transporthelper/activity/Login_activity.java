package ua.study.transporthelper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ua.study.transporthelper.R;
import ua.study.transporthelper.activity.Driver.Driver_map_activity;
import ua.study.transporthelper.activity.Passanger.Psg_login_activity;

public class Login_activity extends AppCompatActivity implements View.OnClickListener{

    private Button passenger_btn;
    private  Button driver_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        passenger_btn = findViewById(R.id.passenger_btn);
        passenger_btn.setOnClickListener(this);

        driver_btn = findViewById(R.id.driver_btn);
        driver_btn.setOnClickListener(this);

        checkPermission();

    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Login_activity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1 );
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Login_activity.this,new String[]{Manifest.permission.INTERNET},2 );
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Login_activity.this,new String[]{Manifest.permission.ACCESS_NETWORK_STATE},3 );
        }

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.driver_btn:
                intent = new Intent(this, Driver_map_activity.class);
                startActivity(intent);

                break;
            case R.id.passenger_btn:
                intent = new Intent(this, Psg_login_activity.class);
                startActivity(intent);
                break;
        }
    }
}
