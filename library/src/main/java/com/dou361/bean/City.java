package com.dou361.bean;

import java.io.Serializable;

import org.litepal.crud.DataSupport;
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
 * 创建日期：2015/11/25
 * <p/>
 * 描 述：城市
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class City extends DataSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7339731256737905870L;
	private int id;
	private int cityId;
	private int provinceId;
	private String cityName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
