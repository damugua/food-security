package com.zsgj.mobileinspect.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.bean.Kindergarten;

public class SchoolsAdapter extends BaseAdapter {
	private List<Kindergarten> data;
    private Context context;
//    private ViewHolder holder;
    
    public SchoolsAdapter(Context context, List<Kindergarten> data) {
        this.data = data;
        this.context = context;
    }
    
    /**
     * 处理listview数据变化
     */
    public void setList(List<Kindergarten> list) {
        this.data = list;
        notifyDataSetChanged();
    }
    public void addList(List<Kindergarten> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_school_list, null);
			viewHolder.tvSchoolName=(TextView) convertView.findViewById(R.id.tv_schoolname);
			viewHolder.tvSchoolAdress=(TextView) convertView.findViewById(R.id.tv_schooladdress);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		viewHolder.tvSchoolName.setText(data.get(position).getName());
		viewHolder.tvSchoolAdress.setText(data.get(position).getAddress());
		return convertView;
	}
	  private class ViewHolder {
	        public TextView tvSchoolName, tvSchoolAdress;
	    }

}
