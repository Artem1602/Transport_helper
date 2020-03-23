package ua.study.transporthelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ua.study.transporthelper.R;

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

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.driver_btn:
                intent = new Intent(this, Psg_map_wait_activity.class);
                startActivity(intent);

                break;
            case R.id.passenger_btn:
                intent = new Intent(this, Psg_login_activity.class);
                startActivity(intent);
                break;
        }
    }
}
