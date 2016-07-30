package com.zyuc.common.model.condition;


public enum SortKeyType{
	 
	START_TIME(0,"start_time"),
	TIME(1,"time"),
	MAX_BYTES(2,"max_bytes"),
	MAX_PKTS(3,"max_pkts"),
	CREATE_TIME(4, "create_time"), 
	UPDATE_TIME(5, "update_time");

	private int dbNumber;
	
	private String name;
	
	private SortKeyType(int dbNumber,String name){
		this.dbNumber = dbNumber;
		this.name = name;
	}
	
	public static SortKeyType valueof(String name){
		SortKeyType[] values = SortKeyType.values();
		for(SortKeyType type : values){
			if(type.name.equalsIgnoreCase(name)){
				return type;
			}
		}
		return null;
	}
	
	/**
	 * valueOf.
	 * @param ordinal int
	 * @return SortKeyType
	 */
	public static SortKeyType valueOf(int ordinal) {
		SortKeyType[] values = SortKeyType.values();
		if (ordinal >= values.length) {
			return null;
		} 
		else {
			return values[ordinal];
		}
	}
}
