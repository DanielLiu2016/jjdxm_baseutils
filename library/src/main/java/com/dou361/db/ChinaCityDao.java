package com.dou361.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dou361.bean.City;
import com.dou361.bean.County;
import com.dou361.bean.Province;
import com.dou361.utils.LogUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网科技控股有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/11/17
 * <p/>
 * 描 述：省市区三级联动数据库查询工具
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class ChinaCityDao {

    private static String tag = ChinaCityDao.class.getSimpleName();

    /**
     * 保存地区信息
     */
    public static void queryCountyAndSave(DBOpenHelper mDBOpenHelper) {
        SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.rawQuery("select * from areas", null);
        while (cursor.moveToNext()) {
            County mCounty = new County();
            int countyId = cursor.getInt(cursor.getColumnIndex("areaid"));
            int cityId = cursor.getInt(cursor.getColumnIndex("cityid"));
            String countyName = cursor.getString(cursor
                    .getColumnIndex("area"));
            mCounty.setCountyId(countyId);
            mCounty.setCityId(cityId);
            if (countyName != null) {
                mCounty.setCountyName(countyName);
            }
            list.add(mCounty);
        }
        db.close();
        if (list.size() > 0) {
            DataSupport.saveAll(list);
            LogUtils.tag(tag).log("复制地区表信息成功！");
        }
    }

    /**
     * 保存城市信息
     */
    public static void queryCityAndSave(DBOpenHelper mDBOpenHelper) {
        SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.rawQuery("select * from cities", null);
        while (cursor.moveToNext()) {
            City mCity = new City();
            int provinceId = cursor.getInt(cursor.getColumnIndex("provinceid"));
            int cityId = cursor.getInt(cursor.getColumnIndex("cityid"));
            String cityName = cursor.getString(cursor.getColumnIndex("city"));
            mCity.setCityId(cityId);
            mCity.setProvinceId(provinceId);
            if (cityName != null) {
                mCity.setCityName(cityName);
            }
            list.add(mCity);
        }
        db.close();
        if (list.size() > 0) {
            DataSupport.saveAll(list);
            LogUtils.tag(tag).log("复制城市表信息成功！");
        }
    }

    /**
     * 保存省份信息
     */
    public static void queryProviceAndSave(DBOpenHelper mDBOpenHelper) {
        SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.rawQuery("select * from provinces", null);
        while (cursor.moveToNext()) {
            Province mProvince = new Province();
            int provinceId = cursor.getInt(cursor.getColumnIndex("provinceid"));
            String provinceName = cursor.getString(cursor
                    .getColumnIndex("province"));
            mProvince.setProvinceId(provinceId);
            if (provinceName != null) {
                mProvince.setProvinceName(provinceName);
            }
            list.add(mProvince);
        }
        db.close();
        if (list.size() > 0) {
            DataSupport.saveAll(list);
            LogUtils.tag(tag).log("复制省份表信息成功！");
        }
    }
}
