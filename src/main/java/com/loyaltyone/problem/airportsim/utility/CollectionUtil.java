package com.loyaltyone.problem.airportsim.utility;

import java.util.Collection;
import java.util.Iterator;

public class CollectionUtil {
	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return ((collection == null) || (collection.size() == 0));
	}

	public static <T> String toString(Collection<T> collection, String delimiter) {
		Guard.notNull(delimiter, "delimiter");

		if (isNullOrEmpty(collection)) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		for (Iterator i$ = collection.iterator(); i$.hasNext();) {
			Object element = i$.next();

			sb.append(delimiter).append(element.toString());
		}

		sb.delete(0, delimiter.length());

		return sb.toString();
	}
}
