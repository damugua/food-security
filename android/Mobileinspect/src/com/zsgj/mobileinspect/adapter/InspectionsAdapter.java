package com.zsgj.mobileinspect.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.bean.GeneralInspection;

public class InspectionsAdapter extends BaseAdapter {
	private List<GeneralInspection> data;
    private Context context;
    
    public InspectionsAdapter(Context context, List<GeneralInspection> data) {
        this.data = data;
        this.context = context;
    }
    
    /**
     * 处理listview数据变化
     */
    public void setList(List<GeneralInspection> list) {
        this.data = list;
        notifyDataSetChanged();
    }
    public void addList(List<GeneralInspection> list) {
    	this.data.addAll(list);
    	notifyDataSetChanged();
    }



	@Override
	public int getCount() {
		if(data==null){
			return 0;
		}
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_inspect_list, null);
			viewHolder.tvSchoolName=(TextView) convertView.findViewById(R.id.tv_schoolname);
			viewHolder.tvInspectPerson=(TextView) convertView.findViewById(R.id.tv_inspectPerson);
			viewHolder.tvInspectTime=(TextView) convertView.findViewById(R.id.tv_inspectTime);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.tvSchoolName.setText(data.get(position).getCateringUnit().getName());
		viewHolder.tvInspectPerson.setText(data.get(position).getSyjUser().getName());
		viewHolder.tvInspectTime.setText(data.get(position).getInspectTime());
		return convertView;
	}
	  private class ViewHolder {
	        public TextView tvSchoolName, tvInspectPerson,tvInspectTime;
	    }

}
