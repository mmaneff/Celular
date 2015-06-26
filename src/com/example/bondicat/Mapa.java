package com.example.bondicat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.view.Menu;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;


public class Mapa extends FragmentActivity {

    private GoogleMap mMap; 
    private String razonSocial;
    private double latitud;
    private double longitud;
    private String domicilio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           
        setContentView(R.layout.mapa);
        
        Bundle bundle = this.getIntent().getExtras();
        razonSocial = bundle.getString("razonSocial");
        latitud = bundle.getDouble("latitud");
        longitud = bundle.getDouble("longitud");
        domicilio = bundle.getString("domicilio");
        
        setUpMapIfNeeded();
    }
  
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private void setUpMapIfNeeded() {
        if (mMap == null) {            
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    
private void setUpMap() {
	 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitud, longitud), 15));
     mMap.setMyLocationEnabled(true);
  mMap.addMarker(new MarkerOptions().position(new LatLng(latitud, longitud)).title(razonSocial).snippet(domicilio));
    }
}