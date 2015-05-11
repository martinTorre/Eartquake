package com.martin.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.martin.R;
import com.martin.adapter.SummaryAdapter;
import com.martin.model.DetailProperties;
import com.martin.model.Features;
import com.martin.model.Summary;
import com.martin.util.Constants;
import com.martin.util.HttpHelper;

@SuppressWarnings("deprecation")
public class SummaryActivity extends ActionBarActivity{

	private ListView listviewSummary;
	private Context context;
	private List<Features> listFeatures;
	private HttpHelper httpHelper;
	private ActionBar actionBar;
	private Summary summary;
	private ProgressBar progressBar;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_summary);
		
		initializeUI();
		initializeComponents();
	}

	private void initializeComponents() {
		// TODO Auto-generated method stub
		
		context = this;
		httpHelper = new HttpHelper();
		listFeatures = new ArrayList<Features>();
		poblateList();
	}

	private void poblateList() {
		// TODO Auto-generated method stub
		try {
			listFeatures.clear();
			
			if(HttpHelper.isOnline(context)){
				summary = new ConsumeSummaryTask().execute().get();
				
				for(int i=0; i<summary.getFeatures().length; i++){
					Features feature = summary.getFeatures()[i];
					feature.setDetailProperties(new ConsumeSummaryDetailTask(
							feature.getProperties().getDetail()).execute().get());
					listFeatures.add(feature);
				}
			}
			else{
				
			}
			
			setActionBarProperties();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listviewSummary.setAdapter(null);
		SummaryAdapter summaryAdapter = new SummaryAdapter(context,listFeatures);
		listviewSummary.setAdapter(summaryAdapter);
		summaryAdapter.notifyDataSetChanged();
		
		setActionBarProperties();
	}

	@SuppressLint("InflateParams")
	private void setActionBarProperties() {
		// TODO Auto-generated method stub
		actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View view = layoutInflater.inflate(R.layout.layout_custom_action_bar, null);
		TextView txtTitle = (TextView)view.findViewById(R.id.txtTitle);
		Button btnRefresh = (Button)view.findViewById(R.id.btnRefresh);
		
		if(summary!=null){
			txtTitle.setText(summary.getMetadata().getTitle());
			btnRefresh.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					poblateList();
				}
			});
		}
		
		txtTitle.setSelected(true);
		actionBar.setCustomView(view);
		actionBar.setDisplayShowCustomEnabled(true);
	}

	private void initializeUI() {
		// TODO Auto-generated method stub
		progressBar = (ProgressBar) findViewById(R.id.progress);
		listviewSummary = (ListView) findViewById(R.id.listviewSummary);
	}
	
	private class ConsumeSummaryTask extends AsyncTask<Void, Void, Summary>{
	    
		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected Summary doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return httpHelper.getResponse(Summary.class, Constants.USGS_EARTHQUAKE_URL);
		}
		
		@Override
		protected void onPostExecute(Summary result) {
			progressBar.setVisibility(View.GONE);
		}
	}
	
	private class ConsumeSummaryDetailTask extends AsyncTask<Void, Void, DetailProperties>{
	    
		private String url;
		
		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
		}
		
		ConsumeSummaryDetailTask(String url){
			this.url = url;
		}
		
		@Override
		protected DetailProperties doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			return httpHelper.getResponse(DetailProperties.class, url);
		}
		
		@Override
		protected void onPostExecute(DetailProperties result) {
			progressBar.setVisibility(View.GONE);
		}
	}
}