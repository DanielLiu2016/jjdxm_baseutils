package com.dou361.third_login;

import com.dou361.utils.LogUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class QQUiListener implements IUiListener {

	private String tag = this.getClass().getSimpleName();

	@Override
	public void onComplete(Object o) {
		LogUtils.tag(tag).log("");
	}

	@Override
	public void onError(UiError e) {
		LogUtils.tag(tag).log(
				"code:" + e.errorCode + ", msg:" + e.errorMessage + ", detail:"
						+ e.errorDetail);
	}

	@Override
	public void onCancel() {
		LogUtils.tag(tag).log("onCancel");
	}
}