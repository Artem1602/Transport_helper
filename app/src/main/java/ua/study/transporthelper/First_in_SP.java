package ua.study.transporthelper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class First_in_SP extends Activity {
    public static final String MY_SETTINGS = "settings";
    public static final String FIRST_VISIT_KEY = "hasVisited";
    private boolean has_visited;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        SharedPreferences sp = getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        //First time?
        has_visited = sp.getBoolean(FIRST_VISIT_KEY, false);

        if(!has_visited)
        {
            //TODO Вывод нужной активити
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean(FIRST_VISIT_KEY, true);
            e.commit();
        }
    }
}
