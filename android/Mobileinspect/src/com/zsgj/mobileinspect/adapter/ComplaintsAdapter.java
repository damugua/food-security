package com.zsgj.mobileinspect.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsgj.mobileinspect.R;
import com.zsgj.mobileinspect.bean.Complaint;

public class ComplaintsAdapter extends BaseAdapter {
	private List<Complaint> data;
	private Context context;

	// private ViewHolder holder;

	public ComplaintsAdapter(Context context) {
		this.context = context;
		this.data = new ArrayList<Complaint>();
	}

	/**
	 * 处理listview数据变化
	 */
	public void setList(List<Complaint> list) {
		this.data = list;
		notifyDataSetChanged();
	}
	public List<Complaint> getList(){
		return data;
	}

	public void addList(List<Complaint> list) {
		this.data.addAll(list);
		notifyDataSetChanged();
	}

	public void clearItem() {
		data.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
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
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_complain_list, null);
			viewHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.tv_title);
			viewHolder.tvName = (TextView) convertView
					.findViewById(R.id.tv_name);
			viewHolder.tvTime = (TextView) convertView
					.findViewById(R.id.tv_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvTitle.setText(data.get(position).getTitle());
		viewHolder.tvName.setText(data.get(position).getContent());
		viewHolder.tvTime.setText(data.get(position).getTime());
		return convertView;
	}

	private class ViewHolder {
		public TextView tvTitle, tvTime,tvName;
	}

}
