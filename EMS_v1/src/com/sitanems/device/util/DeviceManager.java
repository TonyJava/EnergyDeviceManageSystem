package com.sitanems.device.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitanems.appInterface.DeviceConfiger;
import com.sitanems.data.DeviceInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.springInterface.ContextFactory;

public class DeviceManager {
	private List<DeviceInfo> dInfoList = null;
	private Map<DeviceInfo, ModbusTcpDevice> deviceMap = null;
	private DeviceFactory deviceFactory = null;
	
	public List<DeviceInfo> getdInfoList() {
		return dInfoList;
	}

	public void setdInfoList(List<DeviceInfo> dInfoList) {
		this.dInfoList = dInfoList;
	}

	public Map<DeviceInfo, ModbusTcpDevice> getDeviceMap() {
		return deviceMap;
	}

	public void setDeviceMap(Map<DeviceInfo, ModbusTcpDevice> deviceMap) {
		this.deviceMap = deviceMap;
	}

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
		deviceFactory = (DeviceFactory) ContextFactory.
				getApplicationContext().getBean("deviceFactory");
		for (int i = 0; i < dInfoList.size(); i++)
		{
			tempDevice = deviceFactory.getDevice(dInfoList.get(i));
			deviceMap.put(dInfoList.get(i), tempDevice);
		}
	}
	
	public void dynamicAddDevice(DeviceInfo dInfo)
	{
		dInfoList.add(dInfo);
		ModbusTcpDevice temp = deviceFactory.getDevice(dInfo);
		deviceMap.put(dInfo, temp);
	}
	
	public DeviceFactory getDeviceFactory() {
		return deviceFactory;
	}

	public void setDeviceFactory(DeviceFactory deviceFactory) {
		this.deviceFactory = deviceFactory;
	}

	public ModbusTcpDevice getDevice(DeviceInfo dInfo)
	{
		return deviceMap.get(dInfo);
	}
}
