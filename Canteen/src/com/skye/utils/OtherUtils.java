package com.skye.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OtherUtils {

	private OtherUtils() {

	}

	public static String getId(String string) {
		StringBuilder sb = new StringBuilder(string);

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		sb.append(df.format(new Date()));// new Date()为获取当前系统时间

		return sb.toString();
	}
}
