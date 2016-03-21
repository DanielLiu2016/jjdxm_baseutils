package com.dou361.utils;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;

/**
 * @author Admin 2015-9-1 下午11:33:37 说明 使用volley框架来加载网络图片
 */
public class ImageFactory {

    private ImageLoader imageLoader;

    public ImageFactory(Context context) {
        imageLoader = new ImageLoader(UtilsManager.getInstance().getRequestQueue(), new BitmapCache());
    }

    public void show(ImageView v, String url, int default1, int error) {
        if (url == null) {
            url = "";
        }
        ImageListener listener = ImageLoader.getImageListener(v,
                default1, error);
        imageLoader.get(url, listener);
    }


}
