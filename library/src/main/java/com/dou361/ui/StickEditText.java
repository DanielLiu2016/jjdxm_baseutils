package com.dou361.ui;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dou361.note.view.ViewUtils;
import com.dou361.note.view.annotation.ViewInject;
import com.dou361.utils.UIUtils;
import com.jingwang.eluxue_online.R;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网电子科技有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015-9-29 下午8:30:16
 * <p/>
 * 描 述：编辑框，编辑文本使用
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class StickEditText extends PopupWindow {

	@ViewInject(R.id.et_content)
	private EditText et_content;
	@ViewInject(R.id.btn_cancel)
	private Button btn_cancel;
	private View mMenuView;
	private OnSendListener ff;
	private TextView tv;

	public void setOnSendListener(OnSendListener l, TextView view) {
		ff = l;
		this.tv = view;
		et_content.setText(tv.getText().toString());
	}

	public StickEditText(Activity context) {
		super(context);
		mMenuView = UIUtils.inflate(R.layout.popuwindow_edittext);
		ViewUtils.inject(this, mMenuView);
		// 取消按钮
		btn_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 销毁弹出框
				dismiss();
			}
		});
		et_content
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_SEND||actionId == EditorInfo.IME_ACTION_DONE
								|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
								) {
							ff.updateData(et_content.getText().toString()
									.trim(), tv);
							return true;
						}
						return false;
					}

				});
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		// this.setAnimationStyle(R.style.AnimBottom);
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

	public interface OnSendListener {
		void updateData(String trim, View v);
	}

}
