package com.sitanems.device.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitanems.appInterface.DeviceFactory;
import com.sitanems.appInterface.DeviceInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;

public class DeviceManager {
	public List<DeviceInfo> dInfoList = null;
	public Map<DeviceInfo, ModbusTcpDevice> deviceMap = null;
	
	public DeviceManager()
	{
		dInfoList = new ArrayList<DeviceInfo>();
		deviceMap = new HashMap<DeviceInfo, ModbusTcpDevice>();
	}
	
	public void addDevice(DeviceInfo dInfo)
	{
		dInfoList.add(dInfo);
	}
	
	public void addDevice(List<DeviceInfo> dInfoList)
	{
		for (int i = 0; i < dInfoList.size(); i++)
		{
			addDevice(dInfoList.get(i));
		}
	}
	
	public void initDevice()
	{
		ModbusTcpDevice tempDevice = null;
		for (int i = 0; i < dInfoList.size(); i++)
		{
			tempDevice = DeviceFactory.getInstance().getDevice(dInfoList.get(i));
			deviceMap.put(dInfoList.get(i), tempDevice);
		}
	}
	
	public void dynamicAddDevice(DeviceInfo dInfo)
	{
		dInfoList.add(dInfo);
		ModbusTcpDevice temp = DeviceFactory.getInstance().getDevice(dInfo);
		deviceMap.put(dInfo, temp);
	}
	
	public ModbusTcpDevice getDevice(DeviceInfo dInfo)
	{
		return deviceMap.get(dInfo);
	}
}
