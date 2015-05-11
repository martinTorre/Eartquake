package com.martin.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.martin.R;
import com.martin.util.Constants;

public class MapActivity extends FragmentActivity{
	
	   private GoogleMap mMap; // Might be null if Google Play services APK is not available.
	   private FragmentManager myFragmentManager;
	   private double lat;
	   private double lng;
	
	   
	   @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.activity_maps);

	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setBackgroundDrawable(new ColorDrawable(0));
	        setContentView(R.layout.activity_map);

	        myFragmentManager = getSupportFragmentManager();

	        lat = getIntent().getDoubleExtra(Constants.INTENT_EXTRA_LATITUD, 0.0);
	        lng = getIntent().getDoubleExtra(Constants.INTENT_EXTRA_LONGITUDE, 0.0);
	        
	        setUpMapIfNeeded();
	   }
	    private void setUpMapIfNeeded() {
	        if (mMap == null) {
	            SupportMapFragment mySupportMapFragment = (SupportMapFragment)myFragmentManager.findFragmentById(R.id.map);
	            mMap = mySupportMapFragment.getMap();
	            // Check if we were successful in obtaining the map.
	            if (mMap != null) {
	                setUpMap();
	            }
	        }
	    }

	    private void setUpMap() {

	    	mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("Marker"));
	        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 7.0f));
	        //mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(30,10) , 14.0f) );
	    }
   }
