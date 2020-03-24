package ua.study.transporthelper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.R;

public class Psg_wait_activity extends AppCompatActivity implements View.OnClickListener {
    private TextView entered_name_str;
    private TextView entered_number_str;
    private Button find_car_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_waiting_layout);

        entered_name_str = findViewById(R.id.entered_name_str);
        entered_number_str = findViewById(R.id.entered_number_str);

        find_car_btn = findViewById(R.id.find_car_btn);
        find_car_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //TODO Реализация для кнопки "Вже поїхав"
    }
}
