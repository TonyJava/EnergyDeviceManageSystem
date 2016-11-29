package com.sitanems.dataAcquire;

import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.sitanems.data.RTDataInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.event.util.BasicEventGenerator;

public class DataAcquireAssistant extends BasicEventGenerator{
	private List<ModbusTcpDevice> deviceList = null;
	private Timer timer = null;
	private AcquireTask task = null;
	private long peroid;
	private long delay;
	
	public List<ModbusTcpDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<ModbusTcpDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public long getPeroid() {
		return peroid;
	}

	public void setPeroid(long peroid) {
		this.peroid = peroid;
	}

	public DataAcquireAssistant()
	{
		timer = new Timer();
		task = new AcquireTask();
		peroid = 30000;
		delay = 1000;
	}
	
	public void startAcquire()
	{
		timer.schedule(task, delay, peroid);
	}
	
	public void stopAcquire()
	{
		timer.cancel();
	}
	
	class AcquireTask extends TimerTask {  
		private ModbusTcpDevice tempDevice = null;
	    @Override  
	    public void run() {  
	        for (int i = 0; i < deviceList.size(); i++)
	        {
	        	tempDevice = deviceList.get(i);
	        	tempDevice.updateAllVar();
	        	RTDataInfo dataInfo = 
	        		new RTDataInfo(1, new Timestamp(System.currentTimeMillis()), 
	        				tempDevice.getAllVarValue());
	        	publish(new AcquireDataEvent(dataInfo));
	        }
	    }   
	} 
	
	
}

 