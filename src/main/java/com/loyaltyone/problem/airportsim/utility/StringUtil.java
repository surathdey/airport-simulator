package com.loyaltyone.problem.airportsim.utility;

import java.io.File;

public class StringUtil {
	public static boolean isNullOrEmpty(String s) {
		return ((s == null) || (s.length() == 0));
	}

	public static boolean isAnyNullOrEmpty(String[] strings) {
		if ((strings == null) || (strings.length == 0)) {
			return false;
		}

		for (String string : strings) {
			if (isNullOrEmpty(string)) {
				return true;
			}
		}

		return false;
	}
	public static String concat(Object...objs) {
		StringBuilder sb = new StringBuilder();

		if (objs != null) {
			for (Object obj : objs) {
				sb.append(obj);
			}
		}

		return sb.toString();
	}



}
