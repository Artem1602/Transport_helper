package ua.study.transporthelper.activity.Passanger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ua.study.transporthelper.R;
import ua.study.transporthelper.Shared_preferences;
import ua.study.transporthelper.settings.User_Firebase;
import ua.study.transporthelper.settings.User_info;

public class Psg_wait_activity extends AppCompatActivity implements View.OnClickListener {
    private TextView entered_name_str;
    private TextView entered_number_str;
    private TextView entered_addres_str;
    private Button find_car_btn;
    //Shared Preferences
    private static final String MY_SETTINGS = "settings";
    private static final String REGISTER_KEY = "hasRegister";
    private static final String PHONE_NUMBER_KEY = "phone_number";
    private static final String NAME_KEY = "phone_number";
    private static final String ADDRESS_KEY = "phone_number";
    private String user_number;
    //FIREBASE
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger_waiting_layout);

        entered_name_str = findViewById(R.id.entered_name_str);
        entered_number_str = findViewById(R.id.entered_number_str);
        entered_addres_str = findViewById(R.id.entered_adress_str);
        find_car_btn = findViewById(R.id.find_car_btn);
        find_car_btn.setOnClickListener(this);

        Bundle arguments = getIntent().getExtras();
        boolean is_register = false;
        if(arguments != null)
        {
            is_register = arguments.getBoolean(Shared_preferences.REGISTER_KEY);
            user_number = arguments.getString(Shared_preferences.PHONE_NUMBER_KEY);
            String user_name = arguments.getString(Shared_preferences.NAME_KEY);
            String user_address = arguments.getString(Shared_preferences.ADDRESS_KEY);

            entered_name_str.setText(user_name);
            entered_number_str.setText(user_number);
            entered_addres_str.setText(user_address);
        }else {
            entered_name_str.setText(User_info.getInstance().getUser_name());
            entered_number_str.setText(User_info.getInstance().getUser_number());
            String string = User_info.getInstance().getUser_address();
            entered_addres_str.setText(User_info.getInstance().getUser_address());
        }
        if(!is_register)
        {
            save_into_firebase();
            put_number_into_sharedPF();
        }
    }
    private void save_into_firebase()
    {
        //TODO Отправка на сервер данных с User_info
        User_Firebase user_firebase = new User_Firebase(User_info.getInstance().getUser_name(),
                User_info.getInstance().getUser_number(),User_info.getInstance().getUser_address(),
                User_info.getInstance().toStringParser());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child(user_firebase.getUser_number()).setValue(user_firebase);
    }

    private void put_number_into_sharedPF()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_preferences.MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(Shared_preferences.PHONE_NUMBER_KEY,User_info.getInstance().getUser_number());
        e.putString(Shared_preferences.NAME_KEY,User_info.getInstance().getUser_name());
        e.putString(Shared_preferences.ADDRESS_KEY,User_info.getInstance().getUser_address());
        e.commit();
    }

    @Override
    public void onClick(View v) {
        //TODO Реализация для кнопки "Вже поїхав"
    }
}
