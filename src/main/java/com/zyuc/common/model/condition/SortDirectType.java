package com.zyuc.common.model.condition;

public enum SortDirectType {
	
	ASC,
	DESC;
	
	/**
	 * valueOf.
	 * @param ordinal int
	 * @return SortDirectType
	 */
	public static SortDirectType valueOf(int ordinal) {
		SortDirectType[] values = SortDirectType.values();
		if (ordinal >= values.length) {
			return null;
		} 
		else {
			return values[ordinal];
		}
	}
}
