package com.zsgj.foodsecurity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsgj.foodsecurity.R;
import com.zsgj.foodsecurity.bean.Notice;

public class NoticesAdapter extends BaseAdapter {
	private List<Notice> data;
	private Context context;

	// private ViewHolder holder;

	public NoticesAdapter(Context context) {
		this.context = context;
		this.data = new ArrayList<Notice>();
	}

	/**
	 * 处理listview数据变化
	 */
	public void setList(List<Notice> list) {
		this.data = list;
		notifyDataSetChanged();
	}

	public void addList(List<Notice> list) {
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
					R.layout.item_school_list, null);
			viewHolder.tvNoticeTitle = (TextView) convertView
					.findViewById(R.id.tv_schoolname);
			viewHolder.tvNoticeTime = (TextView) convertView
					.findViewById(R.id.tv_schooladdress);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvNoticeTitle.setText(data.get(position).getTitle());
		viewHolder.tvNoticeTime.setText(data.get(position).getTime());
		return convertView;
	}

	private class ViewHolder {
		public TextView tvNoticeTitle, tvNoticeTime;
	}

}
