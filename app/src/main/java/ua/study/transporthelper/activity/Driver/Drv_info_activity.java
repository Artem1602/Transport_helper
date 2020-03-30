package ua.study.transporthelper.activity.Driver;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.transporthelper.R;

//TODO Написать обьснение волонтеру по карте
public class Drv_info_activity extends AppCompatActivity implements View.OnClickListener {
    private Button back_btn;
    private TextView blue_marker_tv;
    private TextView red_marker_tv;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_info);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);
//
//        blue_marker_tv = findViewById(R.id.blue_marker_tv);
//        blue_marker_tv.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
//        red_marker_tv = findViewById(R.id.red_marker_tv);
//        red_marker_tv.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, Driver_map_activity.class));
    }
}
