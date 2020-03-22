package ua.study.transporthelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Passanger_activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name_str;
    private EditText number_str;
    private Button set_place_btn;
    //TODO Зделать блокировку или скритие кнопки при первом входе
    private Button have_gone_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_activity);

        name_str = findViewById(R.id.name_str);
        number_str = findViewById(R.id.number_str);

        set_place_btn = findViewById(R.id.set_place_btn);
        set_place_btn.setOnClickListener(this);

        have_gone_btn = findViewById(R.id.have_gone_btn);
        have_gone_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.set_place_btn:

                break;
            case R.id.have_gone_btn:

                break;
        }
    }
}
