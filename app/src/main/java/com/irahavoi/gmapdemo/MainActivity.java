package com.irahavoi.gmapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import static com.irahavoi.gmapdemo.Constants.ANIMATION_SLOW;
import static com.irahavoi.gmapdemo.Constants.BEARING_EAST;
import static com.irahavoi.gmapdemo.Constants.BEARING_NORTH;
import static com.irahavoi.gmapdemo.Constants.BEARING_WEST;
import static com.irahavoi.gmapdemo.Constants.NEW_YORK;
import static com.irahavoi.gmapdemo.Constants.TILT_ZERO;
import static com.irahavoi.gmapdemo.Constants.VITEBSK;
import static com.irahavoi.gmapdemo.Constants.WINNIPEG;
import static com.irahavoi.gmapdemo.Constants.ZOOM;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap m_map;
    boolean mapReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady) {
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });

        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });

        Button btnNewYork = (Button) findViewById(R.id.btnNewYork);
        btnNewYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCameraTo(NEW_YORK, ZOOM, BEARING_WEST, TILT_ZERO, ANIMATION_SLOW);
            }
        });

        Button btnVitebsk = (Button) findViewById(R.id.btnVitebsk);
        btnVitebsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCameraTo(VITEBSK, ZOOM, BEARING_EAST, TILT_ZERO, ANIMATION_SLOW);
            }
        });

        Button btnWinnipeg = (Button) findViewById(R.id.btnWinnipeg);
        btnWinnipeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCameraTo(WINNIPEG, ZOOM, BEARING_NORTH, TILT_ZERO, ANIMATION_SLOW);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       mapReady = true;
        m_map = googleMap;
        LatLng newYork = new LatLng(40.7884, -73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();

        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

    private void moveCameraTo(LatLng place, float zoom, float bearing, float tilt, int animationDurationMilis){
        CameraPosition cp = CameraPosition.builder()
                .target(place)
                .zoom(zoom)
                .bearing(bearing)
                .tilt(tilt)
                .build();

        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(cp), animationDurationMilis, null);
    }
}
