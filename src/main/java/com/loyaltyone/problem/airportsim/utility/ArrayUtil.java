package com.loyaltyone.problem.airportsim.utility;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayUtil {
	public static <T> boolean isNullOrEmpty(T[] array) {
		return ((array == null) || (array.length == 0));
	}

	public static <T> T[] toArray(Collection<T> items, Class<T> clazz) {
		Guard.notNull(items, "items");

		Object[] array = (Object[]) (Object[]) Array.newInstance(clazz,
				items.size());

		return (T[]) items.toArray(array);
	}

	public static <T> List<T> toList(T[] items) {
		Guard.notNull(items, "items");

		List list = new ArrayList();

		for (Object item : items) {
			if (item == null)
				continue;
			list.add(item);
		}

		return list;
	}

	public static <T> Set<T> toSet(T[] items) {
		Guard.notNull(items, "items");

		Set result = new HashSet();

		for (Object item : items) {
			if (item == null)
				continue;
			result.add(item);
		}

		return result;
	}
}
