package ua.study.transporthelper.activity.Passanger;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ua.study.transporthelper.R;
import ua.study.transporthelper.Shared_preferences;
import ua.study.transporthelper.settings.Test_settings;
import ua.study.transporthelper.settings.User_info;

public class Psg_map_activity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    private Button confirm_btn;
    private LatLng user_location;
    private int i_for_fast_click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passanger_map_layout);
        i_for_fast_click = 0;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.psg_location_map);
        mapFragment.getMapAsync(this);
        confirm_btn = findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(this);

        UiSettings settings = mMap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setCompassEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(51.490898, 31.298577)));
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.confirm_btn:
                if(user_location == null)
                {
                    Toast.makeText(this,"Ви не поставили маркер",Toast.LENGTH_LONG).show();
                    break;
                }
                User_info.getInstance().setUser_location(user_location);
                intent = new Intent(this, Psg_wait_activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        user_location = latLng;
        set_marker(latLng);
    }


    private  void set_marker(LatLng marker_position)
    {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(marker_position));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(marker_position.latitude,marker_position.longitude)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15)); // 2 - 21
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getAltitude())));
    }

//    @Override
//    protected void onDestroy() {
//        SharedPreferences sp = getSharedPreferences(Shared_preferences.MY_SETTINGS, Context.MODE_PRIVATE);
//
//
//        super.onDestroy();
//    }
}
