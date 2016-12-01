package com.sitanems.data;

import java.util.Date;
import java.util.Map;

public class RTDataInfo {
	public int id;
	public Date dt;
	public Map<String, Integer> values;

	public RTDataInfo(int id, Date dt, Map<String, Integer> values) {
		this.id = id;
		this.dt = dt;
		this.values = values;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}



	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Map<String, Integer> getValues() {
		return values;
	}

	public void setValues(Map<String, Integer> values) {
		this.values = values;
	}
	
}
