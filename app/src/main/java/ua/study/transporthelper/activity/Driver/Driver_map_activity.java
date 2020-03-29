package ua.study.transporthelper.activity.Driver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ua.study.transporthelper.R;
import ua.study.transporthelper.activity.Login_activity;
import ua.study.transporthelper.settings.User_Firebase;

public class Driver_map_activity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_map_layout);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                get_user(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationClickListener(this);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_show,null);
                TextView name = v.findViewById(R.id.name_info);
                TextView number = v.findViewById(R.id.number_info);
                TextView info = v.findViewById(R.id.info_info);
                name.setText(marker.getTitle());
                number.setText(marker.getSnippet().split("/")[0]);
                info.setText(marker.getSnippet().split("/")[1]);
                return v;
            }
        });


        UiSettings settings = mMap.getUiSettings();
        settings.setMyLocationButtonEnabled(true);
        settings.setCompassEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(51.490898, 31.298577)));
    }

    private void get_user(DataSnapshot snapshot)
    {
        for(DataSnapshot ds : snapshot.getChildren())
        {
            User_Firebase user = ds.getValue(User_Firebase.class);
            set_marker(user.getUser_name(), user.getUser_address(), user.getUser_number(), user.toLatLngParser(user.getUser_location()));
        }
    }

    private  void set_marker(String name,String number ,String address, LatLng marker_position)
    {
        mMap.addMarker(new MarkerOptions().position(marker_position).title(name).snippet(number + "/" + address));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
//            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//            fab.setTooltipText("Send an email");
        }
    }
}
