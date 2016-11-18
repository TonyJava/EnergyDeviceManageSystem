package com.sitanems.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sitanems.appInterface.DeviceFactory;
import com.sitanems.appInterface.DeviceInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;

public class SimpleTest1 {

	public static void main(String[] args) {

		List<DeviceInfo> dInfo = new ArrayList<DeviceInfo>();
		dInfo.add(new DeviceInfo("inPower", "127.0.0.1", 502, 1));
		dInfo.add(new DeviceInfo("highVoltage", "127.0.0.1", 503, 1));
		
		List<ModbusTcpDevice> dList = DeviceFactory.getInstance().getDevice(dInfo);
		
		
		dList.get(0).write( "485_3站地址(IO通讯板)", 3);
		
		dList.get(1).write( "系统状态", 5);
	}

}
