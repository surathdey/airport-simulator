package com.loyaltyone.problem.airportsim.utility;

import java.util.Collection;

public class Guard {
	public static void notNullOrEmpty(String obj, String name) {
		if ((obj != null) && (obj.trim().length() != 0))
			return;
		throw new IllegalArgumentException(String.format(
				"%s is null or empty.", new Object[] { name }));
	}

	public static void notNullOrEmpty(Object[] obj, String name) {
		if (!(ArrayUtil.isNullOrEmpty(obj)))
			return;
		throw new IllegalArgumentException(String.format(
				"%s is null or empty.", new Object[] { name }));
	}

	public static void notNullOrEmpty(Collection<?> obj, String name) {
		if (!(CollectionUtil.isNullOrEmpty(obj)))
			return;
		throw new IllegalArgumentException(String.format(
				"%s is null or empty.", new Object[] { name }));
	}

	public static void notNull(Object o, String name) {
		if (o != null)
			return;
		throw new IllegalArgumentException(String.format("%s is null.",
				new Object[] { name }));
	}

	public static void isTrueDependency(boolean expr, String errorMsg) {
		if (expr)
			return;
		throw new IllegalArgumentException(errorMsg);
	}
}