package ua.study.transporthelper.activity.Passanger;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ua.study.transporthelper.R;
import ua.study.transporthelper.settings.Test_settings;
import ua.study.transporthelper.settings.User_info;

public class Psg_map_activity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Button confirm_btn;
    private LatLng user_location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passanger_map_layout);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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

        // Add a marker in Sydney and move the camera

        LatLng cher = new LatLng(Test_settings.LATITUDE, Test_settings.LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(cher));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(cher.latitude,cher.longitude)));



//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.confirm_btn:
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

    @Override
    public void onMapClick(LatLng latLng) {
       //TODO Чтоб юзеру было понятно что нужно зажать чтоб поставить точку можно выводить Toast о том что нужно делать
    }

    private  void set_marker(LatLng marker_position)
    {
        mMap.clear();

        CameraUpdateFactory.zoomTo(10f); // 2 - 21
        mMap.addMarker(new MarkerOptions().position(marker_position));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(marker_position.latitude,marker_position.longitude)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
