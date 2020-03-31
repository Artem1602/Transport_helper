package ua.study.transporthelper.activity.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.R;

public class Drv_info_activity extends AppCompatActivity implements View.OnClickListener {
    private Button back_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_info);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
