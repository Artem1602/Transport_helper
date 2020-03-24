package ua.study.transporthelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.R;
import ua.study.transporthelper.settings.User_info;

public class Psg_login_activity extends AppCompatActivity implements View.OnClickListener {

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

    public boolean NumberCheck(String number){

        if(number.length()==13){
            if(number.toCharArray()[0]=='+'||number.toCharArray()[1]=='3'||number.toCharArray()[2]=='8'||number.toCharArray()[3]=='0'){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.set_place_btn:
                //Запись полей


                if(!NumberCheck(name_str.getText().toString()))
                {
                    Toast.makeText(this,"Invalid phone number",Toast.LENGTH_LONG).show();
                    break;
                }

                if(name_str.getText().toString().isEmpty() || number_str.getText().toString().isEmpty())
                {
                    Toast.makeText(this,"Заповніть всі поля",Toast.LENGTH_LONG).show();
                    break;
                }
                User_info.getInstance().setUser_name(name_str.getText().toString());
                User_info.getInstance().setUser_number(number_str.getText().toString());

                intent = new Intent(this, Psg_map_wait_activity.class);
                startActivity(intent);
                break;

        }
    }
}