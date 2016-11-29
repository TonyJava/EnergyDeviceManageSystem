package com.sitanems.device.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sitanems.data.DeviceInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.springInterface.ContextFactory;

public class DeviceFactory {
	private static DeviceFactory instance =null;
	
	private Map<String, String> deviceMap;
	private DeviceFactory()
	{
		deviceMap = new HashMap<String, String>();
		fillMap();
	}
	
	public static DeviceFactory getInstance()
	{
		if (instance == null)
		{
			instance = new DeviceFactory();	
		}
		return instance;
	}
	
	public void fillMap()
	{
		if (deviceMap != null)
		{
			deviceMap.put("inPower", "inPowerDevice");
			deviceMap.put("highVoltage", "highVoltageDevice");
		}
	}
	
	public ModbusTcpDevice getDevice(DeviceInfo dInfo)
	{
		ModbusTcpDevice device = (ModbusTcpDevice) ContextFactory.
				getApplicationContext().getBean(deviceMap.get(dInfo.type));
		device.ipAddress = dInfo.ipAddr;
		device.port = dInfo.port;
		device.slaveId = dInfo.slaveId;
		device.init();
		return device;
	}
	
	public List<ModbusTcpDevice> getDevice(List<DeviceInfo> dInfo)
	{
		List<ModbusTcpDevice> deviceList = new ArrayList<ModbusTcpDevice>();
		for (int i = 0; i < dInfo.size(); i++)
		{
			deviceList.add(getDevice(dInfo.get(i)));
		}
		
		return deviceList;
	}
}
