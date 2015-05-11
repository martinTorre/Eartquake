package com.martin.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.martin.R;
import com.martin.model.Features;
import com.martin.util.Constants;

public class DetailBottomFragment extends Fragment{

	private Button btnShowMap;
	private Context context;
	private double latitud;
	private double longitude;
	private Features feature;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container,
			Bundle savedInstanceState){
		
		context = getActivity();
		feature = (Features)getActivity().getIntent().getExtras().getSerializable(Constants.INTENT_EXTRA_FEATURE);
		latitud = Double.parseDouble(feature.getDetailProperties().getProperties().getProducts().getOrigin()[0].getProperties().getLatitude());	
		longitude = Double.parseDouble(feature.getDetailProperties().getProperties().getProducts().getOrigin()[0].getProperties().getLongitude());
		
		return inflater.inflate(R.layout.fragment_info_bottom, container, false);		
	}
	
	@Override
	public void onActivityCreated (Bundle state){
		super.onActivityCreated(state);	
		
		btnShowMap = (Button)getView().findViewById(R.id.btnShowMap);
		btnShowMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showMap();
			}
		});
	}
	
	public void showMap(){
		Intent intent = new Intent(context, MapActivity.class);
		intent.putExtra(Constants.INTENT_EXTRA_LATITUD, latitud);
		intent.putExtra(Constants.INTENT_EXTRA_LONGITUDE, longitude);
		startActivity(intent);
	}
	
}
