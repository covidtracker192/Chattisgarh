package com.covid19.chattisgarh;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class hospitals extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng latLng;
    private FusedLocationProviderClient fusedLocationClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(180000);
        mLocationRequest.setFastestInterval(90000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        mMap.setMyLocationEnabled(true);

        // Test Center 1
        final LatLng center1 = new LatLng(21.465585, 81.725095);
        mMap.addMarker(new MarkerOptions().position(center1).title("Dept of Lab services, Balco Medical Centre (Vedanta Medical Research Foundation, Uparwara, Chhattisgarh ").snippet("Phone Number: 077122 37575"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center1));

        //Test Center 2
        final LatLng center2 = new LatLng(21.383768, 81.601194);
        mMap.addMarker(new MarkerOptions().position(center2).title("Regional Leprosy Training and Research Institute, Lalpur, Raipur (Chhattisgarh) under Govt. of India, Ministry of Health and Family Welfare, Raipur, Chhattisgarh ").snippet("Phone Number: 077124 12792"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center2));

        //Test Center 3
        final LatLng center3 = new LatLng(21.507050, 81.635580);
        mMap.addMarker(new MarkerOptions().position(center3).title("Pt. Jawahar Lal Nehru Memorial Medical College, Raipur, Chhattisgarh ").snippet("Phone Number: 077125 23919"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center3));

        //Test Center 4
        final LatLng center4 = new LatLng(21.465585, 81.644447);
        mMap.addMarker(new MarkerOptions().position(center4).title("MMI Narayana Multispeciality Hospital, Raipur, Chhattisgarh").snippet("Phone Number: 080675 06880"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center4));

        //Test Center 5
        final LatLng center5 = new LatLng(21.608755, 81.650920);
        mMap.addMarker(new MarkerOptions().position(center5).title("AIIMS RAIPUR").snippet("Phone Number: 077125 72240"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center5));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getPosition().equals(center1)) {
                    Uri uriUrl = Uri.parse("https://balcomedicalcentre.com/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center2)) {
                    Uri uriUrl = Uri.parse("http://rltrird.cg.gov.in/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }

                else if(marker.getPosition().equals(center3)) {
                    Uri uriUrl = Uri.parse("https://ptjnmcraipur.in/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center4)) {
                    Uri uriUrl = Uri.parse("https://www.narayanahealth.org/hospitals/raipur/mmi-narayana-multispeciality-hospital?utm_source=mmi-narayana-multispeciality-hospital&utm_medium=GMB_Hospital&utm_campaign=Organic");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
            }
        });

    }

    LocationCallback mLocationCallback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for(Location location1 : locationResult.getLocations()) {
                if (getApplicationContext() != null) {
                    mLastLocation = location1;
                    latLng = new LatLng(location1.getLatitude(), location1.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
                }
            }

        }
    };
    public void onBackPressed(){
        Intent intent = new Intent(hospitals.this, home.class);
        startActivity(intent);
        finish();
    };
}
