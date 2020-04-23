package ua.study.behome.activity.Partners;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ua.study.behome.R;

public class Partners extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imm_btn_nk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parters);
        imm_btn_nk = findViewById(R.id.NKorpus_img);
        imm_btn_nk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.NKorpus_img:
                Intent link_NK = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9-3OCc5g5oE"));
                startActivity(link_NK);
                break;
        }
    }
}
