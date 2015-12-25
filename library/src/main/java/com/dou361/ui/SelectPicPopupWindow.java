package com.dou361.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dou361.note.view.ViewUtils;
import com.dou361.note.view.annotation.ViewInject;
import com.dou361.utils.UIUtils;
import com.jingwang.eluxue_online.R;
/**
 * @author jjdxm 2015-4-8 上午11:14:13 说明 选择图片popuwindow，选择相册或者拍照
 */
public class SelectPicPopupWindow extends PopupWindow {

	@ViewInject(R.id.tv_take_photo)
	private TextView tv_take_photo;
	@ViewInject(R.id.tv_pick_photo)
	private TextView tv_pick_photo;
	@ViewInject(R.id.tv_cancel)
	private TextView tv_cancel;
	private View mMenuView;

	public SelectPicPopupWindow(Activity context, OnClickListener itemsOnClick, boolean showTakePhoto) {
		super(context);
		mMenuView = UIUtils.inflate(R.layout.popuwindow_photo);
		ViewUtils.inject(this, mMenuView);
		// 取消按钮
		tv_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 销毁弹出框
				dismiss();
			}
		});
		if(showTakePhoto) {
			tv_take_photo.setVisibility(View.VISIBLE);
		} else {
			tv_take_photo.setVisibility(View.GONE);
		}
		// 设置按钮监听
		tv_take_photo.setOnClickListener(itemsOnClick);
		tv_pick_photo.setOnClickListener(itemsOnClick);
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.FILL_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		//this.setAnimationStyle(R.style.AnimBottom);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

}
