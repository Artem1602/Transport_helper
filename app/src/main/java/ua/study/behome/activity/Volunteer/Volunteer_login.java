package ua.study.behome.activity.Volunteer;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ua.study.behome.R;
import ua.study.behome.activity.Login_activity;
import ua.study.behome.activity.Passanger.Psg_map_activity;
import ua.study.behome.settings.User_info;

public class Volunteer_login extends AppCompatActivity implements View.OnClickListener  {
    private EditText name_str;
    private EditText number_str;
    private EditText address_str;
    private Button set_place_btn;
    private TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_login);

        name_str = findViewById(R.id.name_str);
        number_str = findViewById(R.id.number_str);
        number_str.setSelection(number_str.getText().length());
        address_str = findViewById(R.id.address_str);
        address_str.setHint("Чим вам допомогти?");
        text = findViewById(R.id.text_pass);
        text.setText("\t Після того, як ви заповнили всі поля інформації, натисніть на кнопку \"Продовжити\", вас буде переміщено на карту. Розмістіть точку там, де вам буде зручно очікувати волонтера.");
        set_place_btn = findViewById(R.id.set_place_btn);
        set_place_btn.setOnClickListener(this);
        number_str.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) number_str.setText("+380");
            }
        });

    }

    public boolean NumberCheck(String number){
        int e=0;
        if(number.length()==13){
            if(number.toCharArray()[0]=='+' && number.toCharArray()[1]=='3' && number.toCharArray()[2]=='8' && number.toCharArray()[3]=='0'){
                for(int i = 1;i<number.length();i++ ){
                    if (number.toCharArray()[i]=='1'||number.toCharArray()[i]=='0'||number.toCharArray()[i]=='2'||number.toCharArray()[i]=='3'
                            ||number.toCharArray()[i]=='4'||number.toCharArray()[i]=='5'||number.toCharArray()[i]=='6'||number.toCharArray()[i]=='7'||number.toCharArray()[i]=='8'||number.toCharArray()[i]=='9'){
                        e++;
                    }
                    if (e==12){
                        return true;
                    }
                }
            }else{
                return false;
            }
        }
        return false;
    }



    @Override
    public void onClick(View v) {
        Intent intent;
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.gps_error)
                .setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(Volunteer_login.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    }
                }).create();
        switch (v.getId())
        {
            case R.id.set_place_btn:
                if(ContextCompat.checkSelfPermission(Volunteer_login.this ,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    alertDialog.show();
                    break;
                }
                //Запись полей
                if(name_str.getText().toString().isEmpty() || number_str.getText().toString().isEmpty()||address_str.getText().toString().isEmpty())
                {
                    Toast.makeText(this,R.string.fill_all_fields,Toast.LENGTH_LONG).show();
                    break;
                }

                if(!NumberCheck(number_str.getText().toString()))
                {
                    Toast.makeText(this,R.string.wrong_phone_number,Toast.LENGTH_LONG).show();
                    break;
                }

                User_info.getInstance().setUser_name(name_str.getText().toString());
                User_info.getInstance().setUser_number(number_str.getText().toString());
                User_info.getInstance().setUser_address(address_str.getText().toString());
                User_info.getInstance().setKey(true);
                intent = new Intent(this, Psg_map_activity.class);
                startActivity(intent);
                break;

        }
    }
}
