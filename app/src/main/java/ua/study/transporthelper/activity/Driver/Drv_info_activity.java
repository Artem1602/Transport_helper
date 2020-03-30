package ua.study.transporthelper.activity.Driver;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.R;

//TODO Написать обьснение волонтеру по карте
public class Drv_info_activity extends AppCompatActivity {
    private Button info_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_info);
    }
}
