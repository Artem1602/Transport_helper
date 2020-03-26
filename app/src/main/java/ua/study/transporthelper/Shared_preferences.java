package ua.study.transporthelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

import ua.study.transporthelper.activity.Login_activity;
import ua.study.transporthelper.activity.Passanger.Psg_wait_activity;

public class Shared_preferences extends Activity {
    public static final String MY_SETTINGS = "settings";
    public static final String REGISTER_KEY = "hasRegister";
    public static final String PHONE_NUMBER_KEY = "user_number";
    public static final String NAME_KEY = "user_name";
    public static final String ADDRESS_KEY = "user_address";


    private boolean has_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        has_register = sp.getBoolean(REGISTER_KEY, false);
        Intent intent;
        if(!has_register)
        {
            intent = new Intent(this, Login_activity.class);
            startActivity(intent);
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean(REGISTER_KEY, true);
            e.commit();
        }else {
            intent = new Intent(this, Psg_wait_activity.class);
            intent.putExtra(REGISTER_KEY,true);
            intent.putExtra(NAME_KEY,sp.getString(NAME_KEY,"ERROR"));
            intent.putExtra(PHONE_NUMBER_KEY,sp.getString(PHONE_NUMBER_KEY,"ERROR"));
            intent.putExtra(ADDRESS_KEY,sp.getString(ADDRESS_KEY,"ERROR"));
            startActivity(intent);
        }

    }
}
