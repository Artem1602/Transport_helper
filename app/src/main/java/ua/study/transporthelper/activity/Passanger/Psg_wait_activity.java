package ua.study.transporthelper.activity.Passanger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ua.study.transporthelper.R;
import ua.study.transporthelper.Shared_preferences;
import ua.study.transporthelper.activity.Login_activity;
import ua.study.transporthelper.settings.User_Firebase;
import ua.study.transporthelper.settings.User_info;

public class Psg_wait_activity extends AppCompatActivity implements View.OnClickListener {

    private TextView entered_name_str;
    private TextView entered_number_str;
    private TextView entered_addres_str;
    private Button find_car_btn;
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

        //Getting data from Shared_preferences
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
            entered_addres_str.setText(User_info.getInstance().getUser_address());
            user_number = User_info.getInstance().getUser_number();
        }
        if(!is_register)
        {
            save_into_firebase();
        }
    }
    private void save_into_firebase()
    {
        User_Firebase user_firebase = new User_Firebase(User_info.getInstance().getUser_name(),
                User_info.getInstance().getUser_number(),User_info.getInstance().getUser_address(),
                User_info.getInstance().toStringParser());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.child(user_firebase.getUser_number()).setValue(user_firebase).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //If successful push data
                put_user_data_into_sharedPF();
            }
        });
    }

    private void put_user_data_into_sharedPF()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_preferences.MY_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(Shared_preferences.PHONE_NUMBER_KEY,User_info.getInstance().getUser_number());
        e.putString(Shared_preferences.NAME_KEY,User_info.getInstance().getUser_name());
        e.putString(Shared_preferences.ADDRESS_KEY,User_info.getInstance().getUser_address());
        e.putBoolean(Shared_preferences.REGISTER_KEY,true);
        e.commit();
    }

    @Override
    public void onClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.sure_to_delete)
                .setNegativeButton(R.string.no,null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete_user_from_FB();
                    }
                }).create();
        alertDialog.show();
    }

    private void delete_user_from_FB()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(user_number);
        myRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Psg_wait_activity.this,R.string.your_data_from_map,Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences(Shared_preferences.MY_SETTINGS, Context.MODE_PRIVATE);
                SharedPreferences.Editor e = sharedPreferences.edit();
                e.putBoolean(Shared_preferences.REGISTER_KEY,false);
                e.commit();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Psg_wait_activity.this,Login_activity.class);;
                        startActivity(intent);
                    }
                },2000);
            }
        });
    }
    @Override
    public void onBackPressed() {}
}
