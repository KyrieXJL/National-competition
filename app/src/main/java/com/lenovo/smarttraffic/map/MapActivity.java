package com.lenovo.smarttraffic.map;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.lenovo.smarttraffic.R;

public class MapActivity extends AppCompatActivity {
    private MapView map;
    private ImageView location;
    private ImageView layer;
    private ImageView line;
    private ImageView marker;
    private AMap aMap;
    View infoview=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = (MapView) findViewById(R.id.map);
        location = (ImageView) findViewById(R.id.location);
        layer = (ImageView) findViewById(R.id.layer);
        line = (ImageView) findViewById(R.id.line);
        marker = (ImageView) findViewById(R.id.marker);
        aMap=map.getMap();
        map.onCreate(savedInstanceState);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng latLng=new LatLng(39.941853, 116.385307);
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("什刹海");
                markerOptions.snippet("什刹海");
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.marker_self )));
                markerOptions.draggable(false);
                aMap.addMarker(markerOptions);
                aMap.setInfoWindowAdapter(new AMap.ImageInfoWindowAdapter() {
                    @Override
                    public long getInfoWindowUpdateTime() {
                        return 0;
                    }

                    @Override
                    public View getInfoWindow(Marker marker) {
                        if (infoview==null){
                            infoview=View.inflate(MapActivity.this, R.layout.map_id_view, null);
                            return infoview;
                        }
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {
                        return null;
                    }
                });

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }
}
