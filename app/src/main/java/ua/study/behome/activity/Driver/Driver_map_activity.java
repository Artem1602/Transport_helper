package ua.study.behome.activity.Driver;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ua.study.behome.R;
import ua.study.behome.settings.User_Firebase;

public class Driver_map_activity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnInfoWindowClickListener,
View.OnClickListener{

    private GoogleMap mMap;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Button info_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_map_layout);
        info_btn = findViewById(R.id.info_btn);
        info_btn.setOnClickListener(this);
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
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v;
                v = getLayoutInflater().inflate(R.layout.info_show_p,null);
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

        mMap.moveCamera(CameraUpdateFactory.zoomTo(6));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(50.4501, 30.5234)));
    }

    private void get_user(DataSnapshot snapshot)
    {
        for(DataSnapshot ds : snapshot.getChildren())
        {
            User_Firebase user = ds.getValue(User_Firebase.class);
            set_marker(user.getUser_name(), user.getUser_address(), user.getUser_number(), user.toLatLngParser(user.getUser_location()),user.isKey());
        }
    }


    private  void set_marker(String name,String number ,String address, LatLng marker_position, boolean is_people) {
        if (!is_people) {
            mMap.addMarker(new MarkerOptions().position(marker_position).title(name).snippet(number + "/" + address));
        }
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
    public boolean onMyLocationButtonClick() {
        LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        boolean gps_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.GPS_dont_turn_on).setPositiveButton(R.string.open_settings, new DialogInterface.OnClickListener() {
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
    public void onInfoWindowClick(Marker marker) {

        String number = "tel:" + marker.getSnippet().split("/")[1];
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(number));

        AlertDialog alertDialog = new AlertDialog.Builder(this).setMessage(R.string.do_you_want_to_call)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(intent);
                        }
                    }).setNegativeButton(R.string.no, null).create();
        alertDialog.show();
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,Drv_info_activity.class));
    }
}
