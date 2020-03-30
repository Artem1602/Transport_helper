package ua.study.transporthelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import ua.study.transporthelper.R;

public class Info_activity extends AppCompatActivity {

    private TextView developer_2;
    private TextView developer_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        developer_2 = findViewById(R.id.developer_2);
        developer_2.setMovementMethod(LinkMovementMethod.getInstance());
        developer_3 = findViewById(R.id.developer_3);
        developer_3.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
