package com.dou361.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Admin 2015-9-1 下午10:59:26 说明 网络图片的三级缓存，先从缓存中获取，本地获取，网络获取
 */
public class BitmapCache implements ImageCache {

    // private LruCache<String, Bitmap> mCache;
    private static BitmapFactory.Options mOptions = new BitmapFactory.Options();

    /**
     * 图片缓存最大数量
     */
    public static final int MAX_DRAWABLE_COUNT = 100;
    /**
     * 图片的KEY缓存
     */
    private static ConcurrentLinkedQueue<String> mKeyCache = new ConcurrentLinkedQueue<String>();
    /**
     * 图片的缓存
     */
    private static Map<String, Bitmap> mDrawableCache = new ConcurrentHashMap<String, Bitmap>();
    /**
     * 图片的总大小
     */
    private static long mTotalSize;

    static {
        mOptions.inDither = false;// 设置为false，将不考虑图片的抖动值，这会减少图片的内存占用
        mOptions.inPurgeable = true;// 设置为ture，表示允许系统在内存不足时，删除bitmap的数组。
        mOptions.inInputShareable = true;// 和inPurgeable配合使用，如果inPurgeable是false，那么该参数将被忽略，表示是否对bitmap的数组进行共享
    }

    public BitmapCache() {
        // int maxSize = 10 * 1024 * 1024;
        // mCache = new LruCache<String, Bitmap>(maxSize) {
        // @Override
        // protected int sizeOf(String key, Bitmap value) {
        // return value.getRowBytes() * value.getHeight();
        // }
        // };
    }

    @Override
    public Bitmap getBitmap(String url) {
        /** 先从缓存里面拿，如果没有再从本地去拿 */
        // Bitmap bitmap = mCache.get(url);
        Bitmap bitmap = mDrawableCache.get(url);
        if (bitmap == null) {
            bitmap = loadFromLocal(url);
        }
        return bitmap;
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        /** 联网取数据成功后，先添加到缓存中，再保存到本地 */
        // mCache.put(url, bitmap);
        mDrawableCache.put(url, bitmap);
        if (bitmap != null) {// 放到内存缓存队列中
            addDrawableToMemory(url, bitmap);
        }
        String path = FileUtils.getIconDir()
                + url.replace(url.substring(url.lastIndexOf(".")), "");
        FileUtils.writeImage(bitmap, path, true, url);
    }

    /**
     * 添加到内存
     */
    private static void addDrawableToMemory(String url, Bitmap Bitmap) {
        mKeyCache.remove(url);
        mDrawableCache.remove(url);
        // 如果大于等于100张，或者图片的总大小大于应用总内存的四分之一先删除前面的
        while (mKeyCache.size() >= MAX_DRAWABLE_COUNT
                || mTotalSize >= SystemUtils.getOneAppMaxMemory() / 4) {
            String firstUrl = mKeyCache.remove();
            android.graphics.Bitmap remove = mDrawableCache.remove(firstUrl);
            mTotalSize -= BitmapUtils.getBitmapSize(remove);
        }
        mKeyCache.add(url);// 添加
        mDrawableCache.put(url, Bitmap);
        mTotalSize += BitmapUtils.getBitmapSize(Bitmap);
    }

    /**
     * 从本地设备中加载
     */
    private Bitmap loadFromLocal(String url) {
        Bitmap bitmap = null;
        String path = FileUtils.getIconDir();
        FileInputStream fis = null;
        try {
            // 获取流
            fis = new FileInputStream(new File(path
                    + url.replace(url.substring(url.lastIndexOf(".")), "")));
            if (fis != null) {
                // BitmapFactory.decodeByteArray(data, offset, length)
                // BitmapFactory.decodeFile(pathName)
                // BitmapFactory.decodeStream(is)
                // 上面三个分析源码可知，他们都是在Java层创建byte数组，然后把数据传递给本地代码。
                // 下面这个是把文件描述符传递给本地代码，由本地代码去创建图片
                // 优点，由于是本地代码创建的，那么byte数组的内存占用不会算到应用内存中，并且一旦内存不足，将会把bitmap的数组回收掉，而bitmap不会被回收
                // 当显示的时候，发现bitmap的数组为空时，将会再次根据文件描述符去加载图片，此时可能由于加载耗时造成界面卡顿，但总比OOM要好得多。
                // 由于本地代码在创建图片时，没有对图片进行校验，所以如果文件不完整，或者根本就不是一个图片时，系统也不会报错，仍然会返回一个bitmap,但是这个bitmap是一个纯黑色的bitmap。
                // 所以我们在下载图片的时候，一定要先以一个临时文件下载，等下载完毕了，再对图片进行重命名。
                bitmap = BitmapFactory.decodeFileDescriptor(fis.getFD(), null,
                        mOptions);
            }
            if (bitmap != null) {// 放到内存缓存队列中
                // mCache.put(url, bitmap);
                mDrawableCache.put(url, bitmap);
            }
            if (bitmap != null) {// 放到内存缓存队列中
                addDrawableToMemory(url, bitmap);
            }
        } catch (OutOfMemoryError e) {
            mKeyCache.clear();
            mDrawableCache.clear();
        } catch (Exception e) {
            // LogUtils.log(e);
        } finally {
            FileUtils.close(fis);
        }
        return bitmap;
    }

}
