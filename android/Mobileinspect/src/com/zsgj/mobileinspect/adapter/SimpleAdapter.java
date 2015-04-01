package com.zsgj.mobileinspect.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.bean.InspectResultType;
import com.zsgj.mobileinspect.widget.PinnedSectionListView.PinnedSectionListAdapter;

public class SimpleAdapter extends BaseAdapter implements PinnedSectionListAdapter{
	private Context context;
	List<InspectResultType> inspectResultTypes;
	public SimpleAdapter(Context context, List<InspectResultType> inspectResultTypes) {
		this.context=context;
		this.inspectResultTypes=inspectResultTypes;
	}

	@Override
	public int getCount() {
		return 6;
	}

	@Override
	public InspectResultType getItem(int position) {
		return inspectResultTypes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.simplerow, null);
			viewHolder.tvCheckItem=(TextView) convertView.findViewById(R.id.rowTextView);
			viewHolder.checkBox=(CheckBox) convertView.findViewById(R.id.CheckBox01);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		if(isItemViewTypePinned(getItemViewType(position))){
			viewHolder.checkBox.setVisibility(View.GONE);
		}else{
			viewHolder.checkBox.setVisibility(View.VISIBLE);
		}
		viewHolder.tvCheckItem.setText(inspectResultTypes.get(position).getName());
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return (int) inspectResultTypes.get(position).getParentId();
	}

	@Override
	public boolean isItemViewTypePinned(int viewType) {
		return  viewType == 0;
	}
	public class ViewHolder{
		public TextView tvCheckItem;
		public CheckBox checkBox;
	}

}
