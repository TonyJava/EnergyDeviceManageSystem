package com.sitanems.main;

import com.sitanems.appInterface.DeviceConfiger;
import com.sitanems.dataAcquire.DataAcquireAssistant;
import com.sitanems.springInterface.ContextFactory;

public class SimpleTest2 {

	public static void main(String[] args) {
		DeviceConfiger deviceConfiger = (DeviceConfiger) ContextFactory.
				getApplicationContext().getBean("deviceConfiger");
		DataAcquireAssistant dataAcquire = (DataAcquireAssistant) ContextFactory.
				getApplicationContext().getBean("dataAcquireAssistant");
		deviceConfiger.init();
		dataAcquire.startAcquire();
	}

}
