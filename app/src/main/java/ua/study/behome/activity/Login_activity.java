package ua.study.behome.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ua.study.behome.R;
import ua.study.behome.activity.Driver.Driver_map_activity;
import ua.study.behome.activity.Passanger.Psg_login_activity;
import ua.study.behome.activity.Volunteer.Volunteer_login;
import ua.study.behome.activity.Volunteer.Volunteer_map_activity;

public class Login_activity extends AppCompatActivity implements View.OnClickListener{

    private Button passenger_btn;
    private Button volunteer_btn;
    private  Button driver_btn;
    private  Button people_btn;
    private Button info_btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        passenger_btn = findViewById(R.id.passenger_btn);
        passenger_btn.setOnClickListener(this);

        driver_btn = findViewById(R.id.driver_btn);
        driver_btn.setOnClickListener(this);

        people_btn = findViewById(R.id.people_btn);
        people_btn.setOnClickListener(this);

        info_btn_login = findViewById(R.id.login_info_btn);
        info_btn_login.setOnClickListener(this);

        volunteer_btn = findViewById(R.id.volenteer_btn);
        volunteer_btn.setOnClickListener(this);

        checkPermission();
    }

    private void checkPermission() {

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Login_activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.gps_error)
                .setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Login_activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }
                }).create();

        switch (v.getId())
        {
            case R.id.driver_btn:
                if(ContextCompat.checkSelfPermission(Login_activity.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    alertDialog.show();
                    break;
                }
                intent = new Intent(this, Driver_map_activity.class);
                startActivity(intent);
                break;
            case R.id.passenger_btn:
                intent = new Intent(this, Psg_login_activity.class);
                startActivity(intent);
                break;
            case R.id.people_btn:
                intent = new Intent(this, Volunteer_login.class);
                startActivity(intent);
                break;
            case R.id.login_info_btn:
                intent = new Intent(this, Info_activity.class);
                startActivity(intent);
                break;
            case R.id.volenteer_btn:
                if(ContextCompat.checkSelfPermission(Login_activity.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    alertDialog.show();
                    break;
                }
                intent = new Intent(this, Volunteer_map_activity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onBackPressed() {}
}
