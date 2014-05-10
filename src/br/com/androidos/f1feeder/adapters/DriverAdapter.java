package br.com.androidos.f1feeder.adapters;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.androidos.f1feeder.domain.Driver;
import br.com.androidos.f1feeder.R;


public class DriverAdapter extends BaseAdapter{

	private Context context;
	private List<Driver> drivers;

	public DriverAdapter(Context context, List<Driver> drivers) {
		this.context = context;
		this.drivers = drivers;
	}

	@Override
	public int getCount() {
		return drivers.size();
	}

	@Override
	public Object getItem(int position) {
		return drivers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return drivers.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Driver driver = drivers.get(position);
		Resources resources = context.getResources();
		
		HolderView holder = null;
		
		if(convertView == null){
			holder = new HolderView();
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.driver_details_list, null);
			
			holder.ivFlag = (ImageView) convertView.findViewById(R.id.ivFlag);
			
			holder.tvFullName = (TextView) convertView.findViewById(R.id.tvFullName);
			holder.tvPoints = (TextView) convertView.findViewById(R.id.tvPoints);
			holder.tvWins = (TextView) convertView.findViewById(R.id.tvWins);
			holder.tvScuderia = (TextView) convertView.findViewById(R.id.tvScuderia);
			
			convertView.setTag(holder);
			
		} else {
			holder = (HolderView) convertView.getTag();
			
		}
		
		holder.ivFlag.setImageDrawable(resources.getDrawable(resources.getIdentifier(driver.getFlagImage(), "drawable", context.getPackageName())));
		holder.tvFullName.setText(driver.getGivenName().concat(" ").concat(driver.getFamilyName()));
		holder.tvPoints.setText(String.valueOf(driver.getPoints()));
		holder.tvWins.setText(String.valueOf(driver.getWins()));
		holder.tvScuderia.setText(driver.getScuderia());
		
		return convertView;
	}
	
	static class HolderView{
		TextView tvFullName;
		TextView tvPoints;
		TextView tvWins;
		ImageView ivFlag;
		TextView tvScuderia;
		
	}

}