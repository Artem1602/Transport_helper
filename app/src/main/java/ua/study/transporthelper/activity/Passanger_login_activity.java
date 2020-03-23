package ua.study.transporthelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.Maps_fragment;
import ua.study.transporthelper.R;
import ua.study.transporthelper.settings.User_info;

public class Passanger_login_activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name_str;
    private EditText number_str;
    private Button set_place_btn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_login);

        name_str = findViewById(R.id.name_str);
        number_str = findViewById(R.id.number_str);

        set_place_btn = findViewById(R.id.set_place_btn);
        set_place_btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.set_place_btn:
                intent = new Intent(this, Maps_fragment.class);
                startActivity(intent);
                break;

        }
    }
}
