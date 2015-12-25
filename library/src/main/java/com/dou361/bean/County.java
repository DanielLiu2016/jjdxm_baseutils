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
 * 描 述：县或区
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class County extends DataSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7339731256737905870L;
	private int id;
	private int countyId;
	private int cityId;
	private String countyName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountyId() {
		return countyId;
	}

	public void setCountyId(int countyId) {
		this.countyId = countyId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

}
