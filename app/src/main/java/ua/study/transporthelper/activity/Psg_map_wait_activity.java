package ua.study.transporthelper.activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import ua.study.transporthelper.settings.Test_settings;
import ua.study.transporthelper.settings.User_info;

public class Psg_map_wait_activity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Button confirm_btn;
    private LatLng user_location;


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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(this);

        UiSettings settings = mMap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setCompassEnabled(true);

        // Add a marker in Sydney and move the camera

        LatLng cher = new LatLng(Test_settings.LATITUDE, Test_settings.LONGITUDE);
        set_marker(cher);

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.confirm_btn:
                User_info.getInstance().setUser_location(user_location);


                //TODO Написать реализацию... отправка на сервер
                break;
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Log.d("SSSPPP",latLng.latitude + "  " + latLng.longitude);
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

        mMap.addMarker(new MarkerOptions().position(marker_position));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(marker_position.latitude,marker_position.longitude)));
    }
}
