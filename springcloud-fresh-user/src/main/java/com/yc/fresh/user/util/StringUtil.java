package com.yc.fresh.user.util;

public class StringUtil {
	/**
	 * 为空判断
	 * @param strs
	 * @return
	 */
	public static boolean checkNull(String ... strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		} else {
			for (String str : strs) {
				if (str == null || "".equals(str) || "null".equals(str)) {
					return true;
				}
			}
			return false;
		}
	}
	
	public static boolean checkNull(Object ... strs){
		if(strs == null || strs.length <= 0){
			return true;
		}else{
			for(Object str : strs){
				if(str == null || "".equals(String.valueOf(str))){
					return true;
				}
			}
		}
		return false;
	}
}
