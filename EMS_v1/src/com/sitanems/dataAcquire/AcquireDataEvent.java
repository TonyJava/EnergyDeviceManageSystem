package com.sitanems.dataAcquire;

import com.sitanems.data.RTDataInfo;
import com.sitanems.event.util.IEvent;

public class AcquireDataEvent implements IEvent {
	private RTDataInfo data;
	
	public AcquireDataEvent(RTDataInfo data) {
		this.data = data;
	}

	@Override
	public Object getEvent() {
		return this.data;
	}

}
