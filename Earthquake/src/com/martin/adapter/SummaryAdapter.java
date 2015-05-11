package com.martin.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.martin.R;
import com.martin.model.Features;
import com.martin.model.Properties;
import com.martin.ui.DetailActivity;
import com.martin.util.Constants;

public class SummaryAdapter extends BaseAdapter{

	private Context context;
	List<Features> listFeatures;
	private TextView txtMagnitude;
	private TextView txtPlace;
	private LinearLayout item;
	
    public SummaryAdapter(Context context, List<Features> listFeatures) {
        this.context = context;
        this.listFeatures = listFeatures;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.listFeatures.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.listFeatures.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int pos, View rowView, ViewGroup parent) {
		// TODO Auto-generated method stub

        if (rowView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_summary_item, parent, false);      
        }
 
        // Set data into the view.
        
        final Features feature = listFeatures.get(pos);
        final Properties properties = feature.getProperties();
        
        txtMagnitude = (TextView) rowView.findViewById(R.id.txtMagnitude);
        txtPlace = (TextView) rowView.findViewById(R.id.txtPlace);
        item = (LinearLayout) rowView.findViewById(R.id.item_summary_list);
        
        double mag = properties.getMag();
        
        txtMagnitude.setText(mag+"");
        txtPlace.setText(properties.getPlace());
        Log.i("","Entro: "+mag);
        if(mag<0.9){
        	item.setBackgroundColor(context.getResources().getColor(R.color.mag_1));
        }
        else if(mag<1.9){
        	item.setBackgroundColor(context.getResources().getColor(R.color.mag_2));
        }
        else if(mag<2.9){
        	item.setBackgroundColor(context.getResources().getColor(R.color.mag_3));
        }
		else if(mag<3.9){
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_4));
		}
		else if(mag<4.9){
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_5));
		}
		else if(mag<5.9){
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_6));
		}
		else if(mag<6.9){
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_7));
		}
		else if(mag<7.9){
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_8));
		}
		else if(mag<8.9){
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_9));
		}
		else{
			item.setBackgroundColor(context.getResources().getColor(R.color.mag_10));
		}
        
        item.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, DetailActivity.class);
				intent.putExtra(Constants.INTENT_EXTRA_FEATURE, feature);
				context.startActivity(intent);
			}
		});
        
        return rowView;
	}
	
}
