package com.coolweather.xiaoranas.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sp工具类
 */
public class PrefUtils {
	
	public static boolean getBoolean(Context ct,String key,boolean defValue) {
		SharedPreferences sp = ct.getSharedPreferences("config",Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
		
	}
	
	public static void setBoolean(Context ct,String key,boolean value) {
		SharedPreferences sp = ct.getSharedPreferences("config",Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
		
	}
	
	
	public static String getString(Context ct,String key,String defValue) {
		SharedPreferences sp = ct.getSharedPreferences("config",Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
		
	}
	
	public static void setString(Context ct,String key,String value) {
		SharedPreferences sp = ct.getSharedPreferences("config",Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
		
	}
	
	
	public static int getInt(Context ct,String key,int defValue) {
		SharedPreferences sp = ct.getSharedPreferences("config",Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
		
	}
	
	public static void setInt(Context ct,String key,int value) {
		SharedPreferences sp = ct.getSharedPreferences("config",Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
		
	}
}
