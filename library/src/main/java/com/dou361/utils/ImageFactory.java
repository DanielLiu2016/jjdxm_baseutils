package com.dou361.utils;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.jingwang.eluxue_online.R;
import com.dou361.application.BaseApplication;

/**
 * @author Admin 2015-9-1 下午11:33:37 说明 使用volley框架来加载网络图片
 */
public class ImageFactory {

	private ImageLoader imageLoader;

	public ImageFactory(Context context) {
		imageLoader = new ImageLoader(BaseApplication.getApplication()
				.getRequestQueue(), new BitmapCache());
	}

	public void show(ImageView v, String url) {
		if (url == null) {
			url = "";
		}
		ImageListener listener = ImageLoader.getImageListener(v,
				R.mipmap.ic_default, R.mipmap.ic_error);
		imageLoader.get(url, listener);
	}

}
