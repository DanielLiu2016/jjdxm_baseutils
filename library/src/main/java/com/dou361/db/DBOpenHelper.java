package com.dou361.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jingwang.eluxue_online.utils.SystemConfig;
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
 * 描 述：数据库帮助工具
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class DBOpenHelper extends SQLiteOpenHelper {

	/**
	 * 表创建语句
	 */
	private static String dbName = SystemConfig.Chinacity_DB_Name;
	private static int dbVersion = SystemConfig.Default_DB_Version;

	/**
	 * @param context
	 *            上下文
	 * @param dbName
	 *            数据库
	 * @param sql
	 *            表创建语句
	 */
	public DBOpenHelper(Context context) {
		super(context, dbName, null, dbVersion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		/**
		 * 初始化表结构
		 */
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
