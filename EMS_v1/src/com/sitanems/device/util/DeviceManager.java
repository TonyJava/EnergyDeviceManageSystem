package com.sitanems.device.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sitanems.data.DeviceInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;

public class DeviceManager {
	private List<DeviceInfo> dInfoList = null;
	private Map<DeviceInfo, ModbusTcpDevice> deviceMap = null;
	
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
