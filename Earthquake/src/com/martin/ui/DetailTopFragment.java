package com.martin.ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martin.R;
import com.martin.model.Features;
import com.martin.util.Constants;

public class DetailTopFragment extends Fragment{
	
	private TextView txtPlace;
	private TextView txtDepth;
	private TextView txtDate;
	private TextView txtMagnitude;
	private Features feature;
	
	@Override
	public View onCreateView (LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState){
		
		feature = (Features)getActivity().getIntent().getExtras().getSerializable(Constants.INTENT_EXTRA_FEATURE);
		
		return inflater.inflate(R.layout.fragment_info_top, container, false);
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onActivityCreated (Bundle state){
		super.onActivityCreated(state);
		
		Date date = new Date(feature.getProperties().getTime());
		Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
		
		txtPlace = (TextView) getView().findViewById(R.id.txtPlace);
		txtDepth = (TextView) getView().findViewById(R.id.txtDepth);
		txtDate = (TextView) getView().findViewById(R.id.txtDate);
		txtMagnitude = (TextView) getView().findViewById(R.id.txtMagnitude);
		
		txtPlace.setText(feature.getProperties().getPlace());
		txtDepth.setText(feature.getDetailProperties().getProperties().getProducts().getOrigin()[0].getProperties().getDepth());
		txtDate.setText(format.format(date));
		txtMagnitude.setText(feature.getProperties().getMag()+"");
		
	}
}
