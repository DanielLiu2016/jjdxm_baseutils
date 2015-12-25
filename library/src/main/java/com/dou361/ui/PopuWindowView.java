package com.dou361.ui;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.dou361.adapter.PopuWindowAdapter;
import com.dou361.bean.Popu;
import com.dou361.utils.UIUtils;
import com.jingwang.eluxue_online.R;

import java.util.ArrayList;
import java.util.List;

public class PopuWindowView {

	View viewItem = null;
	ListView pupoListView;
	PopupWindow pullDownView;// 弹出窗口
	private List<Popu> popuLists = new ArrayList<Popu>();
	private PopuWindowAdapter mPopuWindowAdapter;
	private Handler mHandler;
	private TdataListener mTdataListener;
	private int maxLine = 5;
	private int what;

	public PopuWindowView(Handler mHandler, int what, int width) {
		this.mHandler = mHandler;
		this.what = what;
		viewItem = UIUtils.inflate(R.layout.popu_options);
		pupoListView = (ListView) viewItem.findViewById(R.id.list);
		mPopuWindowAdapter = new PopuWindowAdapter(mHandler, what, popuLists);
		pupoListView.setAdapter(mPopuWindowAdapter);
		pullDownView = new PopupWindow(viewItem, width,
				LayoutParams.WRAP_CONTENT, true);
		pullDownView.setOutsideTouchable(true);
		pullDownView.setBackgroundDrawable(new BitmapDrawable());
	}

	/** 设置下拉框的数据 */
	public void initPupoData(TdataListener tdataListener) {
		tdataListener.initPupoData(popuLists);
		if (popuLists != null && popuLists.size() > maxLine) {
			pullDownView.setHeight(UIUtils.dip2px(maxLine * 40));
		}
		mPopuWindowAdapter.notifyDataSetChanged();
	}

	/** 设置最大行popuWindow */
	public void setMaxLines(int maxLines) {
		maxLine = maxLines;
	}

	/** 显示popuWindow */
	public void showing(View v) {
		pullDownView.showAsDropDown(v, 0, -3);
	}

	/** 关闭popuWindow */
	public void dismiss() {
		pullDownView.dismiss();
	}

	/** 设置当前选择的位置，必须在初始化数据之前，并且设置的位置不能超出集合大小 */
	public void setSelectItem(int index) {
		Message msg = new Message();
		Bundle data = new Bundle();
		// 设置选中索引
		data.putInt("popuPosition", index);
		msg.setData(data);
		msg.what = what;
		// 发出消息
		mHandler.sendMessage(msg);
	}

	/** 动态设置下拉框的数据 */
	public interface TdataListener {
		/** 初始化数据 */
		void initPupoData(List<Popu> lists);
	}

	/** 获取选择的名称 */
	public String getTitle(int popuPosition) {
		return popuLists.get(popuPosition).getTitle();
	}

	/** 获取选择的id */
	public int getId(int popuPosition) {
		return popuLists.get(popuPosition).getId();
	}

	/** 获取选择的sid */
	public String getSid(int popuPosition) {
		return popuLists.get(popuPosition).getSid();
	}

}
