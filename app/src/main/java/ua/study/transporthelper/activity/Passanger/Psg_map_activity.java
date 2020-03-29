package ua.study.transporthelper.activity.Passanger;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
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
import ua.study.transporthelper.settings.User_info;

public class Psg_map_activity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    private Button confirm_btn;
    private LatLng user_location;
    private int click_i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passanger_map_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.psg_location_map);
        mapFragment.getMapAsync(this);
        confirm_btn = findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(this);
        click_i = 0;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMapClickListener(this);

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
                    Toast.makeText(this,R.string.you_dont_set_marker,Toast.LENGTH_LONG).show();
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
    }

    @Override
    public boolean onMyLocationButtonClick() {
        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        boolean gps_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.GPS_dont_turn_on).setPositiveButton(R.string.open_settings,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton(R.string.ignore, null).create();
            alertDialog.show();
        }
        return false;
    }
    @Override
    public void onMapClick(LatLng latLng) {
        click_i++;
        if(click_i%2==0){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setMessage(R.string.put_marker)
                    .setPositiveButton(R.string.good, null).create();
            alertDialog.show();
        }
    }
}
