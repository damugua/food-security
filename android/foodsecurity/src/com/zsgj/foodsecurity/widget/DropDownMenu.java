package com.zsgj.foodsecurity.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zsgj.foodsecurity.R;


/**
 * 自定义下拉菜单Menu
 * @author tanxu
 *
 */
public class DropDownMenu implements OnItemClickListener {
	
	private Context mContext;
	private PopupWindow mPopupWindow;
	private List<Menu> mMenuList = new ArrayList<Menu>();
	private ListView mListView;
	private MenuAdapter mAdapter;
	private DisplayMetrics outMetrics;
	private OnDropDownMenuItemListener mOnDropDownMenuItemListener;
	private int mPopupWidth;
	private int  backgroundColor = Color.BLUE;
	private int color = Color.WHITE;
	private View contentView;
	
	public DropDownMenu(Context context) {
		mContext = context;
		initView();
	}
	
	public void addItem(String title,int imagId, int itemId) {
		if(mMenuList == null) mMenuList = new ArrayList<Menu>();
		mMenuList.add(new Menu(title,imagId, itemId));
	}
	
	public void removeItem(int itemId) {
		if(mMenuList == null) return;
		for(int i=0;i < mMenuList.size();i++) {
			if(mMenuList.get(i).itemId == itemId) mMenuList.remove(i);
		}
		mAdapter.notifyDataSetChanged(mMenuList);
	}
	
	public void removeAllItem() {
		if(mMenuList == null) return;
		mMenuList.clear();
		mMenuList = null;
	}
	
	public void setItem(String title, int itemId) {
		if(mMenuList == null) return;
		for(int i=0;i<mMenuList.size();i++) {
			if(mMenuList.get(i).itemId == itemId) mMenuList.get(i).itemTitle = title;
		}
		mAdapter.notifyDataSetChanged(mMenuList);
	}
	
	public void setOnDropDownMenuItemListener(OnDropDownMenuItemListener listener) {
		mOnDropDownMenuItemListener = listener;
	}

	private void initView() {
		contentView = LayoutInflater.from(mContext).inflate(R.layout.drop_down_menu, null);
		mListView = (ListView) contentView.findViewById(R.id.drop_down_menu_list);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mListView.setSelector(R.drawable.arrow_selector);
		mAdapter = new MenuAdapter(mContext, mMenuList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		
		mPopupWindow = new PopupWindow(mContext);
		mPopupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(android.R.color.transparent));
		mPopupWindow.setContentView(contentView);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setAnimationStyle(R.style.popWindowAnim);
	}

	
	public void dismiss() {
		mPopupWindow.dismiss();
	}
	
	public void closeMenu() {
		dismiss();
	}
	
	public void showMenuAsAnchor(View anchor) {
		mAdapter.notifyDataSetChanged(mMenuList);
		if(outMetrics == null) {
			outMetrics = mContext.getResources().getDisplayMetrics();
		}
		mPopupWindow.setHeight((int) (mMenuList.size() * outMetrics.density * 41 ));
		mPopupWindow.setWidth(mPopupWidth);
		contentView.setBackgroundColor(backgroundColor);
		mPopupWindow.showAsDropDown(anchor);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(mOnDropDownMenuItemListener != null) {
			mOnDropDownMenuItemListener.onDropDownMenuItemSelected(mMenuList.get(position));
		}
	}
	
	class MenuAdapter extends BaseAdapter {
		private Context mCxt;
		private List<Menu> mList;
		
		public MenuAdapter(Context mCxt, List<Menu> mList) {
			this.mCxt = mCxt;
			this.mList = mList;
		}
		
		public void notifyDataSetChanged(List<Menu> mList) {
			this.mList = mList;
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			if(mList == null) return 0;
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = LayoutInflater.from(mCxt).inflate(R.layout.drop_down_menu_item, null);
			}
			TextView textView = (TextView) convertView.findViewById(R.id.drop_down_menu_item_title);
			textView.setText(mList.get(position).itemTitle);
			textView.setTextColor(color);
			
			ImageView iv = ((ImageView) convertView.findViewById(R.id.drop_down_menu_item_imag));
			if(mList.get(position).itemImagId==-1) {
				iv.setVisibility(View.GONE);
			} else {
				iv.setImageResource(mList.get(position).itemImagId);
			}
			
			return convertView;
		}
		
	}
	
	public class Menu {
		public String itemTitle;
		public int itemImagId;
		public int itemId;
		
		public Menu(String title,int imagId, int itemId) {
			this.itemTitle = title;
			this.itemImagId = imagId;
			this.itemId = itemId;
		}
	}

	public interface OnDropDownMenuItemListener {
		public void onDropDownMenuItemSelected(Menu item);
	}

	public void setWidthwithAnchor(int width) {
		mPopupWidth = width;
	}

	public void setBackgroundColor(int color) {
		this.backgroundColor = color;
	}

	public void setTextColor(int color) {
		this.color=color;
	}
	
}
