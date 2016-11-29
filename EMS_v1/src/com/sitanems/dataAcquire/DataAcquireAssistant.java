package com.sitanems.dataAcquire;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.sitanems.appInterface.DeviceConfiger;
import com.sitanems.data.DeviceInfo;
import com.sitanems.data.RTDataInfo;
import com.sitanems.device.commonDevice.ModbusTcpDevice;
import com.sitanems.event.util.BasicEventGenerator;
import com.sitanems.springInterface.ContextFactory;

public class DataAcquireAssistant extends BasicEventGenerator{
	private List<ModbusTcpDevice> deviceList = null;
	private Timer timer = null;
	private AcquireTask task = null;
	private long peroid;
	private long delay;
	private DeviceConfiger deviceConfiger = null;
	private Map<DeviceInfo, ModbusTcpDevice> deviceMap = null;
	
	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

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
		peroid = 10000;
		delay = 1000;
		
	}
	
	public void init()
	{
		deviceConfiger = (DeviceConfiger) ContextFactory.
				getApplicationContext().getBean("deviceConfiger");
		deviceMap = deviceConfiger.getDeviceMap();
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
	    	System.out.println("begin time :" + new Date());
	        for (Map.Entry<DeviceInfo, ModbusTcpDevice> entry : deviceMap.entrySet())
	        {
	        	System.out.println("device info :" + entry.getKey().type);
	        	tempDevice = entry.getValue();
	        	tempDevice.updateAllVar();
	        	RTDataInfo dataInfo = 
	        		new RTDataInfo(1, new Timestamp(System.currentTimeMillis()), 
	        				tempDevice.getAllVarValue());
	        	publish(new AcquireDataEvent(dataInfo));
	        }
	        System.out.println("end time :" + new Date());
	    }   
	} 
	
	
}

 