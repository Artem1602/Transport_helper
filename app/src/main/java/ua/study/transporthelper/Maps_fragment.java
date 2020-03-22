package ua.study.transporthelper;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ua.study.transporthelper.activity.Login_activity;
import ua.study.transporthelper.settings.Settings;

public class Maps_fragment extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    private Button confirm_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passanger_map_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.location_map);
        mapFragment.getMapAsync(this);

        confirm_btn = findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        LatLng cher = new LatLng(Settings.LATITUDE,Settings.LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(cher).title("You are gay"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Settings.LATITUDE,Settings.LONGITUDE)));

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.confirm_btn:
                //TODO Написать реализацию
                break;
        }
    }
}
