package com.sitanems.appInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sitanems.device.commonDevice.ModbusTcpDevice;

public class DeviceFactory {
	private static DeviceFactory instance =null;
	private static ClassPathXmlApplicationContext ctx = null;
	private static Map<String, String> deviceMap;
	private DeviceFactory()
	{
		
	}
	
	public static DeviceFactory getInstance()
	{
		if (instance == null)
		{
			instance = new DeviceFactory();
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			deviceMap = new HashMap<String, String>();
			fillMap();
		}
		return instance;
	}
	
	public static void fillMap()
	{
		if (deviceMap != null)
		{
			deviceMap.put("inPower", "inPowerDevice");
			deviceMap.put("highVoltage", "highVoltageDevice");
		}
	}
	
	public ModbusTcpDevice getDevice(DeviceInfo dInfo)
	{
		ModbusTcpDevice device = (ModbusTcpDevice) ctx.getBean(deviceMap.get(dInfo.type));
		device.ipAddress = dInfo.ipAddr;
		device.port = dInfo.port;
		device.slaveId = dInfo.slaveId;
		
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
