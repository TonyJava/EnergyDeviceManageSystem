package com.sitanems.appInterface;

import java.util.List;

import com.sitanems.data.DeviceInfo;
import com.sitanems.device.util.DeviceManager;

public class DeviceConfiger {
	private List<DeviceInfo> dInfoList;
	private DeviceManager dManager;
	
	public DeviceConfiger()
	{
		dManager = new DeviceManager();
	}
	
	public DeviceManager getdManager() {
		return dManager;
	}

	public void setdManager(DeviceManager dManager) {
		this.dManager = dManager;
	}

	public List<DeviceInfo> getdInfoList() {
		return dInfoList;
	}

	public void setdInfoList(List<DeviceInfo> dInfoList) {
		this.dInfoList = dInfoList;
	}
	
	public void init()
	{
		dManager.addDevice(dInfoList);
		dManager.initDevice();
	}
}
