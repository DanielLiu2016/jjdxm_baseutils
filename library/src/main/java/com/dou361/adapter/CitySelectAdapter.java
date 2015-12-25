package com.dou361.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dou361.bean.City;
import com.dou361.bean.CityBean;
import com.dou361.bean.County;
import com.dou361.bean.Province;
import com.dou361.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ========================================
 * <p/>
 * 版 权：dou361 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/11/11
 * <p/>
 * 描 述：省市区选择器的适配器
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class CitySelectAdapter extends
        BaseAdapter {

    /**
     * 省份列表
     */
    private List<Province> mProvinceLists;
    /**
     * 城市列表
     */
    private List<City> mCityLists;
    /**
     * 县或区列表
     */
    private List<County> mCountyLists;
    LayoutInflater inflater;
    private List<CityBean> cityBeanList = new ArrayList<CityBean>();

    public CitySelectAdapter(Context context, List<Province> mProvinceLists, List<City> mCityLists, List<County> mCountyLists) {
        this.mProvinceLists = mProvinceLists;
        this.mCityLists = mCityLists;
        this.mCountyLists = mCountyLists;
        initData(0);
        inflater = LayoutInflater.from(context);
    }

    /**
     * 刷新列表
     */
    public void updateData(int selectType) {
        initData(selectType);
        notifyDataSetChanged();
    }

    /**
     * selectType 0为显示省份列表，1为显示城市列表，2为显示县或区列表
     */
    private void initData(int selectType) {
        if (selectType == 0) {
            if (mProvinceLists != null && mProvinceLists.size() > 0) {
                cityBeanList.clear();
                for (int i = 0; i < mProvinceLists.size(); i++) {
                    CityBean mCityBean = new CityBean();
                    mCityBean.setcId(mProvinceLists.get(i).getProvinceId());
                    mCityBean.setcName(mProvinceLists.get(i).getProvinceName());
                    cityBeanList.add(mCityBean);
                }
            }
        } else if (selectType == 1) {
            if (mCityLists != null && mCityLists.size() > 0) {
                cityBeanList.clear();
                for (int i = 0; i < mCityLists.size(); i++) {
                    CityBean mCityBean = new CityBean();
                    mCityBean.setcId(mCityLists.get(i).getCityId());
                    mCityBean.setcName(mCityLists.get(i).getCityName());
                    cityBeanList.add(mCityBean);
                }
            }
        } else if (selectType == 2) {
            if (mCountyLists != null && mCountyLists.size() > 0) {
                cityBeanList.clear();
                for (int i = 0; i < mCountyLists.size(); i++) {
                    CityBean mCityBean = new CityBean();
                    mCityBean.setcId(mCountyLists.get(i).getCountyId());
                    mCityBean.setcName(mCountyLists.get(i).getCountyName());
                    cityBeanList.add(mCityBean);
                }
            }
        }

    }

    @Override
    public int getCount() {
        return cityBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(ResourceUtils.getResourceIdByName("layout", "city_item"), null);
        TextView tv_city = (TextView) v.findViewById(ResourceUtils.getResourceIdByName("id", "tv_city"));
        tv_city.setText(((CityBean) getItem(position)).getcName());
        return v;
    }
}
