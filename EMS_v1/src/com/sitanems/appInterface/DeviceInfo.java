package com.sitanems.appInterface;

public class DeviceInfo {
	public String type;
	public String ipAddr;
	public int port;
	public int slaveId;
	
	public DeviceInfo(String type, String ipAddr, int port, int slaveId) {
		super();
		this.type = type;
		this.ipAddr = ipAddr;
		this.port = port;
		this.slaveId = slaveId;
	}
}
