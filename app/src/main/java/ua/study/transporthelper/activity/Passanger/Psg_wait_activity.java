package ua.study.transporthelper.activity.Passanger;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.R;
import ua.study.transporthelper.settings.User_info;

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

        if(User_info.getInstance().getUser_name().isEmpty() || User_info.getInstance().getUser_number().isEmpty())
        {
            entered_name_str.setText("Error");
            entered_number_str.setText("Error");
        }else {
            entered_name_str.setText(User_info.getInstance().getUser_name());
            entered_number_str.setText(User_info.getInstance().getUser_number());
        }



        //TODO Отправка на сервер данных с User_info
    }

    @Override
    public void onClick(View v) {
        //TODO Реализация для кнопки "Вже поїхав"
    }
}
